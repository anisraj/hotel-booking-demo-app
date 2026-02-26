package me.anisjamadar.hotelbooking.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

}
