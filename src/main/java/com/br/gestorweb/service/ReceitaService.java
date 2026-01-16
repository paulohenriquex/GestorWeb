package com.br.gestorweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.gestorweb.model.Receita;
import com.br.gestorweb.repository.ReceitaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    @Transactional
    public Receita save(Receita receita) {
        if (receita.getIngredientes() != null) {
            receita.getIngredientes().forEach(ingrediente -> ingrediente.setReceita(receita));
        }
        return receitaRepository.save(receita);
    }

    public List<Receita> findAll() {
        return receitaRepository.findAll();
    }
}
