package com.lhbnt.ticketreservation.service;

import com.lhbnt.ticketreservation.dto.MovieCreateDTO;
import com.lhbnt.ticketreservation.dto.MovieDTO;
import com.lhbnt.ticketreservation.dto.PaginationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public interface MovieService {
    MovieDTO uploadMovieImages(UUID movieId, List<MultipartFile> images);
    MovieDTO createMovie(MovieCreateDTO movieCreateDTO);
    PaginationDTO<MovieDTO> getMovies(Map<String, String> filters, Pageable pageable);
    MovieDTO getMovie(UUID id);
}
