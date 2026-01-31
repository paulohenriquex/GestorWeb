package br.com.gestorweb.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.gestorweb.model.ServicoPlanejamento;

public record ServicoPlanejamentoDTO(Long id, Long planejamentoId, Long servicoId, Integer quantitativo, Long usuarioId,
        List<ServicoReceitaDTO> servicoReceitas) {
    public static ServicoPlanejamentoDTO fromEntity(ServicoPlanejamento servicoPlanejamento) {
        if (servicoPlanejamento == null) {
            return null;
        }
        return new ServicoPlanejamentoDTO(
                servicoPlanejamento.getId(),
                servicoPlanejamento.getPlanejamento() != null ? servicoPlanejamento.getPlanejamento().getId() : null,
                servicoPlanejamento.getServico() != null ? servicoPlanejamento.getServico().getId() : null,
                servicoPlanejamento.getQuantitativo(),
                servicoPlanejamento.getUsuario() != null ? servicoPlanejamento.getUsuario().getId() : null,
                servicoPlanejamento.getServicoReceitas() != null
                        ? servicoPlanejamento.getServicoReceitas().stream().map(ServicoReceitaDTO::fromEntity).toList()
                        : new ArrayList<>());
    }
}