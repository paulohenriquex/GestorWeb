package com.br.gestorweb.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.gestorweb.model.Marca;
import com.br.gestorweb.repository.MarcaRepository;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    public Marca save(Marca marca) {
        if (marca == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo");
        }
        return Objects.requireNonNull(marcaRepository.save(marca));
    }

    public ResponseEntity<List<Marca>> findAll() {
        List<Marca> marcas = marcaRepository.findAll();

        if (marcas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marcas);
    }
}
