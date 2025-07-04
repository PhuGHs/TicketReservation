package com.lhbnt.ticketreservation.entity;

import com.lhbnt.ticketreservation.entity.enumeration.SystemRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "system_roles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SystemRole roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
