package org.javaacademy.autoteka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertDtoRs {
    private String uuid;
    private String brand;
    private String color;
    private BigDecimal price;
    private String model;
    private LocalDate postingDate;
}
