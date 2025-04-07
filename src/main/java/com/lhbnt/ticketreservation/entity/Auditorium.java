package com.lhbnt.ticketreservation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "auditoriums")
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "capacity")
    private int capacity;
}
