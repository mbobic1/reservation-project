package com.klik.controller;

import com.klik.entity.Korisnik;
import com.klik.service.KorisnikService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/korisnici")
public class KorisnikController {

    private final KorisnikService korisnikService;

    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @GetMapping
    public List<Korisnik> getAllKorisnici() {
        return korisnikService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKorisnici(@PathVariable("id") Integer id) {
        korisnikService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
