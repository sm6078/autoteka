package org.javaacademy.autoteka.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertDtoRq {
    private String brand;
    private String color;
    private BigDecimal price;
    private String model;
    private LocalDate postingDate;
}
