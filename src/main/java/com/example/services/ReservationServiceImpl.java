package com.example.services;

import com.example.models.*;
//import com.example.repositories.ReservationRepository;
//import com.example.repositories.CarRepository;
import com.example.repositories.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final ExtraRepository extraRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> getReservationByNumber(String reservationNumber) {
        return reservationRepository.findByReservationNumber(reservationNumber);
    }

    @Override
    @Transactional
    public Reservation createReservation(Reservation reservation) {
        Car car = carRepository.findById(reservation.getCar().getId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (car.getStatus() != CarStatus.AVAILABLE) {
            throw new RuntimeException("Car is not AVAILABLE");
        }

        reservation.setReservationNumber(generateReservationNumber());

        reservation.setStatus(ReservationStatus.ACTIVE);
        reservation.setCreationDate(LocalDateTime.now());

        // checkin the car is linked correctly
        reservation.setCar(car);

        // Ensuring list is not null on create
        if (reservation.getExtras() == null) {
            reservation.setExtras(new ArrayList<>());
        }

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

    @Override
    public List<Reservation> getActiveReservations() {
        return reservationRepository.findAllByStatus(ReservationStatus.ACTIVE);
    }

    @Override
    @Transactional
    public boolean returnCar(String reservationNumber) {
        Optional<Reservation> resOpt = reservationRepository.findByReservationNumber(reservationNumber);
        if (resOpt.isEmpty()) return false;

        Reservation res = resOpt.get();
        res.setStatus(ReservationStatus.COMPLETED);
        res.setReturnDate(LocalDateTime.now());

        // updating car location if dropoff is different from current car location
        Car car = res.getCar();
        if (!res.getDropoffLocation().getCode().equals(car.getLocation().getCode())) {
            car.setLocation(res.getDropoffLocation());
            carRepository.save(car);
        }

        reservationRepository.save(res);
        return true;
    }

    @Override
    @Transactional
    public boolean addExtraToReservation(String reservationNumber, Long extraId) {
        Optional<Reservation> resOpt = reservationRepository.findByReservationNumber(reservationNumber);
        Optional<Extra> extraOpt = extraRepository.findById(extraId);

        if (resOpt.isEmpty() || extraOpt.isEmpty()) return false;

        Reservation res = resOpt.get();
        Extra extra = extraOpt.get();

        // Initializing list if it is null
        if (res.getExtras() == null) {
            res.setExtras(new ArrayList<>());
        }

        // checking if already added
        if (res.getExtras().contains(extra)) return false;

        res.getExtras().add(extra);
        reservationRepository.save(res);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteReservation(String reservationNumber) {
        Optional<Reservation> resOpt = reservationRepository.findByReservationNumber(reservationNumber);
        if (resOpt.isEmpty()) return false;

        Reservation res = resOpt.get();

        // disassociating Car and Member before delete (to prevent Cascade remove)
        res.setCar(null);
        res.setMember(null);
        res.setPickupLocation(null);
        res.setDropoffLocation(null);

        // Null check before clear
        if (res.getExtras() != null) {
            res.getExtras().clear();
        }

        reservationRepository.save(res);
        reservationRepository.delete(res);
        return true;
    }
}