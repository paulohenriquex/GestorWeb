package br.com.gestorweb.dto;

public record EnderecoDTO(Long id, String pais, String estado, String cidade, String bairro,
                String logradouro, String cep, String numero, String complemento) {

}
