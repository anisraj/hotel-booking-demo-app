package me.anisjamadar.hotelbooking.controllers;

import lombok.AllArgsConstructor;
import me.anisjamadar.hotelbooking.domain.User;
import me.anisjamadar.hotelbooking.dtos.UserDto;
import me.anisjamadar.hotelbooking.mappers.UserMapper;
import me.anisjamadar.hotelbooking.repositories.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(
        @RequestParam(name = "sort", required = false, defaultValue = "") String sort
    ) {
        if (!Set.of("name", "email").contains(sort)) {
            sort = "name";
        }
        var list = userRepository
                .findAll(Sort.by(sort))
                .stream()
                .map(userMapper::toDto)
                .toList();
        return ResponseEntity.ok(list);
    }
}
