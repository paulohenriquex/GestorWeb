package br.com.gestorweb.dto;

import java.math.BigDecimal;

import br.com.gestorweb.model.Produto;

public record ProdutoDTO(long id, String nome, BigDecimal preco, String medida, Long marcaId, Long categoriaId,
        Long fornecedorId) {

    public static ProdutoDTO fromEntity(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getMedida(),
                produto.getMarca() != null ? produto.getMarca().getId() : null,
                produto.getCategoria() != null ? produto.getCategoria().getId() : null,
                produto.getFornecedor() != null ? produto.getFornecedor().getId() : null);
    }
}
