package br.edu.utfpr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class CotadorDeMoedas {

    public Path verificarEntrada() {
        // TODO
        return Path.of("entrada", "moedas.txt");
    }

    public Path verificarSaida() {
        // TODO
        Path diretorio = Path.of("saida");
        try{ 
            Files.createDirectories(diretorio);   
        } catch (IOException e){
            System.err.println("Erro ao criar o diretorio de saida");
            System.exit(1);
        }
        return diretorio.resolve("cotacoes.csv");
    }

    public List<String> lerArquivoDeMoedas(Path entrada) {
        // TODO
        try{
            return Files.readAllLines(entrada)
                    .stream()
                    .map(String::trim)
                    .map(String::toUpperCase)
                    .filter(moeda -> moeda.matches("^[A-Z]{3}$"))
                    .toList();
        } catch(IOException e){
            System.err.println("Erro ao ler o arquivo de moedas.");
            return List.of();
        }
    }

    public void cotarERegistrar(Path saida, List<String> moedas, ClienteCambio cliente) {
        // TODO
        if (moedas.isEmpty()){
            return;
        }
        var consultas = moedas.stream()
                .map(cliente::consultar)
                .toList();
        CompletableFuture.allOf(consultas.toArray(new CompletableFuture[0]))
                .join();
        var cotacoes = consultas.stream()
                .map(CompletableFuture::join)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        gravarEmCSV(saida, cotacoes);
    }

    private void gravarEmCSV(Path saida, List<Cotacao> cotacoes) {
        // TODO
        try{
            if(!Files.exists(saida)){
                Files.createFile(saida);
            }
            var linhas = new ArrayList<String>();
            linhas.add("moeda,valor,coletadoEm");
            linhas.addAll(cotacoes.stream()
                            .map(Cotacao::paraCsv)
                            .toList()
            );
            var conteudo = String.join("\n", linhas) + "\n";
            Files.writeString(saida, conteudo);
        } catch (IOException e){
           System.err.println("Erro ao gravar o arquivo CSV");
           System.exit(1); 
        }
    }
}
