package com.example.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ExtraDTO {
    private Long id;
    private String name;
    private BigDecimal price;
}