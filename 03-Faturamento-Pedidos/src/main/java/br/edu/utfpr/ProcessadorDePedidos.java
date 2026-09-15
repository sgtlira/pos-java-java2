package br.edu.utfpr;

import br.edu.utfpr.dominio.*;
import br.edu.utfpr.utilidades.LeitorDePedidos;
import br.edu.utfpr.utilidades.ServicosExternos;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.StructuredTaskScope;

public final class ProcessadorDePedidos {

    public ResultadoPedido processarPedido(Pedido pedido) {
        // TODO ver requisitos
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.awaitAllSuccessfulOrThrow())){
            var estoqueTask = scope.fork(() ->
                ServicosExternos.consultarEstoque(
                        pedido.produto(),
                        pedido.identificador()));

            var precoTask = scope.fork(() ->
                    ServicosExternos.consultarPreco(
                            pedido.produto(),
                            pedido.identificador()));

            scope.join();

            var estoque = estoqueTask.get();
            var preco = precoTask.get();

            if (estoque.quantidadeDisponivel() < pedido.quantidade()){
                return new PedidoRejeitado(
                        pedido.identificador(), "estoque insuficiente");
            }

            var frete = cotarFrete(pedido.produto());

            var valorTotal = preco.valorUnitario()
                    .multiply(java.math.BigDecimal.valueOf(pedido.quantidade()))
                    .add(frete.valor());
            return new PedidoAprovado(
                    pedido.identificador(),
                    valorTotal,
                    frete);
        } catch (Exception e) {
            return new PedidoRejeitado(
                    pedido.identificador(),
                    e.getMessage());
        }
    }

    private CotacaoFrete cotarFrete(String produto) throws InterruptedException {
        // TODO ver requisitos
        try (var scope = StructuredTaskScope.open(
                StructuredTaskScope.Joiner.anySuccessfulResultOrThrow())) {

            var frete1 = scope.fork(() ->
                    ServicosExternos.cotarFreteTransportadoraUm(produto));

            var frete2 = scope.fork(() ->
                    ServicosExternos.cotarFreteTransportadoraDois(produto));

            scope.join();

            if (frete1.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
                return frete1.get();
            }

            return frete2.get();
        }
    }

    public Relatorio processarArquivo(Path arquivoEntrada) {
        // TODO ver requisitos
        var pedidos = LeitorDePedidos.ler(arquivoEntrada);

        List<PedidoAprovado> aprovados = new ArrayList<>();
        List<PedidoRejeitado> rejeitados = new ArrayList<>();

        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.<ResultadoPedido>allSuccessfulOrThrow())){
            List<StructuredTaskScope.Subtask<ResultadoPedido>> tarefas = new ArrayList<>();

            for (Pedido pedido : pedidos){
                tarefas.add(scope.fork(() -> processarPedido(pedido)));
            }

            scope.join();

            for (var tarefa : tarefas){
                ResultadoPedido resultado = tarefa.get();

                if (resultado instanceof PedidoAprovado aprovado) {
                    aprovados.add(aprovado);
                } else if (resultado instanceof PedidoRejeitado rejeitado) {
                    rejeitados.add(rejeitado);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Relatorio(aprovados, rejeitados);
    }
}
