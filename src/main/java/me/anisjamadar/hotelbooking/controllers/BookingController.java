package me.anisjamadar.hotelbooking.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.anisjamadar.hotelbooking.dtos.bookings.BookingDto;
import me.anisjamadar.hotelbooking.dtos.bookings.BookingRequest;
import me.anisjamadar.hotelbooking.exceptions.DateValidationException;
import me.anisjamadar.hotelbooking.exceptions.RoomNotAvailableException;
import me.anisjamadar.hotelbooking.exceptions.RoomNotFoundException;
import me.anisjamadar.hotelbooking.exceptions.UserNotFoundException;
import me.anisjamadar.hotelbooking.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(
        @Valid @RequestBody BookingRequest bookingRequest
    ) {
        var bookingDto = bookingService.createBooking(bookingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDto>> getBookingsByUser(
        @PathVariable Long userId
    ) {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }

    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<BookingDto> cancelBooking(
        @PathVariable Long bookingId
    ) {
        var bookingDto = bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok().body(bookingDto);
    }

    @ExceptionHandler(DateValidationException.class)
    public ResponseEntity<Map<String, String>> handleDateValidationException() {
        return ResponseEntity.badRequest().body(
            Map.of("error", "Checkout date must be after check in")
        );
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoomNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of("error", "Room not found.")
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of("error", "User not found.")
        );
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<Map<String, String>> handleRoomNotAvailableException(
        RoomNotAvailableException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", exception.getMessage())
        );
    }


}
