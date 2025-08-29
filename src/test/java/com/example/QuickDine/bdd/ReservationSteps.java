package com.example.QuickDine.bdd;

import com.example.QuickDine.model.Reservation;
import com.example.QuickDine.service.ReservationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReservationSteps {

    @Autowired
    private ReservationService reservationService;

    private Reservation first;
    private Exception caughtException;

    @Given("a table is already booked at 7 PM")
    public void a_table_is_already_booked_at_7_pm() {
        first = Reservation.builder()
                .customerName("Senuja")
                .contactNumber("0766970855")
                .reservationTime(LocalDateTime.of(2025,8,24,19,0))
                .tableNumber(5)
                .build();
        reservationService.save(first);
    }

    @When("another customer tries to book the same table at 7 PM")
    public void another_customer_tries_to_book_same_table() {
        Reservation duplicate = Reservation.builder()
                .customerName("Senuja")
                .contactNumber("0766970855")
                .reservationTime(LocalDateTime.of(2025,8,24,19,0))
                .tableNumber(5)
                .build();
        try {
            reservationService.save(duplicate);
        } catch (Exception e) {
            caughtException = e;
        }
    }

    @Then("the system should reject the reservation")
    public void system_should_reject_reservation() {
        assertNotNull(caughtException);
        assertTrue(caughtException instanceof IllegalArgumentException);
        assertEquals("Table already booked at this time.",caughtException.getMessage());

    }
}
