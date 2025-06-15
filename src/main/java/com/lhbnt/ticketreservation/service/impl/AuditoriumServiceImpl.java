package com.lhbnt.ticketreservation.service.impl;

import com.lhbnt.ticketreservation.config.Messages;
import com.lhbnt.ticketreservation.dto.AuditoriumCreateDTO;
import com.lhbnt.ticketreservation.dto.AuditoriumDTO;
import com.lhbnt.ticketreservation.dto.AuditoriumUpdateDTO;
import com.lhbnt.ticketreservation.entity.Auditorium;
import com.lhbnt.ticketreservation.entity.Theater;
import com.lhbnt.ticketreservation.exception.ResourceNotFoundException;
import com.lhbnt.ticketreservation.repository.AuditoriumRepository;
import com.lhbnt.ticketreservation.repository.TheaterRepository;
import com.lhbnt.ticketreservation.service.AuditoriumService;
import com.lhbnt.ticketreservation.service.mapping.AuditoriumMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditoriumServiceImpl implements AuditoriumService {
    private final TheaterRepository theaterRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final AuditoriumMapper auditoriumMapper;
    @Override
    @Transactional
    public AuditoriumDTO create(AuditoriumCreateDTO auditoriumCreateDTO) {
        Theater theater = theaterRepository.findById(auditoriumCreateDTO.getTheaterId())
                .orElseThrow(() -> new ResourceNotFoundException(Messages.THEATER_NOT_FOUND));
        Auditorium auditorium = Auditorium.builder()
                .name(auditoriumCreateDTO.getName())
                .capacity(auditoriumCreateDTO.getCapacity())
                .theater(theater)
                .build();
        return this.auditoriumMapper.toDto(auditoriumRepository.save(auditorium));
    }

    @Override
    public AuditoriumDTO getById(UUID id) {
        Auditorium auditorium = auditoriumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Messages.AUDITORIUM_NOT_FOUND));
        return this.auditoriumMapper.toDto(auditorium);
    }

    @Override
    @Transactional
    public AuditoriumDTO update(AuditoriumUpdateDTO auditoriumDTO) {
        Auditorium auditorium = auditoriumRepository.findById(auditoriumDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(Messages.AUDITORIUM_NOT_FOUND));
        auditorium.setName(auditoriumDTO.getName());
        auditorium.setCapacity(auditoriumDTO.getCapacity());
        if (!auditorium.getTheater().getId().equals(auditoriumDTO.getTheaterId())) {
            Theater theater = theaterRepository.findById(auditoriumDTO.getTheaterId())
                    .orElseThrow(() -> new ResourceNotFoundException(Messages.THEATER_NOT_FOUND));
            auditorium.setTheater(theater);
        }
        return this.auditoriumMapper.toDto(auditoriumRepository.save(auditorium));
    }

    @Override
    public void deleteById(UUID id) {
        int deleted = this.auditoriumRepository.removeById(id);
        if (deleted == 0) throw new ResourceNotFoundException(Messages.AUDITORIUM_NOT_FOUND);
    }

    @Override
    public List<AuditoriumDTO> getAll() {
        return List.of();
    }
}
