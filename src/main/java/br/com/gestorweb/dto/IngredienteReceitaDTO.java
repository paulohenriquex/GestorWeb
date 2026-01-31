package br.com.gestorweb.dto;

import java.math.BigDecimal;

import br.com.gestorweb.model.IngredienteReceita;

public record IngredienteReceitaDTO(Long id, BigDecimal percapita, Long produtoId, Long receitaId, Long usuarioId) {
    public static IngredienteReceitaDTO fromEntity(IngredienteReceita ingrediente) {
        if (ingrediente == null) {
            return null;
        }
        return new IngredienteReceitaDTO(
                ingrediente.getId(),
                ingrediente.getPercapita(),
                ingrediente.getProduto() != null ? ingrediente.getProduto().getId() : null,
                ingrediente.getReceita() != null ? ingrediente.getReceita().getId() : null,
                ingrediente.getUsuario() != null ? ingrediente.getUsuario().getId() : null);
    }
}
