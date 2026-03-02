package me.anisjamadar.hotelbooking.mappers;

import me.anisjamadar.hotelbooking.domain.Booking;
import me.anisjamadar.hotelbooking.dtos.BookingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "user.bookings", ignore = true)
    BookingDto toDto(Booking booking);
}
