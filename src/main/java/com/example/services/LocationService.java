package com.example.services;

import com.example.models.Location;
import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> getAllLocations();
    Optional<Location> getLocationById(Long id);
    Optional<Location> getLocationByCode(String code);
    Location saveLocation(Location location);
    void deleteLocation(Long id);
}