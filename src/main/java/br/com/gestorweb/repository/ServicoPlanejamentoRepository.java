package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestorweb.model.ServicoPlanejamento;

@Repository
public interface ServicoPlanejamentoRepository extends JpaRepository<ServicoPlanejamento, Long> {
}