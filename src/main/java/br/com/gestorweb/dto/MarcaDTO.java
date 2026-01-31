package br.com.gestorweb.dto;

import br.com.gestorweb.model.Marca;

public record MarcaDTO(Long id, String nome, Long usuarioId) {
    public static MarcaDTO fromEntity(Marca marca) {
        return new MarcaDTO(
                marca.getId(),
                marca.getNome(),
                marca.getUsuario() != null ? marca.getUsuario().getId() : null);
    }

}
