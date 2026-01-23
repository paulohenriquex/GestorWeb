package com.br.gestorweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.gestorweb.model.Fornecedor;
import com.br.gestorweb.service.FornecedorService;

@RestController
@RequestMapping("/fornecedores")
@CrossOrigin(origins = "http://localhost:5173")
public class FornecedorController {
    @Autowired
    public FornecedorService fornecedorService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Fornecedor> cadastrarFornecedor(@RequestBody Fornecedor fornecedor) {
        Fornecedor fornecedorSalvo = fornecedorService.save(fornecedor);
        return ResponseEntity.ok(fornecedorSalvo);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Fornecedor>> listarFornecedores() {
        List<Fornecedor> fornecedores = fornecedorService.findAll();
        if (fornecedores == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(fornecedores);
    }
}
