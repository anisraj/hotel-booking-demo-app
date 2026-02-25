package me.anisjamadar.hotelbooking.mappers;

import me.anisjamadar.hotelbooking.domain.User;
import me.anisjamadar.hotelbooking.dtos.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
