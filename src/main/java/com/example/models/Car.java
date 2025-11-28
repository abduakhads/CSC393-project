package com.example.models;

import java.math.BigDecimal;
import java.util.List;

public class Car {
    private Long id;
    private String barcode;
    private String licensePlateNumber;
    private Integer numberOfSeats;
    private String brand;
    private String model;
    private Double mileage;
    private BigDecimal dailyPrice;
    private String category;

    // enum feilds
    private CarStatus status;
    private TransmissionType transmissionType;

    // rel
    private Location location;
    private List<Reservation> reservations;

    // constructors
    public Car() {

    }

    public Car(Long id, String barcode, String licensePlateNumber, Integer numberOfSeats, String brand, String model, Double mileage, BigDecimal dailyPrice, String category, CarStatus status, TransmissionType transmissionType, Location location, List<Reservation> reservations) {
        this.id = id;
        this.barcode = barcode;
        this.licensePlateNumber = licensePlateNumber;
        this.numberOfSeats = numberOfSeats;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.dailyPrice = dailyPrice;
        this.category = category;
        this.status = status;
        this.transmissionType = transmissionType;
        this.location = location;
        this.reservations = reservations;
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(BigDecimal dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
