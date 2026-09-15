package br.edu.utfpr.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO requisito 7
@Retention(RetentionPolicy.RUNTIME)

@Target({
        ElementType.FIELD, ElementType.RECORD_COMPONENT
})

public @interface NaoNulo {
    String mensagem() default "nao pode ser nulo";
}
