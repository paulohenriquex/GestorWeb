package br.com.gestorweb.dto;

import java.util.List;

public record ReceitaDTO(Long id, String nome, String modoDePreparo, List<IngredienteReceitaDTO> ingredientes) {

}
