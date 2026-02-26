package me.anisjamadar.hotelbooking.mappers;

import me.anisjamadar.hotelbooking.domain.User;
import me.anisjamadar.hotelbooking.dtos.RegisterUserRequest;
import me.anisjamadar.hotelbooking.dtos.UpdateUserRequest;
import me.anisjamadar.hotelbooking.dtos.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    @Mapping(target = "phone", source = "phoneNumber")
    User toEntity(RegisterUserRequest registerUserRequest);

    void updateUser(UpdateUserRequest request, @MappingTarget User user);
}
