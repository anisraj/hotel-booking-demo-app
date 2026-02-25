package me.anisjamadar.hotelbooking.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    //private Set<BookingDto> bookings;
}
