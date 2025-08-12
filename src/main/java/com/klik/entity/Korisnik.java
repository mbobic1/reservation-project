package com.klik.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "korisnici")
public class Korisnik implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer korisnikId;

    private String ime;
    private String email;
    private String telefon;
    private String lozinka;

    @Enumerated(EnumType.STRING)
    private Uloga uloga;

    @Column(name = "kreirano")
    private LocalDateTime kreirano;

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public LocalDateTime getKreirano() {
        return kreirano;
    }

    public void setKreirano(LocalDateTime kreirano) {
        this.kreirano = kreirano;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return a list with a single role. The role must be prefixed with "ROLE_".
        return List.of(new SimpleGrantedAuthority("ROLE_" + uloga.name().toUpperCase()));
    }

    @Override
    public String getPassword() {
        return lozinka;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // For simplicity, we return true. You can add logic for account expiration if needed.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // For simplicity, we return true. You can add logic for account locking if needed.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // For simplicity, we return true. You can add logic for password expiration if needed.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // For simplicity, we return true. You can add logic for disabling accounts if needed.
        return true;
    }
}
