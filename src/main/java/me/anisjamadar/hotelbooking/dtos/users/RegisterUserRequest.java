package me.anisjamadar.hotelbooking.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import me.anisjamadar.hotelbooking.validation.PhoneNumber;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank
    @Size(min = 6, max = 25, message = "Password must be between 6 to 25 characters")
    private String password;

    @NotBlank(message = "Phone number cannot be empty")
    @PhoneNumber
    private String phoneNumber;
}
