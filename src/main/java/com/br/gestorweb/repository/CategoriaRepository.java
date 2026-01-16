package com.br.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.gestorweb.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
