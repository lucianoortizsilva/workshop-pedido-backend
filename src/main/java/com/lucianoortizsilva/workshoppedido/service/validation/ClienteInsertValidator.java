package com.lucianoortizsilva.workshoppedido.service.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * Anotação reponsável por realizar validações customizadas ao inserir um novo
 * cliente
 * 
 * @author ortiz
 *
 */
@Constraint(validatedBy = ClienteInsertValidatorImpl.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteInsertValidator {

	String message() default "Erro de validação";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}