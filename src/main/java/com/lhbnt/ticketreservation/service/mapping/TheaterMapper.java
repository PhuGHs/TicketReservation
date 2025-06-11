package com.lhbnt.ticketreservation.service.mapping;

import com.lhbnt.ticketreservation.dto.TheaterDTO;
import com.lhbnt.ticketreservation.entity.Theater;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TheaterMapper extends BaseMapper<Theater, TheaterDTO> {
}
