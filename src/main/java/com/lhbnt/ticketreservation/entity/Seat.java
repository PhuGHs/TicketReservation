package com.lhbnt.ticketreservation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    @Column(name = "row", nullable = false)
    private String row;

    @Column(name = "number", nullable = false)
    private Integer number;

    @ManyToMany(mappedBy = "seats")
    private List<Booking> bookings = new ArrayList<Booking>();
}
