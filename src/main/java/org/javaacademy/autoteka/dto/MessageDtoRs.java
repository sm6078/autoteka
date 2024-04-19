package org.javaacademy.autoteka.dto;


import lombok.Data;
import lombok.NonNull;

@Data
public class MessageDtoRs {
    @NonNull
    private final String message;
}
