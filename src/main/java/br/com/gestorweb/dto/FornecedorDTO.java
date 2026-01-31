package br.com.gestorweb.dto;

import br.com.gestorweb.model.Fornecedor;

public record FornecedorDTO(Long id, String nome, String telefone, String email, EnderecoDTO endereco,
                Long usuarioId) {
        public static FornecedorDTO fromEntity(Fornecedor fornecedor) {
                return new FornecedorDTO(
                                fornecedor.getId(),
                                fornecedor.getNome(),
                                fornecedor.getTelefone(),
                                fornecedor.getEmail(),
                                EnderecoDTO.fromEntity(fornecedor.getEndereco()),
                                fornecedor.getUsuario() != null ? fornecedor.getUsuario().getId() : null);
        }
}
