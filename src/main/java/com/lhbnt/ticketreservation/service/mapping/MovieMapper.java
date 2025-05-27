package com.lhbnt.ticketreservation.service.mapping;

import com.lhbnt.ticketreservation.dto.MovieDTO;
import com.lhbnt.ticketreservation.entity.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper extends BaseMapper<Movie, MovieDTO> {
}
