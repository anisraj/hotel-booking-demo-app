package me.anisjamadar.hotelbooking.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {
    @NotNull(message = "user id is required")
    private Long userId;

    @NotNull(message = "room id is required")
    private Long roomId;

    @NotNull(message = "Check in date is required")
    @FutureOrPresent(message = "Check in cannot in past")
    private LocalDate checkInDate;

    @NotNull(message = "Check out date is required")
    @Future(message = "Check out must be after check in")
    private LocalDate checkOutDate;
}
