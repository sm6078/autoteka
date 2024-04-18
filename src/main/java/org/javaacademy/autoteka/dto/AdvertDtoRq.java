package org.javaacademy.autoteka.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AdvertDtoRq {
    private String brand;
    private String color;
    private BigDecimal price;
    private String model;
    private LocalDate postingDate;
}
