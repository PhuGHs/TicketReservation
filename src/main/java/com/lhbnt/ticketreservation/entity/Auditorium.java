package com.lhbnt.ticketreservation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "auditoriums")
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
}
