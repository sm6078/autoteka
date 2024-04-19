package org.javaacademy.autoteka.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.NonNull;

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
