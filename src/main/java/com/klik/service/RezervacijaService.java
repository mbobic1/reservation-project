package com.klik.service;

import com.klik.dto.RezervacijaRequestDTO;
import com.klik.entity.Rezervacija;

import java.util.List;

public interface RezervacijaService {
    List<Rezervacija> getAll();
    Rezervacija create(RezervacijaRequestDTO dto);
    List<Rezervacija> getByKorisnik(Integer korisnikId);
    Rezervacija updateStatus(Long id, Rezervacija.Status status);
    void delete(Long id);
    Rezervacija otkazi(Long id);
}
