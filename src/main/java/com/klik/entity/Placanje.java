package com.klik.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "placanja")
public class Placanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placanjeId;

    @ManyToOne
    @JoinColumn(name = "korisnik_id", nullable = false)
    private Korisnik korisnik;

    @ManyToOne
    @JoinColumn(name = "rezervacija_id", nullable = false)
    private Rezervacija rezervacija;

    private Double iznos;
    private LocalDateTime datum;

    public Long getPlacanjeId() {
        return placanjeId;
    }

    public void setPlacanjeId(Long placanjeId) {
        this.placanjeId = placanjeId;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    // Getteri i setteri
}
