package me.anisjamadar.hotelbooking.dtos.bookings;

import lombok.Data;
import me.anisjamadar.hotelbooking.domain.BookingStatus;
import me.anisjamadar.hotelbooking.dtos.rooms.RoomDto;
import me.anisjamadar.hotelbooking.dtos.users.UserDto;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookingDto {
    private Long id;
    private UserDtoForBookings user;
    private RoomDto room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus status;
    private BigDecimal totalPrice;
}
