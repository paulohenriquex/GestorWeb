package br.com.gestorweb.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String senha;
    private String role;

    @OneToMany(mappedBy = "usuario")
    private List<Produto> produtos;

    @OneToMany(mappedBy = "usuario")
    private List<Receita> receitas;

    @OneToMany(mappedBy = "usuario")
    private List<Fornecedor> fornecedores;

    @OneToMany(mappedBy = "usuario")
    private List<Servico> servicos;

    @OneToMany(mappedBy = "usuario")
    private List<Planejamento> planejamentos;

    @OneToMany(mappedBy = "usuario")
    private List<Marca> marcas;

    @OneToMany(mappedBy = "usuario")
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "usuario")
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "usuario")
    private List<IngredienteReceita> ingredientes;

}
