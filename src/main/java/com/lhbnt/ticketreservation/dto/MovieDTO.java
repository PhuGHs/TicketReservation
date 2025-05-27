package com.lhbnt.ticketreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieDTO {
    private UUID id;
    private String title;
    private String description;
    private int duration;
    private String genre;
    private List<String> imageUrls;
}
