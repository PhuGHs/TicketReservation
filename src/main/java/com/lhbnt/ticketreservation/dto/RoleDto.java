package com.lhbnt.ticketreservation.dto;

import com.lhbnt.ticketreservation.entity.enumeration.SystemRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleDto {
    private Long id;
    private SystemRole roleName;
}
