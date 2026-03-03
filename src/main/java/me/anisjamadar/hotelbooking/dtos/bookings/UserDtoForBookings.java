package me.anisjamadar.hotelbooking.dtos.bookings;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import me.anisjamadar.hotelbooking.dtos.users.BookingDtoForUser;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDtoForBookings {
    private Long id;
    private String name;
    private String email;
    private String phone;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
    private LocalDateTime updatedAt;
}
