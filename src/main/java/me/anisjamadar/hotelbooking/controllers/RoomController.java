package me.anisjamadar.hotelbooking.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.anisjamadar.hotelbooking.dtos.AvailableRoomsRequest;
import me.anisjamadar.hotelbooking.dtos.RoomsAvailableOnDateRequest;
import me.anisjamadar.hotelbooking.dtos.RoomDto;
import me.anisjamadar.hotelbooking.exceptions.RoomNotFoundException;
import me.anisjamadar.hotelbooking.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        var rooms = roomService.getAllRooms();
        return ResponseEntity.ok().body(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getRoomById(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok().body(roomService.getRoomById(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<RoomDto>> getAvailableRooms(
        @Valid @RequestBody AvailableRoomsRequest availableRoomsRequest
    ) {
        return ResponseEntity.ok(roomService.getAvailableRooms(availableRoomsRequest.getCheckIn(), availableRoomsRequest.getCheckOut()));
    }

    @GetMapping("/available-on-date")
    public ResponseEntity<List<RoomDto>> getAvailableOnDate(
        @Valid @RequestBody RoomsAvailableOnDateRequest request
    ) {
        return ResponseEntity.ok(roomService.getAvailableRoomsOnDate(request.getDate()));
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoomNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of("error", "Room not found.")
        );
    }
}
