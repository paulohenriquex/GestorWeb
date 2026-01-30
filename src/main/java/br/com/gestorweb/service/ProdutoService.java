package br.com.gestorweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestorweb.dto.ProdutoDTO;
import br.com.gestorweb.model.Produto;
import br.com.gestorweb.repository.CategoriaRepository;
import br.com.gestorweb.repository.MarcaRepository;
import br.com.gestorweb.repository.ProdutoRepository;

@Service
public class ProdutoService {
        @Autowired
        private MarcaRepository marcaRepository;
        @Autowired
        private CategoriaRepository categoriaRepository;
        @Autowired
        private ProdutoRepository produtoRepository;

        public ProdutoDTO save(ProdutoDTO dto) {
                Produto produto = new Produto();
                produto.setNome(dto.nome());
                produto.setPreco(dto.preco());
                produto.setMedida(dto.medida());
                if (dto.marcaId() != null) {
                        produto.setMarca(marcaRepository.getReferenceById(dto.marcaId()));
                }
                if (dto.categoriaId() != null) {
                        produto.setCategoria(categoriaRepository.getReferenceById(dto.categoriaId()));
                }
                produto = produtoRepository.save(produto);
                return converterParaDTO(produto);
        }

        public List<ProdutoDTO> findAll() {
                List<Produto> produtosNoBanco = produtoRepository.findAll();
                List<ProdutoDTO> listaDeDtos = new ArrayList<>();
                for (Produto produto : produtosNoBanco) {
                        ProdutoDTO dto = converterParaDTO(produto);
                        listaDeDtos.add(dto);
                }
                return listaDeDtos;
        }

        private ProdutoDTO converterParaDTO(Produto produto) {
                Long marcaId = (produto.getMarca() != null) ? produto.getMarca().getId() : null;
                Long categoriaId = (produto.getCategoria() != null) ? produto.getCategoria().getId() : null;
                return new ProdutoDTO(
                                produto.getId(),
                                produto.getNome(),
                                produto.getPreco(),
                                produto.getMedida(),
                                marcaId,
                                categoriaId);
        }
}
