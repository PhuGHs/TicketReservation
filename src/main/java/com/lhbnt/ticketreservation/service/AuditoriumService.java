package com.lhbnt.ticketreservation.service;

import com.lhbnt.ticketreservation.dto.AuditoriumCreateDTO;
import com.lhbnt.ticketreservation.dto.AuditoriumDTO;
import com.lhbnt.ticketreservation.dto.AuditoriumUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface AuditoriumService {
    AuditoriumDTO create(AuditoriumCreateDTO auditoriumCreateDTO);
    AuditoriumDTO getById(UUID id);
    AuditoriumDTO update(AuditoriumUpdateDTO auditoriumCreateDTO);
    void deleteById(UUID id);
    List<AuditoriumDTO> getAll();
}
