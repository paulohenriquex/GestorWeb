package br.com.gestorweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestorweb.repository.ProdutoRepository;
import br.com.gestorweb.repository.ReceitaRepository;
import br.com.gestorweb.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import br.com.gestorweb.dto.IngredienteReceitaDTO;
import br.com.gestorweb.dto.ReceitaDTO;
import br.com.gestorweb.model.IngredienteReceita;
import br.com.gestorweb.model.Receita;

@Service
public class ReceitaService {
    private final ReceitaRepository receitaRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public ReceitaService(ReceitaRepository receitaRepository, ProdutoRepository produtoRepository,
            UsuarioRepository usuarioRepository) { // Removed PlanejamentoRepository
        this.receitaRepository = receitaRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ReceitaDTO save(ReceitaDTO dto) {
        Receita receita = new Receita();
        if (dto.id() != null) {
            receita = receitaRepository.findById(dto.id())
                    .orElseThrow(() -> new RuntimeException("Receita não encontrada"));
        }

        receita.setNome(dto.nome());
        receita.setModoDePreparo(dto.modoDePreparo());

        if (dto.usuarioId() != null) {
            receita.setUsuario(usuarioRepository.getReferenceById(dto.usuarioId()));
        }

        if (dto.ingredientes() != null) {
            List<IngredienteReceita> listaDeIngredientes = new ArrayList<>();
            for (IngredienteReceitaDTO ingredienteDTO : dto.ingredientes()) {
                IngredienteReceita ingrediente = new IngredienteReceita();
                ingrediente.setPercapita(ingredienteDTO.percapita());
                if (ingredienteDTO.produtoId() != null) {
                    ingrediente.setProduto(produtoRepository.getReferenceById(ingredienteDTO.produtoId()));
                }
                ingrediente.setUsuario(receita.getUsuario());
                ingrediente.setReceita(receita);
                listaDeIngredientes.add(ingrediente);
            }
            if (receita.getIngredientes() != null) {
                receita.getIngredientes().clear();
                receita.getIngredientes().addAll(listaDeIngredientes);
            } else {
                receita.setIngredientes(listaDeIngredientes);
            }
            receita = receitaRepository.save(receita);
            return ReceitaDTO.fromEntity(receita);
        }
        return ReceitaDTO.fromEntity(receitaRepository.save(receita)); // Moved this line outside the if block
    }

    public List<ReceitaDTO> findAll() {
        List<Receita> receitasNoBanco = receitaRepository.findAll();
        List<ReceitaDTO> listaDeDtos = new ArrayList<>();
        for (Receita receita : receitasNoBanco) {
            listaDeDtos.add(ReceitaDTO.fromEntity(receita));
        }
        return listaDeDtos;
    }

}
