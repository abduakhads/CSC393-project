package com.example.models;

import java.time.LocalDateTime;
import java.util.List;

public class Reservation {
    private Long id;
    private String reservationNumber;
    private LocalDateTime creationDate;
    private LocalDateTime pickupDateTime;
    private LocalDateTime dropoffDateTime;
    private LocalDateTime returnDate;

    // enum field
    private ReservationStatus status;

    // relationships
    private Member member;
    private Car car;
    private Location pickupLocation;
    private Location dropoffLocation;
    private List<Extra> extras;

    // constructors
    public Reservation() {}

    public Reservation(Long id, String reservationNumber, LocalDateTime creationDate, LocalDateTime pickupDateTime, LocalDateTime dropoffDateTime, LocalDateTime returnDate, ReservationStatus status, Member member, Car car, Location pickupLocation, Location dropoffLocation, List<Extra> extras) {
        this.id = id;
        this.reservationNumber = reservationNumber;
        this.creationDate = creationDate;
        this.pickupDateTime = pickupDateTime;
        this.dropoffDateTime = dropoffDateTime;
        this.returnDate = returnDate;
        this.status = status;
        this.member = member;
        this.car = car;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.extras = extras;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getPickupDateTime() {
        return pickupDateTime;
    }

    public void setPickupDateTime(LocalDateTime pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    public LocalDateTime getDropoffDateTime() {
        return dropoffDateTime;
    }

    public void setDropoffDateTime(LocalDateTime dropoffDateTime) {
        this.dropoffDateTime = dropoffDateTime;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Location getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(Location dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }
}
