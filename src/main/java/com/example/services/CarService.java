package com.example.services;

import com.example.models.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> getAllCars();
    Optional<Car> getCarById(Integer id);
    Optional<Car> getCarByBarcode(String barcode);
    Car saveCar(Car car);
    void deleteCar(Integer id);
}