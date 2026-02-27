package me.anisjamadar.hotelbooking.services;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.anisjamadar.hotelbooking.dtos.BookingDto;
import me.anisjamadar.hotelbooking.dtos.BookingRequest;
import me.anisjamadar.hotelbooking.exceptions.RoomNotFoundException;
import me.anisjamadar.hotelbooking.exceptions.UserNotFoundException;
import me.anisjamadar.hotelbooking.repositories.BookingRepository;
import me.anisjamadar.hotelbooking.repositories.RoomRepository;
import me.anisjamadar.hotelbooking.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public BookingDto createBooking(
        @Valid @RequestBody BookingRequest bookingRequest
    ) {
        var user = userRepository
                .findById(bookingRequest.getUserId())
                .orElseThrow(UserNotFoundException::new);

        var room = roomRepository
                .findById(bookingRequest.getRoomId())
                .orElseThrow(RoomNotFoundException::new);
        return null;
    }
}
