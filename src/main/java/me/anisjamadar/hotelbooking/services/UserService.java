package me.anisjamadar.hotelbooking.services;

import lombok.AllArgsConstructor;
import me.anisjamadar.hotelbooking.dtos.RegisterUserRequest;
import me.anisjamadar.hotelbooking.dtos.UpdateUserRequest;
import me.anisjamadar.hotelbooking.dtos.UserDto;
import me.anisjamadar.hotelbooking.exceptions.EmailAlreadyRegisteredException;
import me.anisjamadar.hotelbooking.exceptions.UserNotFoundException;
import me.anisjamadar.hotelbooking.mappers.UserMapper;
import me.anisjamadar.hotelbooking.repositories.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers(String sort) {
        if (!Set.of("name", "email").contains(sort)) {
            sort = "name";
        }
        return userRepository
                .findAll(Sort.by(sort))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto getUser(Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return userMapper.toDto(user);
    }

    public UserDto registerUser(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyRegisteredException();
        }

        var user = userMapper.toEntity(request);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto updateUser(Long id, UpdateUserRequest request) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException();
        }

        userMapper.updateUser(request, user);
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public void deleteUser(Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException();
        }
        userRepository.delete(user);
    }
}
