package com.lhbnt.ticketreservation.service.impl;

import com.lhbnt.ticketreservation.config.JWTTokenUtil;
import com.lhbnt.ticketreservation.config.Messages;
import com.lhbnt.ticketreservation.dto.AuthDto;
import com.lhbnt.ticketreservation.dto.LoginResponse;
import com.lhbnt.ticketreservation.dto.UserDto;
import com.lhbnt.ticketreservation.entity.Role;
import com.lhbnt.ticketreservation.entity.User;
import com.lhbnt.ticketreservation.entity.enumeration.SystemRole;
import com.lhbnt.ticketreservation.exception.InvalidRequestException;
import com.lhbnt.ticketreservation.exception.UnauthorizedException;
import com.lhbnt.ticketreservation.repository.RoleRepository;
import com.lhbnt.ticketreservation.repository.UserRepository;
import com.lhbnt.ticketreservation.service.UserService;
import com.lhbnt.ticketreservation.service.mapping.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JWTTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(AuthDto authDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(authDto.getEmail()).orElseThrow(
                () -> new UnauthorizedException(Messages.EMAIL_NOT_FOUND)
        );
        return new LoginResponse(userMapper.toDto(user), jwtTokenUtil.generateToken(user.getUsername()));
    }

    @Override
    @Transactional
    public UserDto register(AuthDto authDto) {
        var isExisted = userRepository.existsByEmail(authDto.getEmail());
        if (isExisted) {
            throw new InvalidRequestException(Messages.EMAIL_ALREADY_EXISTS);
        }
        Role userRole = roleRepository.findByRoleName(SystemRole.USER)
                .orElseThrow(() -> new InvalidRequestException(Messages.ROLE_NOT_FOUND));
        User user = User.builder()
                .email(authDto.getEmail())
                .username("LE VAN PHU")
                .password(passwordEncoder.encode(authDto.getPassword()))
                .roles(new HashSet<>(Collections.singleton(userRole)))
                .build();
        return userMapper.toDto(userRepository.save(user));
    }
}
