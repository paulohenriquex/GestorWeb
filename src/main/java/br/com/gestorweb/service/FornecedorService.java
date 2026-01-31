package br.com.gestorweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestorweb.dto.EnderecoDTO;
import br.com.gestorweb.dto.FornecedorDTO;
import br.com.gestorweb.model.Endereco;
import br.com.gestorweb.model.Fornecedor;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.FornecedorRepository;
import br.com.gestorweb.repository.EnderecoRepository;
import br.com.gestorweb.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;
    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository, EnderecoRepository enderecoRepository,
            UsuarioRepository usuarioRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public FornecedorDTO save(FornecedorDTO dto) {
        Fornecedor fornecedor = (dto.id() != null) ? fornecedorRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado")) : new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEmail(dto.email());

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        fornecedor.setUsuario(usuario);

        if (dto.endereco() != null) {
            Endereco endereco = (dto.endereco().id() != null) ? enderecoRepository.findById(dto.endereco().id())
                    .orElseThrow(() -> new RuntimeException("Endereço não encontrado")) : new Endereco();
            atualizarDadosEndereco(endereco, dto.endereco(), usuario);
            fornecedor.setEndereco(enderecoRepository.save(endereco));
        }
        return FornecedorDTO.fromEntity(fornecedorRepository.save(fornecedor));
    }

    public List<FornecedorDTO> findAll() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        List<FornecedorDTO> dtos = new ArrayList<>();
        for (Fornecedor f : fornecedores) {
            dtos.add(FornecedorDTO.fromEntity(f));
        }
        return dtos;
    }

    // Método auxiliar para não poluir o save
    private void atualizarDadosEndereco(Endereco e, EnderecoDTO d, Usuario u) {
        e.setLogradouro(d.logradouro());
        e.setPais(d.pais());
        e.setEstado(d.estado());
        e.setCidade(d.cidade());
        e.setBairro(d.bairro());
        e.setCep(d.cep());
        e.setNumero(d.numero());
        e.setComplemento(d.complemento());
        e.setUsuario(u);
    }
}
