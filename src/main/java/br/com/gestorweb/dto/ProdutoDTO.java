package br.com.gestorweb.dto;

import java.math.BigDecimal;

public record ProdutoDTO(long id, String nome, BigDecimal preco, String medida, Long marcaId, Long categoriaId) {
}
