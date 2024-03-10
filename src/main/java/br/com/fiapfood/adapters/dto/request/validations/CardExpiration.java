package br.com.fiapfood.adapters.dto.request.validations;

import br.com.fiapfood.adapters.dto.request.validations.impl.CardExpirationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ METHOD, FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CardExpirationValidator.class)
public @interface CardExpiration {
    String message() default "Expiration invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}