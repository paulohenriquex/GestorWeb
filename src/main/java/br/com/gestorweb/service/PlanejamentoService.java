package br.com.gestorweb.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestorweb.model.IngredienteReceita;
import br.com.gestorweb.model.Planejamento;
import br.com.gestorweb.model.ServicoPlanejamento;
import br.com.gestorweb.repository.PlanejamentoRepository;

@Service
public class PlanejamentoService {
    private final PlanejamentoRepository planejamentoRepository;

    public PlanejamentoService(PlanejamentoRepository planejamentoRepository) {
        this.planejamentoRepository = planejamentoRepository;
    }

    public List<String> calcularListaDeCompras(Long planejamentoId) {
        Planejamento p = planejamentoRepository.findById(planejamentoId)
                .orElseThrow(() -> new RuntimeException("Planejamento não encontrado"));
        List<String> listaDeCompras = new ArrayList<>();

        for (ServicoPlanejamento sp : p.getServicosPlanejamento()) {
            Integer quantitativoPessoas = sp.getQuantitativo();
            if (quantitativoPessoas == null || quantitativoPessoas <= 0) {
                continue; // Skip if no valid quantity is set for the service
            }
            for (br.com.gestorweb.model.ServicoReceita sr : sp.getServicoReceitas()) {
                for (IngredienteReceita ing : sr.getReceita().getIngredientes()) {
                    BigDecimal totalNecessario = ing.getPercapita().multiply(new BigDecimal(quantitativoPessoas));
                    listaDeCompras.add(
                            ing.getProduto().getNome() + ": " + totalNecessario + " " + ing.getProduto().getMedida());
                }
            }
        }
        return listaDeCompras;
    }
}
