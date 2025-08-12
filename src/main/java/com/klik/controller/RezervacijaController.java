package com.klik.controller;

import com.klik.dto.RezervacijaRequestDTO;
import com.klik.entity.Rezervacija;
import com.klik.service.RezervacijaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rezervacije")
public class RezervacijaController {

    private final RezervacijaService service;

    public RezervacijaController(RezervacijaService service) {
        this.service = service;
    }

    // GET /rezervacije
    @GetMapping
    public ResponseEntity<List<Rezervacija>> getAllRezervacije() {
        return ResponseEntity.ok(service.getAll());
    }

    // POST /rezervacije
    @PostMapping
    public ResponseEntity<Rezervacija> kreiraj(@RequestBody RezervacijaRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    // GET /rezervacije/korisnik/{id}
    @GetMapping("/korisnik/{id}")
    public ResponseEntity<List<Rezervacija>> korisnickeRezervacije(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getByKorisnik(id));
    }

    // PUT /rezervacije/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Rezervacija> promijeniStatus(@PathVariable Long id, @RequestParam Rezervacija.Status status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    // DELETE /rezervacije/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisi(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/otkazi")
    public ResponseEntity<Rezervacija> otkazi(@PathVariable Long id) {
        return ResponseEntity.ok(service.otkazi(id));
    }
}
