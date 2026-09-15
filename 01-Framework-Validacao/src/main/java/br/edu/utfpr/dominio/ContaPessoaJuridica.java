package br.edu.utfpr.dominio;

import br.edu.utfpr.anotacoes.NaoNulo;
import br.edu.utfpr.anotacoes.Positivo;
import br.edu.utfpr.anotacoes.Tamanho;

import java.math.BigDecimal;

public record ContaPessoaJuridica(
            @NaoNulo
            @Tamanho(min = 4, max = 4)
            String agencia,
        
            @NaoNulo
            String numero,
            
            @NaoNulo
            String razaoSocial,
            
            @NaoNulo
            @Tamanho(min = 14, max = 14)
            String cnpj,
            
            @NaoNulo(mensagem = "o capital social e obrigatorio")
            @Positivo
            BigDecimal capitalSocial
        ) implements ContaBancaria{
    // TODO revisar e implementar corretamente conforme requisitos
    
}
