package br.com.gestorweb.service;

import org.springframework.stereotype.Service;

import br.com.gestorweb.dto.ReceitaDTO;
import br.com.gestorweb.model.Receita;
import br.com.gestorweb.repository.ReceitaRepository;

@Service
public class ReceitaService {
    private final ReceitaRepository receitaRepository;

    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public ReceitaDTO save(ReceitaDTO dto) {
        Receita receita = new Receita();
        receita.setNome(dto.nome());
        receita.setModoDePreparo(dto.modoDePreparo());
        receita = receitaRepository.save(receita);
        return converterParaDTO(receita);
    }

    private ReceitaDTO converterParaDTO(Receita receita) {
        return new ReceitaDTO(receita.getId(), receita.getNome(), receita.getModoDePreparo());
    }

}
