package com.lhbnt.ticketreservation.controller;

import com.lhbnt.ticketreservation.annotation.ValidImageFile;
import com.lhbnt.ticketreservation.dto.MovieCreateDTO;
import com.lhbnt.ticketreservation.dto.MovieDTO;
import com.lhbnt.ticketreservation.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MovieDTO> createMovie(
            @RequestPart(value = "dto") @Valid MovieCreateDTO dto,
            @RequestPart(value = "files", required = false) @ValidImageFile List<MultipartFile> files
    ) {
        var movie = movieService.createMovie(dto, files);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable UUID id) {
        var movie = movieService.getMovie(id);
        return ResponseEntity.ok(movie);
    }
}
