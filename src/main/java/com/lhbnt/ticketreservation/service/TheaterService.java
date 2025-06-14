package com.lhbnt.ticketreservation.service;

import com.lhbnt.ticketreservation.dto.PaginationDTO;
import com.lhbnt.ticketreservation.dto.TheaterCreateDTO;
import com.lhbnt.ticketreservation.dto.TheaterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public interface TheaterService {
    TheaterDTO createTheater(TheaterCreateDTO theaterCreateDTO);
    TheaterDTO getTheater(UUID id);
    PaginationDTO<TheaterDTO> getAllTheaters(Map<String, String> filters, Pageable pageable);
    TheaterDTO updateTheater(UUID id, TheaterCreateDTO theaterCreateDTO);
}
