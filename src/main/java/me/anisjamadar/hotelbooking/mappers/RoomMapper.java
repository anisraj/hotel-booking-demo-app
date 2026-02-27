package me.anisjamadar.hotelbooking.mappers;

import me.anisjamadar.hotelbooking.domain.Room;
import me.anisjamadar.hotelbooking.dtos.RoomDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDto toDto(Room room);
}
