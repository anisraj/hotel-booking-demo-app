package me.anisjamadar.hotelbooking.repositories;

import me.anisjamadar.hotelbooking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}