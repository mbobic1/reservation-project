package com.klik.service;

import com.klik.dto.PlacanjeRequestDTO;
import com.klik.entity.Placanje;

import java.util.List;

public interface PlacanjeService {
    Placanje izvrsiPlacanje(PlacanjeRequestDTO dto);
    List<Placanje> dohvatiPoKorisniku(Long korisnikId);
    List<Placanje> dohvatiSva();
}
