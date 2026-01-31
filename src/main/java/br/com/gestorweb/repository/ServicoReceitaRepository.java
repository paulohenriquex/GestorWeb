package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestorweb.model.ServicoReceita;

@Repository
public interface ServicoReceitaRepository extends JpaRepository<ServicoReceita, Long> {
}