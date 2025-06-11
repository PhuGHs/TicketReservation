package com.lhbnt.ticketreservation.service.impl;

import com.lhbnt.ticketreservation.config.Messages;
import com.lhbnt.ticketreservation.dto.MovieCreateDTO;
import com.lhbnt.ticketreservation.dto.MovieDTO;
import com.lhbnt.ticketreservation.entity.Movie;
import com.lhbnt.ticketreservation.entity.enumeration.ResourceType;
import com.lhbnt.ticketreservation.exception.ResourceNotFoundException;
import com.lhbnt.ticketreservation.repository.MovieRepository;
import com.lhbnt.ticketreservation.service.ImageService;
import com.lhbnt.ticketreservation.service.MovieService;
import com.lhbnt.ticketreservation.service.mapping.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public Page<MovieDTO> getMovies(Map<String, String> filters, Pageable pageable) {
        Specification<Movie> spec = getMovieByFilters(filters);
        Page<Movie> movies = movieRepository.findAll(spec, pageable);
        return movies.map(this.movieMapper::toDto);
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
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            filters.forEach((k, v) -> {
                switch (k) {
                    case "title" -> predicates.getExpressions().add(
                            cb.like(cb.lower(root.get("title")), "%" + v.toLowerCase() + "%")
                    );
                    case "genre" -> predicates.getExpressions().add(
                            cb.equal(root.get("genre"), v)
                    );
                    case "minDuration" -> predicates.getExpressions().add(
                            cb.greaterThanOrEqualTo(root.get("duration"), v)
                    );
                    case "maxDuration" -> predicates.getExpressions().add(
                            cb.lessThanOrEqualTo(root.get("duration"), v)
                    );
                }
            });

            return predicates;
        };
    }
}
