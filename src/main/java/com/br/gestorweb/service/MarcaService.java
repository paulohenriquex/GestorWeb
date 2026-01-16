package com.br.gestorweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.gestorweb.model.Marca;
import com.br.gestorweb.repository.MarcaRepository;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    public ResponseEntity<Marca> save(Marca marca) {
        try {
            return ResponseEntity.ok(marcaRepository.save(marca));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<Marca>> findAll() {
        try {
            return ResponseEntity.ok(marcaRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
