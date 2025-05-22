package com.lhbnt.ticketreservation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public class MovieCreateDTO {
    @NotBlank
    @Max(value = 100)
    private String title;

    @NotBlank
    @Max(value = 500)
    private String description;
}
