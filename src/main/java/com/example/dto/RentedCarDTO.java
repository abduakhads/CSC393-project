package com.example.dto;

import com.example.models.TransmissionType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RentedCarDTO {
    private String brand;
    private String model;
    private String category;
    private TransmissionType transmissionType;
    private String barcode;
    private String reservationNumber;
    private String memberName;
    private LocalDateTime dropoffDateTime;
    private String dropoffLocationCode;
    private Long reservationDayCount;
}