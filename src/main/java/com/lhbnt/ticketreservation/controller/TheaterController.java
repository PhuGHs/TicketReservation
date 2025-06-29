package com.lhbnt.ticketreservation.controller;

import com.lhbnt.ticketreservation.dto.TheaterCreateDTO;
import com.lhbnt.ticketreservation.service.AuditoriumService;
import com.lhbnt.ticketreservation.service.TheaterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/theaters")
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;
    private final AuditoriumService auditoriumService;

    @PostMapping()
    ResponseEntity<?> createTheater(@Valid @RequestBody TheaterCreateDTO theaterCreateDTO) {
        var result = theaterService.createTheater(theaterCreateDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateTheater(@PathVariable UUID id, @Valid @RequestBody TheaterCreateDTO theaterCreateDTO) {
        var result = theaterService.updateTheater(id, theaterCreateDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getTheater(@PathVariable UUID id) {
        var result = theaterService.getTheater(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/auditoriums")
    ResponseEntity<?> getTheaterAuditoriums(@PathVariable UUID id) {
        var result = auditoriumService.getAll(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    ResponseEntity<?> getTheaters(@RequestParam Map<String, String> filters, @PageableDefault(size = 10) Pageable pageable) {
        var result = theaterService.getAllTheaters(filters, pageable);
        return ResponseEntity.ok(result);
    }
}
