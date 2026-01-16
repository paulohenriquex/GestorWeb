package com.br.gestorweb.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.gestorweb.model.Receita;
import com.br.gestorweb.service.ReceitaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/receita")
@RequiredArgsConstructor
public class ReceitaController {

    private final ReceitaService receitaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Receita> cadastrarReceita(@RequestBody Receita receita) {
        Receita novaReceita = receitaService.save(receita);
        try {
            return ResponseEntity.ok(receitaService.save(novaReceita));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Receita>> listarReceitas() {
        return ResponseEntity.badRequest().build();
    }
}
