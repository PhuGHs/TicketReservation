package com.lhbnt.ticketreservation.dto;


import com.lhbnt.ticketreservation.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriumDTO {
    private UUID id;
    private TheaterDTO theater;
    private String name;
    private int capacity;
}
