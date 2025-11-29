package com.example.services;

import com.example.models.Car;
import com.example.models.TransmissionType;
import com.example.repositories.CarRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Optional<Car> getCarByBarcode(String barcode) {
        return carRepository.findByBarcode(barcode);
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public boolean deleteCar(String barcode) {
        Optional<Car> carOpt = carRepository.findByBarcode(barcode);
        if (carOpt.isEmpty()) {
            return false;
        }
        Car car = carOpt.get();

        if (car.getReservations() != null && !car.getReservations().isEmpty()) {
            return false; // Cannot delete, has history
        }

        carRepository.delete(car);
        return true;
    }

    @Override
    public List<Car> searchAvailableCars(String pickupLocationCode,
                                         LocalDateTime startDate,
                                         LocalDateTime endDate,
                                         String category,
                                         TransmissionType transmissionType,
                                         Double minPrice,
                                         Double maxPrice,
                                         Integer minSeats) {
        return carRepository.searchCars(pickupLocationCode,
                                        startDate,
                                        endDate,
                                        category,
                                        transmissionType,
                                        minPrice,
                                        maxPrice,
                                        minSeats);
    }
}