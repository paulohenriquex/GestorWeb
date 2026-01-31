package br.com.gestorweb.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.gestorweb.model.Planejamento;

public record PlanejamentoDTO(Long id, LocalDate data, Long usuarioId,
        List<ServicoPlanejamentoDTO> servicosPlanejamento) {
    public static PlanejamentoDTO fromEntity(Planejamento planejamento) {
        return new PlanejamentoDTO(
                planejamento.getId(),
                planejamento.getData(),
                planejamento.getUsuario() != null ? planejamento.getUsuario().getId() : null,
                planejamento.getServicosPlanejamento() != null
                        ? planejamento.getServicosPlanejamento().stream().map(ServicoPlanejamentoDTO::fromEntity)
                                .toList()
                        : new ArrayList<>());
    }
}
