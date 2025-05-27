package com.lhbnt.ticketreservation.service.impl;

import com.lhbnt.ticketreservation.config.Messages;
import com.lhbnt.ticketreservation.dto.MovieCreateDTO;
import com.lhbnt.ticketreservation.dto.MovieDTO;
import com.lhbnt.ticketreservation.entity.Movie;
import com.lhbnt.ticketreservation.exception.ResourceNotFoundException;
import com.lhbnt.ticketreservation.repository.MovieRepository;
import com.lhbnt.ticketreservation.service.ImageService;
import com.lhbnt.ticketreservation.service.MovieService;
import com.lhbnt.ticketreservation.service.mapping.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final ImageService imageService;

    @Override
    @Transactional
    public MovieDTO createMovie(MovieCreateDTO movieCreateDTO, List<MultipartFile> files) {
        Movie movie = Movie.builder()
                .title(movieCreateDTO.getTitle())
                .genre(movieCreateDTO.getGenre())
                .description(movieCreateDTO.getDescription())
                .duration(movieCreateDTO.getDuration())
                .build();
        movie = movieRepository.save(movie);
        List<String> imageUrls = imageService.addMultipleImages(movie.getId(), files)
                .stream()
                .map(image -> imageService.getUrl(image.getId()))
                .toList();
        var movieDto = movieMapper.toDto(movie);
        movieDto.setImageUrls(imageUrls);
        return movieDto;
    }

    @Override
    public List<MovieDTO> getMovies(Map<String, String> filters) {
        return List.of();
    }

    @Override
    public MovieDTO getMovie(UUID id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (Objects.isNull(movie)) throw new ResourceNotFoundException(Messages.MOVIE_NOT_FOUND);
        MovieDTO movieDTO = movieMapper.toDto(movie);
        movieDTO.setImageUrls(
                imageService.getMovieImages(movie.getId())
                        .stream()
                        .map(v -> imageService.getUrl(v.getId()))
                        .toList()
        );
        return movieDTO;
    }
}
