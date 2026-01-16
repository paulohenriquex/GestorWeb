package com.br.gestorweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.gestorweb.model.Categoria;
import com.br.gestorweb.repository.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public ResponseEntity<Categoria> save(Categoria categoria) {
        try {
            return ResponseEntity.ok(categoriaRepository.save(categoria));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<Categoria>> findAll() {
        try {
            return ResponseEntity.ok(categoriaRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
