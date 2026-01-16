package com.br.gestorweb.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.gestorweb.model.Categoria;
import com.br.gestorweb.repository.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria save(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo");
        }
        return Objects.requireNonNull(categoriaRepository.save(categoria));
    }

    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(categorias);
    }
}
