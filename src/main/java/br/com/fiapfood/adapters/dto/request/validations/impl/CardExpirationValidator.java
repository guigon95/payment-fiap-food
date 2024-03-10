package br.com.fiapfood.adapters.dto.request.validations.impl;

import br.com.fiapfood.adapters.dto.request.validations.CardExpiration;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class CardExpirationValidator implements ConstraintValidator<CardExpiration, String> {
    private String dateFormat;

    @Override
    public void initialize(CardExpiration cardExpiration) {
        this.dateFormat  = "MM/yy";
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
           Date valueDate = sdf.parse(value);
           Date now =sdf.parse(sdf.format(Date.from(Instant.now())));
            return valueDate.after(now);
        } catch (Exception e) {
            return false;
        }
    }
}
