package com.lhbnt.ticketreservation.dto;

import com.lhbnt.ticketreservation.entity.Role;
import com.lhbnt.ticketreservation.entity.enumeration.SystemRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private String password;
    private Set<RoleDto> roles;
}
