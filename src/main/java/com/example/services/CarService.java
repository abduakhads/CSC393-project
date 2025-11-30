package com.example.services;

import com.example.models.Car;
import com.example.models.TransmissionType;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;


public interface CarService {
    List<Car> getAllCars();
    Optional<Car> getCarById(Long id);
    Optional<Car> getCarByBarcode(String barcode);
    Car saveCar(Car car);
    boolean deleteCar(String barcode);


    List<Car> searchAvailableCars(String pickupLocationCode,
                                  LocalDateTime startDate,
                                  LocalDateTime endDate,
                                  String category,
                                  TransmissionType transmissionType,
                                  Double minPrice,
                                  Double maxPrice,
                                  Integer minSeats);
}