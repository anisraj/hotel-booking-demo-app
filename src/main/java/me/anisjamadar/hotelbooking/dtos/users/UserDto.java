package me.anisjamadar.hotelbooking.dtos.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
    private LocalDateTime updatedAt;

    private Set<BookingDtoForUser> bookings = new HashSet<>();
}
