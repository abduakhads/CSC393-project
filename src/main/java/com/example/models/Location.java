package com.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<Car> carsAtLocation;

    @OneToMany(mappedBy = "pickupLocation", fetch = FetchType.LAZY)
    private List<Reservation> pickupsFrom;

    @OneToMany(mappedBy = "dropoffLocation", fetch = FetchType.LAZY)
    private List<Reservation> dropoffsTo;
}