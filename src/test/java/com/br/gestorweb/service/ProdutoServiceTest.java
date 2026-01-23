package com.br.gestorweb.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.br.gestorweb.model.Categoria;
import com.br.gestorweb.model.Fornecedor;
import com.br.gestorweb.model.Marca;
import com.br.gestorweb.model.Produto;
import com.br.gestorweb.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class) // 1. Inicializa o Mockito com JUnit 5
public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Test
    @DisplayName("Deve salvar um produto com sucesso quando os dados forem válidos")
    void deveSalvarProdutoComSucesso() {

        // 1. Criar os objetos dependentes (Mockados ou instanciados simples)
        Marca marca = new Marca();
        marca.setId(1L); // Assumindo que Marca tem um setId

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(1L);

        Categoria categoria = new Categoria();
        categoria.setId(1L);

        // 2. Montar o Produto
        Produto produtoParaSalvar = new Produto();
        produtoParaSalvar.setNome("Tomate");
        produtoParaSalvar.setPreco(new BigDecimal("6.00"));
        produtoParaSalvar.setMedida("Kg");

        // Aqui passamos os OBJETOS, não os IDs soltos
        produtoParaSalvar.setMarca(marca);
        produtoParaSalvar.setFornecedor(fornecedor);
        produtoParaSalvar.setCategoria(categoria);

        // 3. Ensinar o Mockito
        // "Quando o repositório tentar salvar qualquer produto, retorne o produto que
        // criamos"
        when(produtoRepository.save(produtoParaSalvar)).thenReturn(produtoParaSalvar);
        // --- AÇÃO (Act) ---
        Produto produtoSalvo = produtoService.save(produtoParaSalvar);

        // --- VERIFICAÇÃO (Assert) ---
        Assertions.assertNotNull(produtoSalvo);
        Assertions.assertEquals("Tomate", produtoSalvo.getNome());
        Assertions.assertEquals(new BigDecimal("6.00"), produtoSalvo.getPreco());

        // Verifica se o repository foi chamado 1 vez
        verify(produtoRepository).save(produtoParaSalvar);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o preço for negativo")
    void deveLanccarExcecaoQuandoPrecoForNegativo() {
        // Arrange (Cenário)
        Produto produtoInvalido = new Produto();
        produtoInvalido.setNome("Produto Errado");
        produtoInvalido.setPreco(new BigDecimal("-1.00")); // Preço negativo

        // Act & Assert (Ação e Verificação)
        // Esperamos que o Service lance uma exceção ao tentar salvar
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            produtoService.save(produtoInvalido);
        });

        Assertions.assertEquals("O preço não pode ser negativo", exception.getMessage());
    }

}
