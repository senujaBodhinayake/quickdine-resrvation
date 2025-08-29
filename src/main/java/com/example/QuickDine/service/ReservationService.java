package com.example.QuickDine.service;

import com.example.QuickDine.model.Reservation;
import com.example.QuickDine.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    public Reservation save(Reservation reservation){
        boolean isTableTaken = reservationRepository.existsByTableNumberAndReservationTime(
                reservation.getTableNumber(),
                reservation.getReservationTime()
        );

        if (isTableTaken){
            throw new IllegalArgumentException("Table already booked at this time.");
        }
        return reservationRepository.save(reservation);
    }
    public List<Reservation> getAll(){
        return  reservationRepository.findAll();
    }
}
