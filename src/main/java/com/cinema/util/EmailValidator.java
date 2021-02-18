package com.cinema.util;

import com.cinema.lib.EmailValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements
        ConstraintValidator<EmailValidation, String> {
    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("\\w+@[a-z]+\\.[a-z]+")
                && (contactField.length() > 5);
    }
}
