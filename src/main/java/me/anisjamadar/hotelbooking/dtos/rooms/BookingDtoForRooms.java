package me.anisjamadar.hotelbooking.dtos.rooms;

import lombok.Data;
import me.anisjamadar.hotelbooking.domain.BookingStatus;
import me.anisjamadar.hotelbooking.dtos.bookings.UserDtoForBookings;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookingDtoForRooms {
    private Long id;
    private UserDtoForBookings user;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus status;
    private BigDecimal totalPrice;
}
