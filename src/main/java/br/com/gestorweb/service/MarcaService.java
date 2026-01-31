package br.com.gestorweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestorweb.dto.MarcaDTO;
import br.com.gestorweb.model.Marca;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.MarcaRepository;
import br.com.gestorweb.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final UsuarioRepository usuarioRepository;

    public MarcaService(MarcaRepository marcaRepository, UsuarioRepository usuarioRepository) {
        this.marcaRepository = marcaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public MarcaDTO save(MarcaDTO dto) {
        Marca marca = (dto.id() != null)
                ? marcaRepository.findById(dto.id()).orElseThrow(() -> new RuntimeException("Marca não encontrada"))
                : new Marca();
        marca.setNome(dto.nome());
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        marca.setUsuario(usuario);
        marca = marcaRepository.save(marca);
        return MarcaDTO.fromEntity(marca);
    }

    public List<MarcaDTO> findAll() {
        List<Marca> marcasNoBanco = marcaRepository.findAll();
        List<MarcaDTO> listaDeDtos = new ArrayList<>();
        for (Marca marca : marcasNoBanco) {
            listaDeDtos.add(MarcaDTO.fromEntity(marca));
        }
        return listaDeDtos;
    }

}
