package com.klik.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "tretmani")
public class Tretman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tretmanId;

    private String naziv;
    private String opis;
    private Float cijena;

    @Column(name = "kreirano")
    private LocalDateTime kreirano;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Float getCijena() {
        return cijena;
    }

    public void setCijena(Float cijena) {
        this.cijena = cijena;
    }

    public Integer getId() {
        return tretmanId;
    }

    public void setId(Integer tretmanId) {
        this.tretmanId = tretmanId;
    }
}

