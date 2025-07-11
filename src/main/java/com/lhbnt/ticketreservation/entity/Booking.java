package com.lhbnt.ticketreservation.entity;

import com.lhbnt.ticketreservation.entity.enumeration.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "booking_seats",
            joinColumns = { @JoinColumn(name = "booking_id")},
            inverseJoinColumns = { @JoinColumn(name = "seat_id")}
    )
    private List<Seat> seats = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "expires_at")
    private Instant expiresAt;
}
