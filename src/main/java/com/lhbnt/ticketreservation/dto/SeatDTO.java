package com.lhbnt.ticketreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeatDTO {
    private UUID id;
    private UUID auditoriumId;
    private String row;
    private Integer number;
    private boolean isAvailable;
}
