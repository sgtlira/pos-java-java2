package br.edu.utfpr.dominio;

import br.edu.utfpr.anotacoes.NaoNulo;
import br.edu.utfpr.anotacoes.Tamanho;
import java.time.LocalDate;

public record ContaPessoaFisica(
        @NaoNulo
        @Tamanho(min = 4, max = 4)
        String agencia,

        @NaoNulo
        String numero,

        @NaoNulo
        String titular,

        @NaoNulo
        @Tamanho(min = 11, max = 11)
        String cpf,
        
        @NaoNulo
        String email,
        
        @NaoNulo(mensagem = "a data de nascimento e obrigatoria")
        LocalDate dataNascimento
        ) implements ContaBancaria{
    // TODO revisar e implementar corretamente conforme requisitos
}
