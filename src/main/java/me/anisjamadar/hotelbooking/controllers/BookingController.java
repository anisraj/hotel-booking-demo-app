package me.anisjamadar.hotelbooking.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import me.anisjamadar.hotelbooking.dtos.BookingDto;
import me.anisjamadar.hotelbooking.dtos.BookingRequest;
import me.anisjamadar.hotelbooking.exceptions.*;
import me.anisjamadar.hotelbooking.repositories.BookingRepository;
import me.anisjamadar.hotelbooking.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(
        @Valid @RequestBody BookingRequest bookingRequest,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        var bookingDto = bookingService.createBooking(bookingRequest);
        var uri = uriComponentsBuilder.path("/bookings/{id}").buildAndExpand(bookingDto.getId()).toUri();
        return ResponseEntity.created(uri).body(bookingDto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDto>> getBookingsByUser(
        @NotNull @PathVariable Long userId
    ) {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }

    @PutMapping("/cancel/{bookingId}/")
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
