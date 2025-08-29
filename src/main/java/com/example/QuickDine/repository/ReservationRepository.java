package com.example.QuickDine.repository;

import com.example.QuickDine.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByTableNumberAndReservationTime(int tableNumber, LocalDateTime resrvationTime);
}
