package com.klik.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rezervacije")
public class Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rezervacijaId;

    @ManyToOne
    @JoinColumn(name = "korisnik_id", nullable = false)
    private Korisnik korisnik;

    @ManyToOne
    @JoinColumn(name = "tretman_id", nullable = false)
    private Tretman tretman;

    private LocalDateTime datumRezervacije;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime kreirano;

    @Transient
    private boolean placeno;

    public boolean isPlaceno() {
        return placeno;
    }

    public void setPlaceno(boolean placeno) {
        this.placeno = placeno;
    }

    public Long getRezervacijaId() {
        return rezervacijaId;
    }

    public void setRezervacijaId(Long rezervacijaId) {
        this.rezervacijaId = rezervacijaId;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Tretman getTretman() {
        return tretman;
    }

    public void setTretman(Tretman tretman) {
        this.tretman = tretman;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(LocalDateTime datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    public LocalDateTime getKreirano() {
        return kreirano;
    }

    public void setKreirano(LocalDateTime kreirano) {
        this.kreirano = kreirano;
    }

    public enum Status {
        NA_CEKANJU,
        POTVRDJENO,
        OTKAZANO
    }

}
