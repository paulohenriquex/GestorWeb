package br.com.gestorweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestorweb.dto.ProdutoDTO;
import br.com.gestorweb.model.Produto;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.CategoriaRepository;
import br.com.gestorweb.repository.MarcaRepository;
import br.com.gestorweb.repository.ProdutoRepository;
import br.com.gestorweb.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
        private final MarcaRepository marcaRepository;
        private final CategoriaRepository categoriaRepository;
        private final ProdutoRepository produtoRepository;
        private final UsuarioRepository usuarioRepository;

        public ProdutoService(MarcaRepository marcaRepository, CategoriaRepository categoriaRepository,
                        ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
                this.marcaRepository = marcaRepository;
                this.categoriaRepository = categoriaRepository;
                this.produtoRepository = produtoRepository;
                this.usuarioRepository = usuarioRepository;

        }

        @Transactional
        public ProdutoDTO save(ProdutoDTO dto, Long usuarioId) {

                Usuario usuario = usuarioRepository.findById(usuarioId)
                                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
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
                produto.setUsuario(usuario);
                produto = produtoRepository.save(produto);
                return converterParaDTO(produto);
        }

        public List<ProdutoDTO> findAll(Long usuarioId) {
                List<Produto> produtosNoBanco = produtoRepository.finbByUsuarioId(usuarioId); // Mantém a correção do
                                                                                              // typo
                List<ProdutoDTO> listaDeDtos = new ArrayList<>(); // Reverte para o ArrayList tradicional
                for (Produto produto : produtosNoBanco) { // Reverte para o loop for tradicional
                        listaDeDtos.add(converterParaDTO(produto));
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
