package me.anisjamadar.hotelbooking.dtos.users;

import lombok.Data;
import me.anisjamadar.hotelbooking.domain.BookingStatus;
import me.anisjamadar.hotelbooking.dtos.rooms.RoomDto;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookingDtoForUser {
    private Long id;
    private RoomDto room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus status;
    private BigDecimal totalPrice;
}
