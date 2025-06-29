package com.lhbnt.ticketreservation.controller;

import com.lhbnt.ticketreservation.dto.AuditoriumCreateDTO;
import com.lhbnt.ticketreservation.dto.AuditoriumUpdateDTO;
import com.lhbnt.ticketreservation.entity.Auditorium;
import com.lhbnt.ticketreservation.service.AuditoriumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/auditoriums")
@RequiredArgsConstructor
public class AuditoriumController {
    private final AuditoriumService auditoriumService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuditoriumById(@PathVariable("id") UUID id) {
        var result = this.auditoriumService.getById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<?> createAuditorium(@Valid @RequestBody AuditoriumCreateDTO auditorium) {
        var result = this.auditoriumService.create(auditorium);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> updateAuditorium(@Valid @RequestBody AuditoriumUpdateDTO auditorium) {
        var result = this.auditoriumService.update(auditorium);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuditoriumById(@PathVariable UUID id) {
        this.auditoriumService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
