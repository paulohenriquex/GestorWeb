package com.br.gestorweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.gestorweb.model.Fornecedor;
import com.br.gestorweb.repository.FornecedorRepository;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public ResponseEntity<Fornecedor> save(Fornecedor fornecedor) {
        try {
            return ResponseEntity.ok(fornecedorRepository.save(fornecedor));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<Fornecedor>> findAll() {
        try {
            return ResponseEntity.ok(fornecedorRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
