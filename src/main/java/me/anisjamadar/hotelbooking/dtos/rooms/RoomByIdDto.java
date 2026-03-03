package me.anisjamadar.hotelbooking.dtos.rooms;

import lombok.Data;
import me.anisjamadar.hotelbooking.domain.RoomType;
import me.anisjamadar.hotelbooking.dtos.bookings.BookingDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
public class RoomByIdDto implements Serializable {
    Long id;
    String roomNumber;
    RoomType type;
    BigDecimal pricePerNight;
    String description;
    List<BookingDtoForRooms> bookings = new ArrayList<>();
}