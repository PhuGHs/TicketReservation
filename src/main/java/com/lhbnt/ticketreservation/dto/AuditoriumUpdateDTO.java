package com.lhbnt.ticketreservation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriumUpdateDTO {
    @NotBlank
    private UUID id;

    @NotBlank
    private UUID theaterId;

    @NotBlank
    private String name;

    @NotBlank
    private int capacity;
}
