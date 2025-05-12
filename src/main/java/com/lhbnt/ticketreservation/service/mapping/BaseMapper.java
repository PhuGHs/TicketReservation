package com.lhbnt.ticketreservation.service.mapping;

public interface BaseMapper<E, D> {
    E toEntity(D d);
    D toDto(E e);
}
