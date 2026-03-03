package me.anisjamadar.hotelbooking.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import me.anisjamadar.hotelbooking.dtos.rooms.AvailableRoomsRequest;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, AvailableRoomsRequest> {
    @Override
    public boolean isValid(AvailableRoomsRequest value, ConstraintValidatorContext context) {
        if (value.getCheckIn() == null || value.getCheckOut() == null) {
            return false;
        }
        return value.getCheckOut().isAfter(value.getCheckIn());
    }
}
