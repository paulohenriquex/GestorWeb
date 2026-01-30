package br.com.gestorweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestorweb.dto.IngredienteReceitaDTO;
import br.com.gestorweb.dto.ReceitaDTO;
import br.com.gestorweb.model.IngredienteReceita;
import br.com.gestorweb.model.Receita;
import br.com.gestorweb.repository.ProdutoRepository;
import br.com.gestorweb.repository.ReceitaRepository;
import jakarta.transaction.Transactional;

@Service
public class ReceitaService {
    private final ReceitaRepository receitaRepository;
    private final ProdutoRepository produtoRepository;

    public ReceitaService(ReceitaRepository receitaRepository, ProdutoRepository produtoRepository) {
        this.receitaRepository = receitaRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public ReceitaDTO save(ReceitaDTO dto) {
        Receita receita = new Receita();
        receita.setId(dto.id());
        receita.setNome(dto.nome());
        receita.setModoDePreparo(dto.modoDePreparo());

        if (dto.ingredientes() != null) {
            List<IngredienteReceita> listaDeIngredientes = new ArrayList<>();
            for (IngredienteReceitaDTO ingredienteDTO : dto.ingredientes()) {
                IngredienteReceita ingrediente = new IngredienteReceita();
                ingrediente.setPercapita(ingredienteDTO.percapita());
                if (ingredienteDTO.produtoId() != null) {
                    ingrediente.setProduto(produtoRepository.getReferenceById(ingredienteDTO.produtoId()));
                }

                ingrediente.setReceita(receita);
                listaDeIngredientes.add(ingrediente);
            }
            receita.setIngredientes(listaDeIngredientes);
        }
        receita = receitaRepository.save(receita);
        return converterParaDTO(receita);
    }

    private ReceitaDTO converterParaDTO(Receita receita) {
        List<IngredienteReceitaDTO> ingredientesDTO = new ArrayList<>();
        if (receita.getIngredientes() != null) {
            for (IngredienteReceita ingrediente : receita.getIngredientes()) {
                Long produtoId = (ingrediente.getProduto() != null) ? ingrediente.getProduto().getId() : null;

                ingredientesDTO.add(new IngredienteReceitaDTO(
                        ingrediente.getId(),
                        produtoId,
                        receita.getId(),
                        ingrediente.getPercapita()));
            }

        }
        return new ReceitaDTO(
                receita.getId(),
                receita.getNome(),
                receita.getModoDePreparo(),
                ingredientesDTO);
    }

}
