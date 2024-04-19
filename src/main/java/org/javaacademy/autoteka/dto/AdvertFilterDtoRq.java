package org.javaacademy.autoteka.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdvertFilterDtoRq {
    private String brand;
    private String color;
    private BigDecimal price;
    private String model;
}
