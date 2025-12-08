package com.example.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReservationResponseDTO {
    private String reservationNumber;
    private LocalDateTime pickupDateTime;
    private LocalDateTime dropoffDateTime;

    private String pickupLocationCode;
    private String pickupLocationName;

    private String dropoffLocationCode;
    private String dropoffLocationName;

    private BigDecimal totalAmount;

    private Long memberId;
    private String memberName;
}