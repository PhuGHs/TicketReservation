package com.lhbnt.ticketreservation.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationDTO<T> {
    private List<T> data;
    private int page;
    private int size;
    private long total;
    private int totalPages;
}
