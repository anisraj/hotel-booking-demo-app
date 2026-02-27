package me.anisjamadar.hotelbooking.repositories;

import me.anisjamadar.hotelbooking.domain.Room;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @EntityGraph(attributePaths = "bookings")
    @Query("SELECT r FROM Room r where r.id = :id")
    Optional<Room> getRoomById(@Param("id") Long id);
}