package com.example.models;
import java.util.List;


public class Location {
    private Long id;
    private String code;
    private String name;

    // relationships
    private List<Car> carsAtLocation;
    private List<Reservation> pickupsFrom;
    private List<Reservation> dropoffsTo;

    // constructors
    public Location() {}

    public Location(Long id, String code, String name, List<Car> carsAtLocation, List<Reservation> pickupsFrom, List<Reservation> dropoffsTo) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.carsAtLocation = carsAtLocation;
        this.pickupsFrom = pickupsFrom;
        this.dropoffsTo = dropoffsTo;
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCarsAtLocation() {
        return carsAtLocation;
    }

    public void setCarsAtLocation(List<Car> carsAtLocation) {
        this.carsAtLocation = carsAtLocation;
    }

    public List<Reservation> getPickupsFrom() {
        return pickupsFrom;
    }

    public void setPickupsFrom(List<Reservation> pickupsFrom) {
        this.pickupsFrom = pickupsFrom;
    }

    public List<Reservation> getDropoffsTo() {
        return dropoffsTo;
    }

    public void setDropoffsTo(List<Reservation> dropoffsTo) {
        this.dropoffsTo = dropoffsTo;
    }
}
