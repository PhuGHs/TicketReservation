package com.lhbnt.ticketreservation.controller;

import com.lhbnt.ticketreservation.annotation.ValidImageFile;
import com.lhbnt.ticketreservation.dto.MovieCreateDTO;
import com.lhbnt.ticketreservation.dto.MovieDTO;
import com.lhbnt.ticketreservation.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/movies")
@RequiredArgsConstructor
@Slf4j
public class MovieController {
    private final MovieService movieService;

    @PostMapping()
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieCreateDTO movieCreateDTO) {
        log.info("Create movie: {}", movieCreateDTO);
        var movie = movieService.createMovie(movieCreateDTO);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable UUID id) {
        var movie = movieService.getMovie(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping
    public ResponseEntity<?> getMovies(@RequestParam() Map<String, String> filters, @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        var results = movieService.getMovies(filters, pageable);
        return ResponseEntity.ok(results);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> uploadMovieImages(
            @PathVariable UUID id,
            @RequestPart(value = "files") @ValidImageFile List<MultipartFile> files
    ) {
        var movie = movieService.uploadMovieImages(id, files);
        return ResponseEntity.ok(movie);
    }
}
