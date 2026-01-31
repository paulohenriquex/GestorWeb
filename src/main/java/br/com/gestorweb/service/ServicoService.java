package br.com.gestorweb.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestorweb.dto.ServicoDTO;
import br.com.gestorweb.model.Servico;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.ServicoRepository;
import br.com.gestorweb.repository.UsuarioRepository;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final UsuarioRepository usuarioRepository;

    public ServicoService(ServicoRepository servicoRepository, UsuarioRepository usuarioRepository) {
        this.servicoRepository = servicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ServicoDTO save(ServicoDTO dto) {
        Servico servico;
        if (dto.id() != null) {
            servico = servicoRepository.findById(dto.id())
                    .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        } else {
            servico = new Servico();
        }
        servico.setNome(dto.nome());

        if (dto.usuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            servico.setUsuario(usuario);
        } else {
            throw new IllegalArgumentException("Usuário é obrigatório para o serviço.");
        }

        servico = servicoRepository.save(servico);
        return ServicoDTO.fromEntity(servico);
    }

    public ServicoDTO findById(Long id) {
        return servicoRepository.findById(id)
                .map(ServicoDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    public List<ServicoDTO> findAll() {
        return servicoRepository.findAll().stream()
                .map(ServicoDTO::fromEntity)
                .collect(Collectors.toList());
    }
}