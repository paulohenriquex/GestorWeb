package br.com.gestorweb.dto;

import java.util.List;

public record UsuarioDTO(Long id, String email, String senha, String role, List<ProdutoDTO> produtos,
        List<ReceitaDTO> receitas, List<FornecedorDTO> fornecedores, List<ServicoDTO> servicos,
        List<PlanejamentoDTO> planejamentos, List<MarcaDTO> marcas, List<CategoriaDTO> categorias,
        List<EnderecoDTO> enderecos, List<IngredienteReceitaDTO> ingredientes) {

}
