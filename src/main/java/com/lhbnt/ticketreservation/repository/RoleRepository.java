package com.lhbnt.ticketreservation.repository;

import com.lhbnt.ticketreservation.entity.Role;
import com.lhbnt.ticketreservation.entity.enumeration.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(SystemRole name);
}
