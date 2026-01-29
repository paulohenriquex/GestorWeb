package br.com.gestorweb.dto;

public record EnderecoDTO(Long id, String logradouro, String pais, String estado, String cidade, String bairro,
        String cep, String numero, String complemento) {

}
