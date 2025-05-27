package com.lhbnt.ticketreservation.entity;

import com.lhbnt.ticketreservation.entity.enumeration.SystemRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "username", length = 150, nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDate createdDate = LocalDate.now();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
