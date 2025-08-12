package com.klik.service;

import com.klik.dto.TretmanDTO;

import java.util.List;

public interface TretmanService {
    List<TretmanDTO> getAll();
    TretmanDTO getById(Integer id);
    TretmanDTO create(TretmanDTO dto);
    TretmanDTO update(Integer id, TretmanDTO dto);
    void delete(Integer id);
    List<TretmanDTO> search(String naziv, Double cijena);
}
