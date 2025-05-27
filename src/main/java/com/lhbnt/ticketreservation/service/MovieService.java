package com.lhbnt.ticketreservation.service;

import com.lhbnt.ticketreservation.dto.MovieCreateDTO;
import com.lhbnt.ticketreservation.dto.MovieDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public interface MovieService {
    MovieDTO createMovie(MovieCreateDTO movieCreateDTO, List<MultipartFile> files);
    List<MovieDTO> getMovies(Map<String, String> filters);
    MovieDTO getMovie(UUID id);
}
