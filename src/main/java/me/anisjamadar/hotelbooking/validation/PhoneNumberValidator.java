package me.anisjamadar.hotelbooking.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext constraintValidatorContext) {
        if (phoneField == null) {
            return false;
        }
        return phoneField.matches("^\\+?[1-9][0-9]{7,14}$");
    }
}
