package com.lhbnt.ticketreservation.repository;

import com.lhbnt.ticketreservation.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, UUID> {
}
