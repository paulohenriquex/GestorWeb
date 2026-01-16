package com.br.gestorweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.gestorweb.model.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    @Query("SELECT r FROM Receita r "
            + "JOIN FETCH r.ingredientes i "
            + "JOIN FETCH i.produto "
            + "WHERE r.id = :id")
    Optional<Receita> findByIdWithDetails(@Param("id") Long id);
}
