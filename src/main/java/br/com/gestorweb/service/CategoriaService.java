package br.com.gestorweb.service;

import org.springframework.stereotype.Service;

import br.com.gestorweb.dto.CategoriaDTO;
import br.com.gestorweb.model.Categoria;
import br.com.gestorweb.repository.CategoriaRepository;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaDTO save(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        categoria = categoriaRepository.save(categoria);
        return converterParaDTO(categoria);
    }

    public CategoriaDTO converterParaDTO(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNome());
    }
}
