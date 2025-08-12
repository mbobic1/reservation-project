package com.klik.controller;

import com.klik.dto.TretmanDTO;
import com.klik.service.TretmanService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tretmani")
public class TretmanController {

    @Autowired
    private TretmanService service;

    @GetMapping
    public List<TretmanDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TretmanDTO getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public TretmanDTO create(@RequestBody TretmanDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public TretmanDTO update(@PathVariable Integer id, @RequestBody TretmanDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public List<TretmanDTO> search(@RequestParam(required = false) String naziv,
                                   @RequestParam(required = false) Double cijena) {
        return service.search(naziv, cijena);
    }
}
