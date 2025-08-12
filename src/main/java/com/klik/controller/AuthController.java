package com.klik.controller;

import com.klik.dto.AuthRequest;
import com.klik.dto.AuthResponse;
import com.klik.entity.Korisnik;
import com.klik.service.KorisnikService;
import com.klik.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final KorisnikService korisnikService;
    private final JwtUtil jwtUtil;

    public AuthController(KorisnikService korisnikService, JwtUtil jwtUtil) {
        this.korisnikService = korisnikService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Korisnik> register(@RequestBody Korisnik korisnik) {
        return ResponseEntity.ok(korisnikService.register(korisnik));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return korisnikService.authenticate(request.getEmail(), request.getLozinka())
                .map(k -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(k.getKorisnikId(),k.getEmail(),k.getUloga().name()))))
                .orElse(ResponseEntity.status(401).build());
    }
}
