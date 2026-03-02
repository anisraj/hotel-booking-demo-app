package me.anisjamadar.hotelbooking.dtos;

import lombok.Data;
import me.anisjamadar.hotelbooking.domain.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookingDto {
    private Long id;
    private UserDto user;
    private RoomDto room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus status;
    private BigDecimal totalPrice;
}
