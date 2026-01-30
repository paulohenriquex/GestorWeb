package br.com.gestorweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestorweb.dto.MarcaDTO;
import br.com.gestorweb.model.Marca;
import br.com.gestorweb.repository.MarcaRepository;
import jakarta.transaction.Transactional;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Transactional
    public MarcaDTO save(MarcaDTO dto) {
        Marca marca = new Marca();
        marca.setNome(dto.nome());
        marca = marcaRepository.save(marca);
        return converterParaDTO(marca);
    }

    public List<MarcaDTO> findAll() {
        List<Marca> marcasNoBanco = marcaRepository.findAll();
        List<MarcaDTO> listaDeDtos = new ArrayList<>();
        for (Marca marca : marcasNoBanco) {
            listaDeDtos.add(converterParaDTO(marca));
        }
        return listaDeDtos;
    }

    private MarcaDTO converterParaDTO(Marca marca) {
        return new MarcaDTO(marca.getId(), marca.getNome());
    }
}
