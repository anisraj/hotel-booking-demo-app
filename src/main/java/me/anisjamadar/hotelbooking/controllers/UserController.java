package me.anisjamadar.hotelbooking.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.anisjamadar.hotelbooking.dtos.users.RegisterUserRequest;
import me.anisjamadar.hotelbooking.dtos.users.UpdateUserRequest;
import me.anisjamadar.hotelbooking.dtos.users.UserDto;
import me.anisjamadar.hotelbooking.exceptions.EmailAlreadyRegisteredException;
import me.anisjamadar.hotelbooking.exceptions.UserNotFoundException;
import me.anisjamadar.hotelbooking.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(
        @RequestParam(name = "sort", required = false, defaultValue = "") String sort
    ) {
        var list = userService.getAllUsers(sort);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(
        @PathVariable Long id
    ) {
        var userDto = userService.getUser(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> registerUser(
        @Valid @RequestBody RegisterUserRequest request,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        var userDto = userService.registerUser(request);
        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
        @PathVariable(name = "id") Long id,
        @Valid @RequestBody UpdateUserRequest request
    ) {
        var userDto = userService.updateUser(id, request);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
        @PathVariable Long id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "User not found.")
        );
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyRegistered() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", "Email is already registered.")
        );
    }
}
