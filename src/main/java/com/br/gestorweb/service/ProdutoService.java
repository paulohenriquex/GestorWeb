package com.br.gestorweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.gestorweb.model.Produto;
import com.br.gestorweb.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ResponseEntity<Produto> save(Produto produto) {
        try {
            produtoRepository.save(produto);
            return ResponseEntity.ok(produto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<Produto>> findAll() {
        try {
            return ResponseEntity.ok(produtoRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
