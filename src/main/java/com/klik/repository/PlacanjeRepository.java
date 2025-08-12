package com.klik.repository;

import com.klik.entity.Placanje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlacanjeRepository extends JpaRepository<Placanje, Long> {
    @Query("SELECT p FROM Placanje p WHERE p.korisnik.id = :korisnikId")
    List<Placanje> findByKorisnikId(@Param("korisnikId") Long korisnikId);

    boolean existsByRezervacija_RezervacijaId(Long rezervacijaId);

}
