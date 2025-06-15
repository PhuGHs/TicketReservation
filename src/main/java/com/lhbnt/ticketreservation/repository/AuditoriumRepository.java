package com.lhbnt.ticketreservation.repository;

import com.lhbnt.ticketreservation.entity.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, UUID>, JpaSpecificationExecutor<Auditorium> {
    @Modifying
    @Query("delete from Auditorium a where a.id = :id")
    int removeById(@Param("id") UUID id);
}
