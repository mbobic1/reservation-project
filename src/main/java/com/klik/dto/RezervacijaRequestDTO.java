package com.klik.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class RezervacijaRequestDTO {
    public Long korisnikId;
    public Long tretmanId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime datumRezervacije;
}
