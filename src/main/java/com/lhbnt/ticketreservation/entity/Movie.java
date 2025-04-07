package com.lhbnt.ticketreservation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "poster_image")
    private String posterImage;

    @Column(name = "duration")
    private int duration;

    @Column(name = "genre")
    private String genre;
}
