package com.lhbnt.ticketreservation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "version")
    private String version;
}
