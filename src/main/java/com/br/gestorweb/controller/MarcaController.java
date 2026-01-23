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

import com.br.gestorweb.model.Marca;
import com.br.gestorweb.service.MarcaService;

@RestController
@RequestMapping("/marcas")
@CrossOrigin(origins = "http://localhost:5173")
public class MarcaController {

    @Autowired
    public MarcaService marcaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Marca> cadastrarMarca(@RequestBody Marca marca) {
        Marca marcaSalva = marcaService.save(marca);
        return ResponseEntity.ok(marcaSalva);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Marca>> listarMarcas() {
        List<Marca> marcas = marcaService.findAll();
        if (marcas == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(marcas);
    }
}
