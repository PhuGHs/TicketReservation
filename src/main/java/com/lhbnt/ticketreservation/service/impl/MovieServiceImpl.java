package com.lhbnt.ticketreservation.service.impl;

import com.lhbnt.ticketreservation.config.Messages;
import com.lhbnt.ticketreservation.dto.MovieCreateDTO;
import com.lhbnt.ticketreservation.dto.MovieDTO;
import com.lhbnt.ticketreservation.dto.PaginationDTO;
import com.lhbnt.ticketreservation.entity.Movie;
import com.lhbnt.ticketreservation.entity.enumeration.ResourceType;
import com.lhbnt.ticketreservation.exception.ResourceNotFoundException;
import com.lhbnt.ticketreservation.repository.MovieRepository;
import com.lhbnt.ticketreservation.service.ImageService;
import com.lhbnt.ticketreservation.service.MovieService;
import com.lhbnt.ticketreservation.service.mapping.MapperUtils;
import com.lhbnt.ticketreservation.service.mapping.MovieMapper;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final ImageService imageService;

    @Override
    public MovieDTO uploadMovieImages(UUID movieId, List<MultipartFile> images) {
        var movie = movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException(Messages.MOVIE_NOT_FOUND));
        List<String> urls = imageService.uploadWithResourceImages(movieId, ResourceType.MOVIE, images);
        var movieDto = movieMapper.toDto(movie);
        movieDto.setImageUrls(urls);
        return movieDto;
    }

    @Override
    @Transactional
    public MovieDTO createMovie(MovieCreateDTO movieCreateDTO) {
        Movie movie = Movie.builder()
                .title(movieCreateDTO.getTitle())
                .genre(movieCreateDTO.getGenre())
                .description(movieCreateDTO.getDescription())
                .duration(movieCreateDTO.getDuration())
                .build();
        return movieMapper.toDto(movieRepository.save(movie));
    }

    @Override
    public PaginationDTO<MovieDTO> getMovies(Map<String, String> filters, Pageable pageable) {
        Specification<Movie> spec = getMovieByFilters(filters);
        Page<Movie> movies = movieRepository.findAll(spec, pageable);
        var movieDTOList = movies.map(this.movieMapper::toDto);
        for (MovieDTO movie : movieDTOList) {
            movie.setImageUrls(
                    imageService.getMovieImages(movie.getId())
                            .stream()
                            .map(v -> imageService.getUrl(v.getId()))
                            .toList()
            );
        }
        return MapperUtils.toPaginationDTO(movieDTOList);
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

    private static Specification<Movie> getMovieByFilters(Map<String, String> filters) {
        Specification<Movie> spec = Specification.where(null);

        for (Map.Entry<String, String> entry : filters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            switch (key) {
                case "title" -> spec = spec.and((root, query, cb) ->
                        cb.like(cb.lower(root.get("title")), "%" + value.toLowerCase() + "%")
                );
                case "genre" -> spec = spec.and((root, query, cb) ->
                        cb.equal(root.get("genre"), value)
                );
                case "minDuration" -> spec = spec.and((root, query, cb) ->
                        cb.greaterThanOrEqualTo(root.get("duration"), Integer.parseInt(value))
                );
                case "maxDuration" -> spec = spec.and((root, query, cb) ->
                        cb.lessThanOrEqualTo(root.get("duration"), Integer.parseInt(value))
                );
            }
        }

        return spec;
    }
}
