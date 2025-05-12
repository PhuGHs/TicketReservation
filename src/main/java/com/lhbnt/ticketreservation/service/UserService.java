package com.lhbnt.ticketreservation.service;

import com.lhbnt.ticketreservation.dto.AuthDto;
import com.lhbnt.ticketreservation.dto.LoginResponse;
import com.lhbnt.ticketreservation.dto.UserDto;

public interface UserService {
    LoginResponse login(AuthDto authDto);
    UserDto register(AuthDto authDto);
}
