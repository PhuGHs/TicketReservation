package com.lhbnt.ticketreservation.service.mapping;

import com.lhbnt.ticketreservation.dto.RoleDto;
import com.lhbnt.ticketreservation.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<Role, RoleDto> {
}
