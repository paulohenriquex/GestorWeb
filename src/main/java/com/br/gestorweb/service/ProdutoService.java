package com.br.gestorweb.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.gestorweb.model.Produto;
import com.br.gestorweb.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto save(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo");
        }

        if (produto.getPreco().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo");
        }

        return Objects.requireNonNull(produtoRepository.save(produto));
    }

    public List<Produto> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()) {
            return null;
        }
        return produtos;
    }

    public Produto findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        return produtoRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        produtoRepository.deleteById(id);
    }

}
