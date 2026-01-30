package br.com.gestorweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestorweb.dto.EnderecoDTO;
import br.com.gestorweb.model.Endereco;
import br.com.gestorweb.repository.EnderecoRepository;
import jakarta.transaction.Transactional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public EnderecoDTO save(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(dto.logradouro());
        endereco.setPais(dto.pais());
        endereco.setEstado(dto.estado());
        endereco.setCidade(dto.cidade());
        endereco.setBairro(dto.bairro());
        endereco.setCep(dto.cep());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco = enderecoRepository.save(endereco);
        return converterParaDTO(endereco);
    }

    public List<EnderecoDTO> findAll() {
        List<Endereco> enderecosNoBanco = enderecoRepository.findAll();
        List<EnderecoDTO> listaDeDtos = new ArrayList<>();
        for (Endereco endereco : enderecosNoBanco) {
            listaDeDtos.add(converterParaDTO(endereco));
        }
        return listaDeDtos;
    }

    public EnderecoDTO converterParaDTO(Endereco endereco) {
        return new EnderecoDTO(endereco.getId(),
                endereco.getLogradouro(),
                endereco.getPais(),
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getNumero(),
                endereco.getComplemento());
    }
}
