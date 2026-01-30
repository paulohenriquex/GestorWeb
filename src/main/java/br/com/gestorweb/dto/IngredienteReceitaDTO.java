package br.com.gestorweb.dto;

import java.math.BigDecimal;

public record IngredienteReceitaDTO(Long id, Long produtoId, Long receitaId, BigDecimal percapita) {

}
