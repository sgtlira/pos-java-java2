package br.edu.utfpr.validacao;

import br.edu.utfpr.anotacoes.NaoNulo;
import br.edu.utfpr.anotacoes.Positivo;
import br.edu.utfpr.anotacoes.Tamanho;

import br.edu.utfpr.dominio.ContaBancaria;
import br.edu.utfpr.dominio.ContaPessoaFisica;
import br.edu.utfpr.dominio.ContaPessoaJuridica;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Validador {

    private static final int IDADE_MINIMA = 18;
    private static final BigDecimal CAPITAL_SOCIAL_MINIMO = new BigDecimal("10000");

    public ResultadoValidacao validar(ContaBancaria conta) {

        final List<Violacao> violacoes = new ArrayList<>();
        // TODO ver requisito 1, implementar aqui
        
        if (conta == null) {
            violacoes.add(
                new Violacao(
                        "(objeto)",
                        "o objeto a validar e nulo"
                )
        );

        return ResultadoValidacao.de(violacoes);
        }
    
        violacoes.addAll(validarCampos(conta));
        violacoes.addAll(regrasDeNegocio(conta));
        return ResultadoValidacao.de(violacoes);
    }
    

    public List<Violacao> validarCampos(Object objeto) {

        final List<Violacao> violacoes = new ArrayList<>();
        // TODO ver requisito 2, implementar aqui
        
        for(Field campo : objeto.getClass().getDeclaredFields()){
            campo.setAccessible(true);
            try{
                Object valor = campo.get(objeto);
                verificarNaoNulo(campo, valor, violacoes);
                verificarTamanho(campo, valor, violacoes);
                verificarPositivo(campo, valor, violacoes);
            } catch(IllegalAccessException e){
                throw new RuntimeException(e);
            }
        }
        return violacoes;
    }

    private void verificarNaoNulo(Field campo, Object valor, List<Violacao> acc) {
        // TODO implementar regra de negocio de nao nulo
        if(campo.isAnnotationPresent(NaoNulo.class) && valor == null){
            NaoNulo anotacao = campo.getAnnotation(NaoNulo.class);
            acc.add(new Violacao(campo.getName(), anotacao.mensagem()));
        }
    }

    private void verificarTamanho(Field campo, Object valor, List<Violacao> acc) {
        // TODO implementar regra de negocio de tamanho
        
        if(!campo.isAnnotationPresent(Tamanho.class) || valor == null){
            return;
        }
        Tamanho anotacao = campo.getAnnotation(Tamanho.class);
        String texto = valor.toString();
        if(texto.length() < anotacao.min() || texto.length() > anotacao.max()){
            acc.add(new Violacao(campo.getName(), anotacao.mensagem()));
        }
    }

    private void verificarPositivo(Field campo, Object valor, List<Violacao> acc) {
        // TODO implementar regra de negocio de positivo
        
        if(!campo.isAnnotationPresent(Positivo.class) || valor == null){
            return;
        }
        Positivo anotacao = campo.getAnnotation(Positivo.class);
        boolean invalido = false;
        switch (valor) {
            case Integer numero -> invalido = numero <= 0;
            case Double numero -> invalido = numero <= 0;
            case Long numero -> invalido = numero <= 0;
            case BigDecimal numero -> invalido = numero.compareTo(BigDecimal.ZERO) <= 0;
            default -> {
            }
        }
        if(invalido){
            acc.add(new Violacao(campo.getName(), anotacao.mensagem()));
        }
    }

    private List<Violacao> regrasDeNegocio(ContaBancaria conta) {
        // TODO implementar as regras de negocio por tipo
        return switch(conta){
            case ContaPessoaFisica pf -> validarMaioridade(pf);
            case ContaPessoaJuridica pj -> validarCapitalSocial(pj.capitalSocial());
        };
    }

    private List<Violacao> validarMaioridade(ContaPessoaFisica pf) {
        // TODO implementar regra de negocio de maioridade
        if(pf.dataNascimento() == null){
            return List.of();
        }  
        
        int idade = Period.between(pf.dataNascimento(), LocalDate.now()).getYears();
        if(idade < IDADE_MINIMA){
            return List.of(new Violacao(Violacao.DATA_NASCIMENTO_CAMPO, Violacao.DATA_NASCIMENTO_MENSAGEM));
        }
        return List.of();
    }

    private List<Violacao> validarCapitalSocial(BigDecimal capital) {
        // TODO implementar regra de negocio de capital social
        if(capital == null){
            return List.of();
        }
        
        if(capital.compareTo(CAPITAL_SOCIAL_MINIMO) < 0){
            return List.of(new Violacao(Violacao.CAPITAL_SOCIAL_CAMPO, Violacao.CAPITAL_SOCIAL_MENSAGEM));
        }
        return List.of();
    }
}
