package com.lhbnt.ticketreservation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "auditoriums")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "capacity")
    private int capacity;

    @OneToMany(mappedBy = "auditorium", orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(mappedBy = "auditorium", orphanRemoval = true)
    private List<Screening> auditoriums = new ArrayList<>();
}
