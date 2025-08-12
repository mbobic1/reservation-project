package com.klik.service.impl;

import com.klik.dto.PlacanjeRequestDTO;
import com.klik.entity.*;
import com.klik.repository.*;
import com.klik.service.PlacanjeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlacanjeServiceImpl implements PlacanjeService {

    private final PlacanjeRepository placanjeRepo;
    private final KorisnikRepository korisnikRepo;
    private final RezervacijaRepository rezervacijaRepo;

    public PlacanjeServiceImpl(PlacanjeRepository placanjeRepo, KorisnikRepository korisnikRepo, RezervacijaRepository rezervacijaRepo) {
        this.placanjeRepo = placanjeRepo;
        this.korisnikRepo = korisnikRepo;
        this.rezervacijaRepo = rezervacijaRepo;
    }

    @Override
    public Placanje izvrsiPlacanje(PlacanjeRequestDTO dto) {
        Placanje p = new Placanje();
        p.setKorisnik(korisnikRepo.findById(Math.toIntExact(dto.korisnikId)).orElseThrow());
        p.setRezervacija(rezervacijaRepo.findById(dto.rezervacijaId).orElseThrow());
        p.setIznos(dto.iznos);
        p.setDatum(LocalDateTime.now());
        return placanjeRepo.save(p);
    }

    @Override
    public List<Placanje> dohvatiPoKorisniku(Long korisnikId) {
        return placanjeRepo.findByKorisnikId(korisnikId);
    }

    @Override
    public List<Placanje> dohvatiSva() {
        return placanjeRepo.findAll();
    }
}
