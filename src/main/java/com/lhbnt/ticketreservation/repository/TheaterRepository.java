package com.lhbnt.ticketreservation.repository;

import com.lhbnt.ticketreservation.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, UUID>, JpaSpecificationExecutor<Theater> {
}
