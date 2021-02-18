package com.cinema.util;

import com.cinema.lib.EmailValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {
    private final String EMAIL_VALIDATION_REGEX = "\\w+@[a-z]+\\.[a-z]+";
    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches(EMAIL_VALIDATION_REGEX)
                && (contactField.length() > 5);
    }
}
