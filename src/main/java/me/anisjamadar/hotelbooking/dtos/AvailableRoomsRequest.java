package me.anisjamadar.hotelbooking.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import me.anisjamadar.hotelbooking.validation.ValidDateRange;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@ValidDateRange
public class AvailableRoomsRequest {
    @NotNull(message = "Check in date required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @FutureOrPresent(message = "Check-in date must be in the future.")
    private LocalDate checkIn;

    @NotNull(message = "Check out date required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @FutureOrPresent(message = "Check-out date must be in the future.")
    private LocalDate checkOut;
}
