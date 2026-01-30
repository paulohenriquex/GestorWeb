package br.com.gestorweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestorweb.dto.EnderecoDTO;
import br.com.gestorweb.dto.FornecedorDTO;
import br.com.gestorweb.model.Endereco;
import br.com.gestorweb.model.Fornecedor;
import br.com.gestorweb.repository.FornecedorRepository;
import jakarta.transaction.Transactional;

@Service
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Transactional
    public FornecedorDTO save(FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEmail(dto.email());
        if (dto.endereco() != null) {
            Endereco endereco = new Endereco();
            endereco.setId(dto.endereco().id());
            endereco.setLogradouro(dto.endereco().logradouro());
            endereco.setPais(dto.endereco().pais());
            endereco.setEstado(dto.endereco().estado());
            endereco.setCidade(dto.endereco().cidade());
            endereco.setBairro(dto.endereco().bairro());
            endereco.setCep(dto.endereco().cep());
            endereco.setNumero(dto.endereco().numero());
            endereco.setComplemento(dto.endereco().complemento());
            fornecedor.setEndereco(endereco);
        }
        fornecedor = fornecedorRepository.save(fornecedor);
        return converterParaDTO(fornecedor);
    }

    public List<FornecedorDTO> findAll() {
        List<Fornecedor> fornecedoresNoBanco = fornecedorRepository.findAll();
        List<FornecedorDTO> listaDeDtos = new ArrayList<>();
        for (Fornecedor fornecedor : fornecedoresNoBanco) {
            listaDeDtos.add(converterParaDTO(fornecedor));
        }
        return listaDeDtos;
    }

    public FornecedorDTO converterParaDTO(Fornecedor fornecedor) {
        EnderecoDTO enderecoDto = null;

        if (fornecedor.getEndereco() != null) {
            enderecoDto = new EnderecoDTO(
                    fornecedor.getEndereco().getId(),
                    fornecedor.getNome(),
                    fornecedor.getEndereco().getEstado(),
                    fornecedor.getEndereco().getCidade(),
                    fornecedor.getEndereco().getBairro(),
                    fornecedor.getEndereco().getLogradouro(),
                    fornecedor.getEndereco().getCep(),
                    fornecedor.getEndereco().getNumero(),
                    fornecedor.getEndereco().getComplemento());
        }

        return new FornecedorDTO(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getTelefone(),
                fornecedor.getEmail(),
                enderecoDto);
    }
}
