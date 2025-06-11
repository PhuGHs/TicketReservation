package com.lhbnt.ticketreservation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieCreateDTO {
    @NotBlank
    @Max(value = 100)
    private String title;

    @NotBlank
    @Max(value = 500)
    private String description;

    private float duration;

    @NotBlank
    private String genre;
}
