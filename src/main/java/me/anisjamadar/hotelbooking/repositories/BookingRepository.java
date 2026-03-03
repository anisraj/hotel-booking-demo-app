package me.anisjamadar.hotelbooking.repositories;

import me.anisjamadar.hotelbooking.domain.Booking;
import me.anisjamadar.hotelbooking.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("""
        SELECT COUNT(b) FROM Booking b
        WHERE b.room.id = :roomId
        AND b.status = 'CONFIRMED'
        AND b.checkInDate < :checkOut
        AND b.checkOutDate > :checkIn
    """)
    long countOverlappingBookings(
        @Param("roomId") Long roomId,
        @Param("checkIn") LocalDate checkIn,
        @Param("checkOut") LocalDate checkOut
    );

    @Query("""
        SELECT r FROM Room r
        WHERE r.id NOT IN (
            SELECT b.room.id FROM Booking b
            WHERE b.status = 'CONFIRMED'
            AND b.checkInDate < :checkOut
            AND b.checkOutDate > :checkIn
        )
    """)
    List<Room> findAvailableRooms(
        @Param("checkIn") LocalDate checkIn,
        @Param("checkOut") LocalDate checkOut
    );

    @Query("""
        SELECT r FROM Room r
        WHERE r.id NOT IN (
            SELECT b.room.id FROM Booking b
            WHERE b.status = 'CONFIRMED'
            AND b.checkInDate <= :date
            AND b.checkOutDate > :date
        )
    """)
    List<Room> findAvailableRoomsOnDate(@Param("date") LocalDate date);

    List<Booking> findAllByUserIdOrderByCreatedAtDesc(Long userId);
}
