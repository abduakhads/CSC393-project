package com.example.repositories;

import com.example.models.TransmissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.models.Car;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByBarcode(String barcode);


    // service 1 - search available cars
    @Query("SELECT c FROM Car c WHERE c.status = 'AVAILABLE' " +
            // Location - required
            "AND c.location.code = :pickupLocationCode " +

            // optionals - Category, Transmission, Price Range (Min and Max), Seats | (If null, ignore)
            "AND (:category IS NULL OR c.category = :category) " +
            "AND (:transmissionType IS NULL OR c.transmissionType = :transmissionType) " +
            "AND (:minPrice IS NULL OR c.dailyPrice >= :minPrice) " +
            "AND (:maxPrice IS NULL OR c.dailyPrice <= :maxPrice) " +
            "AND (:minSeats IS NULL OR c.numberOfSeats >= :minSeats) " +

            // Availability Check (Not booked in these dates) - required
            "AND c.id NOT IN (" +
            "  SELECT r.car.id FROM Reservation r " +
            "  WHERE r.status = 'ACTIVE' " +
            "  AND ((r.pickupDateTime BETWEEN :startDate AND :endDate) " +
            "       OR (r.dropoffDateTime BETWEEN :startDate AND :endDate) " +
            "       OR (:startDate BETWEEN r.pickupDateTime AND r.dropoffDateTime))" +
            ")")
    List<Car> searchCars(
            @Param("pickupLocationCode") String pickupLocationCode,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("category") String category,
            @Param("transmissionType") TransmissionType transmissionType,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minSeats") Integer minSeats
    );
}