package com.lhbnt.ticketreservation.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private UserDto user;
    private String accessToken;
}
