package br.com.gestorweb.dto;

import br.com.gestorweb.model.Categoria;

public record CategoriaDTO(Long id, String nome, Long usuarioId) {
    public static CategoriaDTO fromEntity(Categoria categoria) {
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getUsuario() != null ? categoria.getUsuario().getId() : null);
    }
}
