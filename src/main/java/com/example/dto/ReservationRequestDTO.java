package com.example.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationRequestDTO {
    private String carBarcode;
    private Long memberId;

    private LocalDateTime pickupDateTime;
    private LocalDateTime dropoffDateTime;

    private String pickupLocationCode;
    private String dropoffLocationCode;

    private List<Long> extraIds;
}