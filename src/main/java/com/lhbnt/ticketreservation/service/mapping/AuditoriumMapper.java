package com.lhbnt.ticketreservation.service.mapping;

import com.lhbnt.ticketreservation.dto.AuditoriumDTO;
import com.lhbnt.ticketreservation.entity.Auditorium;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TheaterMapper.class})
public interface AuditoriumMapper extends BaseMapper<Auditorium, AuditoriumDTO>{
}
