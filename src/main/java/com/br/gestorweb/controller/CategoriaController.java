package com.br.gestorweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.gestorweb.model.Categoria;
import com.br.gestorweb.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    public CategoriaService categoriaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Categoria> cadastrarCategoria(@RequestBody Categoria categoria) {
        Categoria categoriaSalva = categoriaService.save(categoria);
        return ResponseEntity.ok(categoriaService.save(categoriaSalva));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return categoriaService.findAll();
    }
}
