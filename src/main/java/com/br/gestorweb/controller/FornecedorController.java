package com.br.gestorweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.gestorweb.model.Fornecedor;
import com.br.gestorweb.service.FornecedorService;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
    @Autowired
    public FornecedorService fornecedorService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Fornecedor> cadastrarFornecedor(@RequestBody Fornecedor fornecedor) {
        Fornecedor fornecedorSalvo = fornecedorService.save(fornecedor);
        return ResponseEntity.ok(fornecedorService.save(fornecedorSalvo));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Fornecedor>> listarFornecedores() {
        return fornecedorService.findAll();
    }
}
