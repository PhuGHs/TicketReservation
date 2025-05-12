package com.lhbnt.ticketreservation.controller;

import com.lhbnt.ticketreservation.dto.AuthDto;
import com.lhbnt.ticketreservation.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthDto authDto) {
        return ResponseEntity.ok(userService.login(authDto));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthDto authDto) {
        return ResponseEntity.ok(userService.register(authDto));
    }
}
