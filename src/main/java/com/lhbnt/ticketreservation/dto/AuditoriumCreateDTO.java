package com.lhbnt.ticketreservation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriumCreateDTO {
    @NotNull  
    private UUID theaterId;

    @NotBlank
    private String name;

    @NotNull
    private int capacity;
}
