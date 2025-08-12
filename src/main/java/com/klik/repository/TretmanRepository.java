package com.klik.repository;

import com.klik.entity.Tretman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TretmanRepository extends JpaRepository<Tretman, Integer> {
    List<Tretman> findByNazivContainingIgnoreCase(String naziv);
    List<Tretman> findByCijenaLessThanEqual(Double cijena);
}
