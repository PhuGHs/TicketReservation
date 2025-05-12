package com.lhbnt.ticketreservation.service.mapping;

import com.lhbnt.ticketreservation.dto.UserDto;
import com.lhbnt.ticketreservation.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper extends BaseMapper<User, UserDto> {
}
