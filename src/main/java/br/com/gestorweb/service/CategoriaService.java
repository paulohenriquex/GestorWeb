package br.com.gestorweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestorweb.dto.CategoriaDTO;
import br.com.gestorweb.model.Categoria;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.CategoriaRepository;
import br.com.gestorweb.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository) {
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public CategoriaDTO save(CategoriaDTO dto) {
        Categoria categoria;
        if (dto.id() != null) {
            categoria = categoriaRepository.findById(dto.id())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        } else {
            categoria = new Categoria();
        }
        categoria.setNome(dto.nome());
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        categoria.setUsuario(usuario);
        categoria = categoriaRepository.save(categoria);
        return CategoriaDTO.fromEntity(categoria);
    }

    public List<CategoriaDTO> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaDTO> dtos = new ArrayList<>();
        for (Categoria categoria : categorias) {
            dtos.add(CategoriaDTO.fromEntity(categoria));
        }
        return dtos;
    }

}
