package me.anisjamadar.hotelbooking.dtos.rooms;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class RoomsAvailableOnDateRequest {
    @NotNull(message = "Date required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @FutureOrPresent(message = "Date must be in the future.")
    private LocalDate date;
}
