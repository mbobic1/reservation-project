package com.klik.service.impl;

import com.klik.dto.TretmanDTO;
import com.klik.entity.Tretman;
import com.klik.repository.TretmanRepository;
import com.klik.service.TretmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TretmanServiceImpl implements TretmanService {

    @Autowired
    private TretmanRepository repository;

    private TretmanDTO toDto(Tretman t) {
        TretmanDTO dto = new TretmanDTO();
        dto.setId(t.getId());
        dto.setNaziv(t.getNaziv());
        dto.setOpis(t.getOpis());
        dto.setCijena(t.getCijena());
        return dto;
    }

    private Tretman toEntity(TretmanDTO dto) {
        Tretman t = new Tretman();
        t.setId(dto.getId());
        t.setNaziv(dto.getNaziv());
        t.setOpis(dto.getOpis());
        t.setCijena(dto.getCijena());
        return t;
    }

    @Override
    public List<TretmanDTO> getAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public TretmanDTO getById(Integer id) {
        return repository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public TretmanDTO create(TretmanDTO dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    public TretmanDTO update(Integer id, TretmanDTO dto) {
        Tretman tretman = repository.findById(id).orElseThrow();
        tretman.setNaziv(dto.getNaziv());
        tretman.setOpis(dto.getOpis());
        tretman.setCijena(dto.getCijena());
        return toDto(repository.save(tretman));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<TretmanDTO> search(String naziv, Double cijena) {
        if (naziv != null && cijena != null) {
            return repository.findAll().stream()
                    .filter(t -> t.getNaziv().toLowerCase().contains(naziv.toLowerCase()))
                    .filter(t -> t.getCijena().doubleValue() <= cijena)
                    .map(this::toDto)
                    .collect(Collectors.toList());
        } else if (naziv != null) {
            return repository.findByNazivContainingIgnoreCase(naziv).stream().map(this::toDto).collect(Collectors.toList());
        } else if (cijena != null) {
            return repository.findByCijenaLessThanEqual(cijena).stream().map(this::toDto).collect(Collectors.toList());
        } else {
            return getAll();
        }
    }
}
