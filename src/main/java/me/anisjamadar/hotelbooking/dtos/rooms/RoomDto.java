package me.anisjamadar.hotelbooking.dtos.rooms;

import lombok.Data;
import me.anisjamadar.hotelbooking.domain.RoomType;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class RoomDto implements Serializable {
    Long id;
    String roomNumber;
    RoomType type;
    BigDecimal pricePerNight;
    String description;
}