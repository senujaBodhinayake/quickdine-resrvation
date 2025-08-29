package com.example.QuickDine;

import com.example.QuickDine.model.Reservation;
import com.example.QuickDine.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

@Transactional
@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Test
    void shouldReservationSuccessfully(){
        Reservation reservation = Reservation.builder()
                        .customerName("Senuja")
                                .contactNumber("0766970855")
                                        .reservationTime(LocalDateTime.of(2025,8,24,19,0))
                                                .tableNumber(5)
                                                        .build();
        Reservation saved = reservationService.save(reservation);


        assertNotNull(saved.getId());
        assertEquals("Senuja",saved.getCustomerName());
        assertEquals(5,saved.getTableNumber());

    }
    // Test for validation

    @Test
    void shouldThrowExceptionIfTableAlreadyBooked(){
        Reservation first = Reservation.builder()
                .customerName("Thamindu")
                .contactNumber("0704545454")
                .reservationTime(LocalDateTime.of(2025,8,24,19,0))
                .tableNumber(5)
                .build();
        reservationService.save(first);

        Reservation duplicate = Reservation.builder()
                .customerName("Gimhani")
                .contactNumber("0708989899")
                .reservationTime(LocalDateTime.of(2025,8,24,19,0))
                .tableNumber(5)
                .build();

        assertThrows(IllegalArgumentException.class,()->{
            reservationService.save(duplicate);
        });
    }
}
