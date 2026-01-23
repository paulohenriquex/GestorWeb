package com.br.gestorweb.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Marca> findAll() {
        List<Marca> marcas = marcaRepository.findAll();

        if (marcas.isEmpty()) {
            return null;
        }
        return marcas;
    }

    public Marca findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        return marcaRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        marcaRepository.deleteById(id);
    }
}
