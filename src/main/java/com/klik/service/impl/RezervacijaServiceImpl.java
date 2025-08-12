package com.klik.service.impl;

import com.klik.dto.RezervacijaRequestDTO;
import com.klik.entity.*;
import com.klik.repository.*;
import com.klik.service.RezervacijaService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RezervacijaServiceImpl implements RezervacijaService {

    private final RezervacijaRepository rezervacijaRepo;
    private final KorisnikRepository korisnikRepo;
    private final TretmanRepository tretmanRepo;
    private final PlacanjeRepository placanjeRepo;

    public RezervacijaServiceImpl(RezervacijaRepository rezervacijaRepo, KorisnikRepository korisnikRepo, TretmanRepository tretmanRepo, PlacanjeRepository placanjeRepo) {
        this.rezervacijaRepo = rezervacijaRepo;
        this.korisnikRepo = korisnikRepo;
        this.tretmanRepo = tretmanRepo;
        this.placanjeRepo = placanjeRepo;
    }

    @Override
    public List<Rezervacija> getAll() {
        List<Rezervacija> rezervacije = rezervacijaRepo.findAll();
        rezervacije.forEach(r -> {
            boolean isPlaceno = placanjeRepo.existsByRezervacija_RezervacijaId(r.getRezervacijaId());
            r.setPlaceno(isPlaceno);
        });
        return rezervacije;
    }
    @Override
    public Rezervacija create(RezervacijaRequestDTO dto) {
        Rezervacija r = new Rezervacija();
        r.setKorisnik(korisnikRepo.findById(Math.toIntExact(dto.korisnikId)).orElseThrow());
        r.setTretman(tretmanRepo.findById(Math.toIntExact(dto.tretmanId)).orElseThrow());
        r.setDatumRezervacije(dto.datumRezervacije);
        r.setStatus(Rezervacija.Status.NA_CEKANJU);
        r.setKreirano(LocalDateTime.now());
        return rezervacijaRepo.save(r);
    }

    @Override
    public List<Rezervacija> getByKorisnik(Integer korisnikId) {
        List<Rezervacija> rezervacije = rezervacijaRepo.findByKorisnik_KorisnikId(korisnikId);
        rezervacije.forEach(r -> {
            boolean isPlaceno = placanjeRepo.existsByRezervacija_RezervacijaId(r.getRezervacijaId());
            r.setPlaceno(isPlaceno);
        });
        return rezervacije;
    }

    @Override
    public Rezervacija updateStatus(Long id, Rezervacija.Status status) {
        Rezervacija r = rezervacijaRepo.findById(id).orElseThrow();
        r.setStatus(status);
        return rezervacijaRepo.save(r);
    }

    @Override
    public void delete(Long id) {
        rezervacijaRepo.deleteById(id);
    }

    @Override
    public Rezervacija otkazi(Long id) {
        Rezervacija r = rezervacijaRepo.findById(id).orElseThrow();
        r.setStatus(Rezervacija.Status.OTKAZANO);
        return rezervacijaRepo.save(r);
    }
}
