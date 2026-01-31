package br.com.gestorweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestorweb.dto.ProdutoDTO;
import br.com.gestorweb.model.Produto;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.CategoriaRepository;
import br.com.gestorweb.repository.FornecedorRepository;
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
    private final FornecedorRepository fornecedorRepository;

    public ProdutoService(MarcaRepository marcaRepository, CategoriaRepository categoriaRepository,
            ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository,
            FornecedorRepository fornecedorRepository) {
        this.marcaRepository = marcaRepository;
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    @Transactional
    public ProdutoDTO save(ProdutoDTO dto, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")); // Considerar exceção mais
                                                                                    // específica
        Produto produto;
        // Se um ID for fornecido, tenta encontrar o produto existente; caso contrário,
        // cria um novo.
        if (dto.id() != 0 && dto.id() != 0) {
            produto = produtoRepository.findById(dto.id())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado")); // Lança exceção se o ID não for
                                                                                        // encontrado
        } else {
            produto = new Produto();
        }
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setMedida(dto.medida());
        if (dto.marcaId() != null) {
            produto.setMarca(marcaRepository.getReferenceById(dto.marcaId()));
        }
        if (dto.categoriaId() != null) {
            produto.setCategoria(categoriaRepository.getReferenceById(dto.categoriaId()));
        } else {
            produto.setCategoria(null); // Garante que a categoria seja desassociada se não for fornecida
        }
        if (dto.fornecedorId() != null) {
            produto.setFornecedor(fornecedorRepository.getReferenceById(dto.fornecedorId()));
        } else {
            produto.setFornecedor(null); // Garante que o fornecedor seja desassociado se não for fornecido
        }
        produto.setUsuario(usuario);
        produto = produtoRepository.save(produto);
        return ProdutoDTO.fromEntity(produto);
    }

    public List<ProdutoDTO> findAll(Long usuarioId) {
        List<Produto> produtosNoBanco = produtoRepository.findByUsuarioId(usuarioId); // Correção do typo
        return produtosNoBanco.stream().map(ProdutoDTO::fromEntity).toList(); // Uso de Streams
    }

}
