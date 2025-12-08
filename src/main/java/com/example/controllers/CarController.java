package com.example.controllers;

import com.example.dto.CarDTO;
import com.example.models.Car;
import com.example.models.TransmissionType;
import com.example.services.CarService;
import com.example.mappers.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    // serv 1: search cars
    @GetMapping("/search")
    public ResponseEntity<List<CarDTO>> searchAvailableCars(
            @RequestParam String pickupLocation, // "pickup location (required)"
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime pickupDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dropoffDate,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) TransmissionType transmissionType,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer minSeats
    ) {
        List<Car> cars = carService.searchAvailableCars(
                pickupLocation, pickupDate, dropoffDate,
                category, transmissionType, minPrice, maxPrice, minSeats
        );

        // 404 if no available cars
        if (cars.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carMapper.toDTOList(cars));
    }

    // serv 7: delete car
    @DeleteMapping("/{barcode}")
    public ResponseEntity<Void> deleteCar(@PathVariable String barcode) {
        Optional<Car> carOpt = carService.getCarByBarcode(barcode);
        if (carOpt.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 - Not Found
        }

        boolean deleted = carService.deleteCar(barcode);

        if (deleted) {
            return ResponseEntity.ok().build(); // 200 - OK
        } else {
            // 406 – Not Acceptable – if car is used in a reservation
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    // for debug
    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return ResponseEntity.ok(carMapper.toDTOList(carService.getAllCars()));
    }
}