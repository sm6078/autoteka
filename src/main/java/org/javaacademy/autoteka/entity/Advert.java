package org.javaacademy.autoteka.entity;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Advert {
    private String uuid;
    @NonNull
    private String brand;
    @NonNull
    private String color;
    @NonNull
    private BigDecimal price;
    @NonNull
    private String model;
    @NonNull
    private LocalDate postingDate;
}
