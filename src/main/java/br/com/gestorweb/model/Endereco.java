package br.com.gestorweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pais;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String cep;
    private String numero;
    private String complemento;

    @OneToOne(mappedBy = "endereco")
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
