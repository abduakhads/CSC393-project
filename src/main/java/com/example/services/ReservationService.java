package com.example.services;

import com.example.models.Reservation;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> getAllReservations();
    Optional<Reservation> getReservationByNumber(String reservationNumber);
    Reservation createReservation(Reservation reservation);
    boolean cancelReservation(String reservationNumber);
}