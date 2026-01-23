package com.br.gestorweb.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.gestorweb.model.Fornecedor;
import com.br.gestorweb.repository.FornecedorRepository;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor save(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo");
        }
        return Objects.requireNonNull(fornecedorRepository.save(fornecedor));
    }

    public List<Fornecedor> findAll() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        if (fornecedores.isEmpty()) {
            return null;
        }
        return fornecedores;
    }

    public Fornecedor findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        return fornecedorRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        fornecedorRepository.deleteById(id);
    }
}
