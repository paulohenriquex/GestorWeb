package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestorweb.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
