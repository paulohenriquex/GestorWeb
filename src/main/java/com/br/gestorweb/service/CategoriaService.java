package com.br.gestorweb.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Categoria> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias.isEmpty()) {
            return null;
        }
        return categorias;
    }

    public Categoria findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        return categoriaRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        categoriaRepository.deleteById(id);
    }

}
