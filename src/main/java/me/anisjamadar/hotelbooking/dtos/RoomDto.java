package me.anisjamadar.hotelbooking.dtos;

import lombok.Data;
import me.anisjamadar.hotelbooking.domain.RoomType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
public class RoomDto implements Serializable {
    Long id;
    String roomNumber;
    RoomType type;
    BigDecimal pricePerNight;
    String description;
    List<BookingDto> bookings = new ArrayList<>();
}