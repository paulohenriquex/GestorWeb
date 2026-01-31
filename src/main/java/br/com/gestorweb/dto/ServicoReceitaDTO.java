package br.com.gestorweb.dto;

import br.com.gestorweb.model.ServicoReceita;

public record ServicoReceitaDTO(Long id, Long servicoPlanejamentoId, Long receitaId, Long usuarioId) {
    public static ServicoReceitaDTO fromEntity(ServicoReceita servicoReceita) {
        if (servicoReceita == null) {
            return null;
        }
        return new ServicoReceitaDTO(
                servicoReceita.getId(),
                servicoReceita.getServicoPlanejamento() != null ? servicoReceita.getServicoPlanejamento().getId()
                        : null,
                servicoReceita.getReceita() != null ? servicoReceita.getReceita().getId() : null,
                servicoReceita.getUsuario() != null ? servicoReceita.getUsuario().getId() : null);
    }
}