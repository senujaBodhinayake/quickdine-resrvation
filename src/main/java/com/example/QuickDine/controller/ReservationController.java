package com.example.QuickDine.controller;

import com.example.QuickDine.model.Reservation;
import com.example.QuickDine.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation)
    {
        return  reservationService.save(reservation);
    }
    @GetMapping
    public List<Reservation> getReservations() {
        return reservationService.getAll();
    }
}
