package me.anisjamadar.hotelbooking.services;

import lombok.AllArgsConstructor;
import me.anisjamadar.hotelbooking.dtos.rooms.RoomByIdDto;
import me.anisjamadar.hotelbooking.dtos.rooms.RoomDto;
import me.anisjamadar.hotelbooking.exceptions.RoomNotFoundException;
import me.anisjamadar.hotelbooking.mappers.RoomMapper;
import me.anisjamadar.hotelbooking.repositories.BookingRepository;
import me.anisjamadar.hotelbooking.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final RoomMapper roomMapper;

    public List<RoomDto> getAllRooms() {
        return roomRepository
                .findAll()
                .stream()
                .map(roomMapper::toDto)
                .toList();
    }

    public RoomByIdDto getRoomById(Long id) {
        return roomMapper.toRoomByIdDto(
                roomRepository
                        .getRoomById(id)
                        .orElseThrow(RoomNotFoundException::new)
        );
    }

    public List<RoomDto> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        return bookingRepository
                .findAvailableRooms(checkIn, checkOut)
                .stream()
                .map(roomMapper::toDto)
                .toList();
    }

    public List<RoomDto> getAvailableRoomsOnDate(LocalDate date) {
        return bookingRepository
                .findAvailableRoomsOnDate(date)
                .stream()
                .map(roomMapper::toDto)
                .toList();
    }

    public boolean isRoomAvailable(Long roomId, LocalDate checkIn, LocalDate checkOut) {
        return bookingRepository.countOverlappingBookings(roomId, checkIn, checkOut) == 0;
    }
}
