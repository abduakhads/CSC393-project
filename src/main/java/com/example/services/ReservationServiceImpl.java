package com.example.services;

import com.example.models.*;
import com.example.repositories.ReservationRepository;
import com.example.repositories.CarRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> getReservationByNumber(String reservationNumber) {
        return reservationRepository.findByReservationNumber(reservationNumber);
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        Car car = carRepository.findById(reservation.getCar().getId())
                .orElseThrow(() -> new RuntimeException("Araba bulunamadı!"));

        if (car.getStatus() != CarStatus.AVAILABLE) {
            throw new RuntimeException("Seçilen araç müsait değil (Not Available)!");
        }

        reservation.setReservationNumber(generateReservationNumber());

        reservation.setStatus(ReservationStatus.ACTIVE);
        reservation.setCreationDate(LocalDateTime.now());

        return reservationRepository.save(reservation);
    }

    @Override
    public boolean cancelReservation(String reservationNumber) {
        Optional<Reservation> existingRes = reservationRepository.findByReservationNumber(reservationNumber);

        if (existingRes.isPresent()) {
            Reservation reservation = existingRes.get();
            reservation.setStatus(ReservationStatus.CANCELLED);
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    private String generateReservationNumber() {
        Random random = new Random();
        int number = 10000000 + random.nextInt(90000000);
        return String.valueOf(number);
    }
}