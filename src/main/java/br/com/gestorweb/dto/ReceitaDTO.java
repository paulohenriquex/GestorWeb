package br.com.gestorweb.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.gestorweb.model.IngredienteReceita;
import br.com.gestorweb.model.Receita;

public record ReceitaDTO(Long id, String nome, String modoDePreparo, List<IngredienteReceitaDTO> ingredientes,
        Long usuarioId) {

    public static ReceitaDTO fromEntity(Receita receita) {
        List<IngredienteReceitaDTO> ingredientesDTO = new ArrayList<>();
        if (receita.getIngredientes() != null) {
            for (IngredienteReceita ing : receita.getIngredientes()) {
                ingredientesDTO.add(IngredienteReceitaDTO.fromEntity(ing));
            }
        }

        return new ReceitaDTO(
                receita.getId(),
                receita.getNome(),
                receita.getModoDePreparo(),
                ingredientesDTO,
                receita.getUsuario() != null ? receita.getUsuario().getId() : null);
    }

}
