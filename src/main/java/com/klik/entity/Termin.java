package com.klik.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "termini")
public class Termin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer terminId;

    private LocalDate datum;
    private LocalTime vrijeme;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "tretman_id")
    private Tretman tretman;

    @ManyToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;
}

