package br.com.gestorweb.dto;

import br.com.gestorweb.model.Endereco;

public record EnderecoDTO(Long id, String pais, String estado, String cidade, String bairro,
        String logradouro,
        String cep, String numero, String complemento, Long usuarioId) {
    public static EnderecoDTO fromEntity(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return new EnderecoDTO(
                endereco.getId(),
                endereco.getPais(),
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getLogradouro(),
                endereco.getCep(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getUsuario() != null ? endereco.getUsuario().getId() : null);
    }
}
