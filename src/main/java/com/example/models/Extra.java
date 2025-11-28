package com.example.models;

import java.math.BigDecimal;
import java.util.List;

public class Extra {
    private Long id;
    private String name;
    private BigDecimal price;

    //rel
    private List<Reservation> reservations;

    // constructors
    public Extra() {}

    public Extra(Long id, String name, BigDecimal price, List<Reservation> reservations) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.reservations = reservations;
    }

    // getters && setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
