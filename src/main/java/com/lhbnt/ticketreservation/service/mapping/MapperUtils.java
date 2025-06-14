package com.lhbnt.ticketreservation.service.mapping;


import com.lhbnt.ticketreservation.dto.PaginationDTO;
import org.springframework.data.domain.Page;

public class MapperUtils {
    public static <T> PaginationDTO<T> toPaginationDTO(Page<T> page) {
        return PaginationDTO.<T>builder()
                .data(page.getContent())
                .total(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getNumber())
                .size(page.getSize())
                .build();
    }
}
