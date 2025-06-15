package com.lhbnt.ticketreservation.service.impl;

import com.lhbnt.ticketreservation.config.Messages;
import com.lhbnt.ticketreservation.dto.PaginationDTO;
import com.lhbnt.ticketreservation.dto.TheaterCreateDTO;
import com.lhbnt.ticketreservation.dto.TheaterDTO;
import com.lhbnt.ticketreservation.entity.Movie;
import com.lhbnt.ticketreservation.entity.Theater;
import com.lhbnt.ticketreservation.exception.ResourceNotFoundException;
import com.lhbnt.ticketreservation.repository.TheaterRepository;
import com.lhbnt.ticketreservation.service.TheaterService;
import com.lhbnt.ticketreservation.service.mapping.MapperUtils;
import com.lhbnt.ticketreservation.service.mapping.TheaterMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TheaterServiceImpl implements TheaterService {
    private static final Logger log = LoggerFactory.getLogger(TheaterServiceImpl.class);
    private final TheaterRepository theaterRepository;
    private final TheaterMapper theaterMapper;

    @Override
    public TheaterDTO createTheater(TheaterCreateDTO theaterCreateDTO) {
        Theater theater = Theater.builder()
                .name(theaterCreateDTO.getName())
                .location(theaterCreateDTO.getLocation())
                .build();
        return theaterMapper.toDto(theaterRepository.save(theater));
    }

    @Override
    public TheaterDTO getTheater(UUID id) {
        var theater = theaterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Messages.THEATER_NOT_FOUND));
        return theaterMapper.toDto(theater);
    }

    @Override
    public PaginationDTO<TheaterDTO> getAllTheaters(Map<String, String> filters, Pageable pageable) {
        Specification<Theater> spec = getTheatersByFilter(filters);
        return MapperUtils.toPaginationDTO(theaterRepository.findAll(spec, pageable).map(theaterMapper::toDto));
    }

    @Override
    public TheaterDTO updateTheater(UUID id, TheaterCreateDTO theaterCreateDTO) {
        var theater = theaterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Messages.THEATER_NOT_FOUND));

        theater.setName(theaterCreateDTO.getName());
        theater.setLocation(theaterCreateDTO.getLocation());
        return theaterMapper.toDto(theaterRepository.save(theater));
    }

    private static Specification<Theater> getTheatersByFilter(Map<String, String> filters) {
        Specification<Theater> spec = Specification.where(null);

        for (Map.Entry<String, String> entry : filters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            switch (key) {
                case "name" -> spec = spec.and((root, query, cb) ->
                        cb.like(cb.lower(root.get("name")), "%" + value.toLowerCase() + "%")
                );
                case "location" -> spec = spec.and((root, query, cb) ->
                        cb.like(cb.lower(root.get("location")), "%" + value.toLowerCase() + "%")
                );
            }
        }

        return spec;
    }
}
