package com.klik.service;

import com.klik.entity.Korisnik;
import com.klik.entity.Uloga;
import com.klik.repository.KorisnikRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {

    private final KorisnikRepository korisnikRepository;
    private final BCryptPasswordEncoder encoder;

    public KorisnikService(KorisnikRepository korisnikRepository, BCryptPasswordEncoder encoder) {
        this.korisnikRepository = korisnikRepository;
        this.encoder = encoder;
    }

    public Korisnik register(Korisnik korisnik) {
        korisnik.setLozinka(encoder.encode(korisnik.getLozinka()));
        korisnik.setKreirano(LocalDateTime.now());
        return korisnikRepository.save(korisnik);
    }

    public Optional<Korisnik> authenticate(String email, String rawPassword) {
        return korisnikRepository.findByEmail(email)
                .filter(k -> encoder.matches(rawPassword, k.getLozinka()));
    }

    public List<Korisnik> getAll() {
        return korisnikRepository.findAll();
    }

    public void delete(Integer id) {
        korisnikRepository.deleteById(id);
    }
}
