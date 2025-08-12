package com.klik.repository;

import com.klik.entity.Rezervacija;
import com.klik.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
    List<Rezervacija> findByKorisnik_KorisnikId(Integer korisnikId);
}
