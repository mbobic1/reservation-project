package com.klik.controller;

import com.klik.dto.PlacanjeRequestDTO;
import com.klik.entity.Placanje;
import com.klik.service.PlacanjeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/placanja")
public class PlacanjeController {

    private final PlacanjeService service;

    public PlacanjeController(PlacanjeService service) {
        this.service = service;
    }

    // POST /placanja
    @PostMapping
    public ResponseEntity<Placanje> izvrsi(@RequestBody PlacanjeRequestDTO dto) {
        return ResponseEntity.ok(service.izvrsiPlacanje(dto));
    }

    // GET /placanja/korisnik/{id}
    @GetMapping("/korisnik/{id}")
    public ResponseEntity<List<Placanje>> korisnicka(@PathVariable Long id) {
        return ResponseEntity.ok(service.dohvatiPoKorisniku(id));
    }

    // GET /placanja
    @GetMapping
    public ResponseEntity<List<Placanje>> sva() {
        return ResponseEntity.ok(service.dohvatiSva());
    }
}
