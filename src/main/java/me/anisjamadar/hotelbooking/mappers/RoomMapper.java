package me.anisjamadar.hotelbooking.mappers;

import me.anisjamadar.hotelbooking.domain.Room;
import me.anisjamadar.hotelbooking.dtos.rooms.RoomByIdDto;
import me.anisjamadar.hotelbooking.dtos.rooms.RoomDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDto toDto(Room room);
    RoomByIdDto toRoomByIdDto(Room room);
}
