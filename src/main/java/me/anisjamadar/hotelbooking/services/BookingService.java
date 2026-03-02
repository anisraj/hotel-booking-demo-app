package me.anisjamadar.hotelbooking.services;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.anisjamadar.hotelbooking.domain.Booking;
import me.anisjamadar.hotelbooking.domain.BookingStatus;
import me.anisjamadar.hotelbooking.dtos.BookingDto;
import me.anisjamadar.hotelbooking.dtos.BookingRequest;
import me.anisjamadar.hotelbooking.exceptions.*;
import me.anisjamadar.hotelbooking.mappers.BookingMapper;
import me.anisjamadar.hotelbooking.repositories.BookingRepository;
import me.anisjamadar.hotelbooking.repositories.RoomRepository;
import me.anisjamadar.hotelbooking.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final BookingMapper bookingMapper;

    public BookingDto createBooking(
        @Valid @RequestBody BookingRequest bookingRequest
    ) {
        if (!bookingRequest.getCheckOutDate().isAfter(bookingRequest.getCheckInDate())) {
            throw new DateValidationException("Check out date is invalid");
        }
        var user = userRepository
                .findById(bookingRequest.getUserId())
                .orElseThrow(UserNotFoundException::new);

        var room = roomRepository
                .findById(bookingRequest.getRoomId())
                .orElseThrow(RoomNotFoundException::new);

        long overlapping = bookingRepository.countOverlappingBookings(
                room.getId(), bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate());

        if (overlapping > 0) {
            throw new RoomNotAvailableException(
                "Room " + room.getRoomNumber() + " is not available for selected dates"
            );
        }

        long nights = ChronoUnit.DAYS.between(bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate());
        BigDecimal totalPrice = room.getPricePerNight().multiply(BigDecimal.valueOf(nights));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setCheckInDate(bookingRequest.getCheckInDate());
        booking.setCheckOutDate(bookingRequest.getCheckOutDate());
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setTotalPrice(totalPrice);

        Booking saved = bookingRepository.save(booking);
        return bookingMapper.toDto(saved);
    }

    public List<BookingDto> getBookingsByUserId(Long userId) {
        userRepository
                .findById(userId)
                .orElseThrow(UserNotFoundException::new);
        return bookingRepository
                .findAllByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    public BookingDto cancelBooking(Long bookingId) {
        var booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(BookingNotFoundException::new);

        if (booking.getCheckInDate().isBefore(LocalDate.now())) {
            throw new DateValidationException("Cannot cancel a past or ongoing booking");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        return bookingMapper.toDto(booking);
    }
}
