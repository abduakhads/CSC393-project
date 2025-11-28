package com.example.repositories;

import com.example.models.Extra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ExtraRepository extends JpaRepository<Extra, Long> {
    Optional<Extra> findByName(String name);
}