package br.com.gestorweb.dto;

import br.com.gestorweb.model.Servico;

public record ServicoDTO(Long id, String nome, Long usuarioId) {
    public static ServicoDTO fromEntity(Servico servico) {
        if (servico == null) {
            return null;
        }
        return new ServicoDTO(
                servico.getId(),
                servico.getNome(),
                servico.getUsuario() != null ? servico.getUsuario().getId() : null
        );
    }

}
