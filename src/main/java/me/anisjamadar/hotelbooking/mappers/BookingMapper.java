package me.anisjamadar.hotelbooking.mappers;

import me.anisjamadar.hotelbooking.domain.Booking;
import me.anisjamadar.hotelbooking.dtos.bookings.BookingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDto toDto(Booking booking);
}
