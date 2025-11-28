package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.models.Car;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByBarcode(String barcode);
}
