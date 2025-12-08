package com.example.controllers;

import com.example.dto.*;
import com.example.mappers.ReservationMapper;
import com.example.models.Car;
import com.example.models.Location;
import com.example.models.Member;
import com.example.models.Reservation;
import com.example.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final CarService carService;
    private final MemberService memberService;
    private final LocationService locationService;
    private final ReservationMapper reservationMapper;

    // serv 2: make reservation
    @PostMapping
    public ResponseEntity<?> makeReservation(@RequestBody ReservationRequestDTO request) {
        Optional<Car> carOpt = carService.getCarByBarcode(request.getCarBarcode());
        if (carOpt.isEmpty()) return ResponseEntity.notFound().build();

        Car car = carOpt.get();
        if (car.getStatus() != com.example.models.CarStatus.AVAILABLE) {
            //business rule says 406, but 206 in code list, we are putting NOT_ACCEPTABLE 406 as it is more appropriate.
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        Reservation res = new Reservation();
        res.setCar(car);

        Optional<Member> memOpt = memberService.getMemberById(request.getMemberId());
        if (memOpt.isEmpty()) return ResponseEntity.notFound().build();
        res.setMember(memOpt.get());

        Optional<Location> pickupLoc = locationService.getLocationByCode(request.getPickupLocationCode());
        Optional<Location> dropoffLoc = locationService.getLocationByCode(request.getDropoffLocationCode());

        if (pickupLoc.isEmpty() || dropoffLoc.isEmpty()) return ResponseEntity.notFound().build();

        res.setPickupLocation(pickupLoc.get());
        res.setDropoffLocation(dropoffLoc.get());

        res.setPickupDateTime(request.getPickupDateTime());
        res.setDropoffDateTime(request.getDropoffDateTime());

        try {
            Reservation saved = reservationService.createReservation(res);

            if (request.getExtraIds() != null) {
                for (Long extraId : request.getExtraIds()) {
                    reservationService.addExtraToReservation(saved.getReservationNumber(), extraId);
                }
            }

            return ResponseEntity.ok(reservationMapper.toResponseDTO(saved));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
        }
    }

    // serv 3: get all rented cars
    @GetMapping("/rented")
    public ResponseEntity<List<RentedCarDTO>> getAllRentedCars() {
        List<Reservation> activeReservations = reservationService.getActiveReservations();
        if (activeReservations.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservationMapper.toRentedCarDTOList(activeReservations));
    }

    // serv 4: add extra
    @PostMapping("/{reservationNumber}/extras/{extraId}")
    public ResponseEntity<Boolean> addExtra(@PathVariable String reservationNumber, @PathVariable Long extraId) {
        try {
            boolean success = reservationService.addExtraToReservation(reservationNumber, extraId);
            if (success) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // serv 5: return car
    @PostMapping("/{reservationNumber}/return")
    public ResponseEntity<Boolean> returnCar(@PathVariable String reservationNumber) {
        try {
            boolean success = reservationService.returnCar(reservationNumber);
            if (success) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // serv 6: cancel reservation
    @PostMapping("/{reservationNumber}/cancel")
    public ResponseEntity<Boolean> cancelReservation(@PathVariable String reservationNumber) {
        try {
            boolean success = reservationService.cancelReservation(reservationNumber);
            if (success) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // serv 8: delete reservation
    @DeleteMapping("/{reservationNumber}")
    public ResponseEntity<Boolean> deleteReservation(@PathVariable String reservationNumber) {
        try {
            boolean success = reservationService.deleteReservation(reservationNumber);
            if (success) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}