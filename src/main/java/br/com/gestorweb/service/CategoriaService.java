package br.com.gestorweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestorweb.dto.CategoriaDTO;
import br.com.gestorweb.model.Categoria;
import br.com.gestorweb.repository.CategoriaRepository;
import jakarta.transaction.Transactional;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public CategoriaDTO save(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        categoria = categoriaRepository.save(categoria);
        return converterParaDTO(categoria);
    }

    public List<CategoriaDTO> findAll() {
        List<Categoria> categoriasNoBanco = categoriaRepository.findAll();
        List<CategoriaDTO> listaDeDtos = new ArrayList<>();
        for (Categoria categoria : categoriasNoBanco) {
            listaDeDtos.add(converterParaDTO(categoria));
        }
        return listaDeDtos;
    }

    public CategoriaDTO converterParaDTO(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNome());
    }
}
