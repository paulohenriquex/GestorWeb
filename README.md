# GestorWeb

## Visão Geral

O GestorWeb é uma aplicação web desenvolvida em Spring Boot para auxiliar no gerenciamento de receitas, produtos, fornecedores e no planejamento de refeições. Ele permite que usuários organizem seus itens, criem receitas detalhadas, planejem serviços (como almoços ou cafés da manhã) com quantitativos específicos de pessoas e gerem listas de compras com base nesses planejamentos.

## Funcionalidades Principais

- **Gerenciamento de Produtos:** Cadastre, visualize e edite produtos, associando-os a marcas, categorias e fornecedores.
- **Gerenciamento de Receitas:** Crie receitas detalhadas com nome, modo de preparo e uma lista de ingredientes com suas quantidades per capita.
- **Gerenciamento de Fornecedores:** Mantenha um registro dos seus fornecedores, incluindo informações de contato e endereço.
- **Gerenciamento de Marcas e Categorias:** Organize seus produtos utilizando marcas e categorias personalizadas.
- **Planejamento de Serviços:** Crie planejamentos para eventos ou períodos específicos, associando diferentes tipos de serviços (ex: Almoço, Café da Manhã).
- **Quantitativos Flexíveis:** Cada serviço dentro de um planejamento pode ter um quantitativo de pessoas diferente, permitindo cálculos precisos.
- **Geração de Lista de Compras:** Com base nos planejamentos e nas receitas associadas, o sistema calcula automaticamente a quantidade total de cada ingrediente necessário, gerando uma lista de compras consolidada.
- **Associação de Usuários:** Todas as entidades (produtos, receitas, fornecedores, etc.) são associadas a um usuário específico, garantindo a segregação de dados.

## Tecnologias Utilizadas

- **Backend:**
  - Java 17+
  - Spring Boot
  - Spring Data JPA
  - Lombok
  - Maven
- **Banco de Dados:**
  - H2 Database (para desenvolvimento/testes, pode ser configurado para outros bancos de dados relacionais como PostgreSQL, MySQL, etc.)
- **Outros:**
  - Jakarta Persistence API (JPA)
  - DTOs (Data Transfer Objects) para comunicação entre camadas

## Estrutura do Projeto

O projeto segue uma arquitetura em camadas, comum em aplicações Spring Boot:

- `br.com.gestorweb.model`: Contém as entidades JPA que representam a estrutura do banco de dados e os objetos de domínio.
  - `Categoria`, `Endereco`, `Fornecedor`, `IngredienteReceita`, `Marca`, `Planejamento`, `Produto`, `Receita`, `Servico`, `ServicoPlanejamento`, `ServicoReceita`, `Usuario`.
- `br.com.gestorweb.dto`: Contém os Data Transfer Objects (DTOs) usados para transferir dados entre as camadas da aplicação, desacoplando as entidades do domínio da camada de apresentação.
  - `CategoriaDTO`, `EnderecoDTO`, `FornecedorDTO`, `IngredienteReceitaDTO`, `MarcaDTO`, `PlanejamentoDTO`, `ProdutoDTO`, `ReceitaDTO`, `ServicoDTO`, `ServicoPlanejamentoDTO`, `ServicoReceitaDTO`, `UsuarioDTO`.
- `br.com.gestorweb.repository`: Contém as interfaces de repositório (Spring Data JPA) para acesso e persistência de dados no banco de dados.
  - `CategoriaRepository`, `EnderecoRepository`, `FornecedorRepository`, `MarcaRepository`, `PlanejamentoRepository`, `ProdutoRepository`, `ReceitaRepository`, `ServicoRepository`, `UsuarioRepository`.
- `br.com.gestorweb.service`: Contém a lógica de negócios da aplicação. Orquestra as operações entre os repositórios e os DTOs, aplicando as regras de negócio.
  - `CategoriaService`, `FornecedorService`, `MarcaService`, `PlanejamentoService`, `ProdutoService`, `ReceitaService`, `ServicoService`.
- `br.com.gestorweb.controller`: (Implícito, mas esperado) Conteria os controladores REST que expõem a API da aplicação.

## Como Rodar o Projeto

### Pré-requisitos

- Java Development Kit (JDK) 17 ou superior
- Maven
- Um IDE de sua preferência (IntelliJ IDEA, VS Code, Eclipse)

### Passos

1.  **Clone o repositório:**

    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    cd gestorweb
    ```

2.  **Compile o projeto com Maven:**

    ```bash
    mvn clean install
    ```

3.  **Execute a aplicação Spring Boot:**
    ```bash
    mvn spring-boot:run
    ```
    A aplicação estará disponível em `http://localhost:8080` (ou na porta configurada no `application.properties`).

## Exemplo de Uso (API)

Embora os controladores não tenham sido fornecidos, a estrutura de serviços sugere endpoints RESTful.

### Exemplo: Criar um Produto

`POST /api/produtos`

```json
{
  "nome": "Arroz Agulhinha",
  "preco": 5.5,
  "medida": "kg",
  "marcaId": 1,
  "categoriaId": 1,
  "fornecedorId": 1,
  "usuarioId": 1
}
```

### Exemplo: Gerar Lista de Compras

`GET /api/planejamentos/{planejamentoId}/lista-compras`

Retornaria algo como:

```json
[
  "Arroz Agulhinha: 1000.0 kg",
  "Feijão Carioca: 500.0 kg",
  "Carne Bovina: 250.0 kg"
]
```

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença

Este projeto está licenciado sob a Licença MIT.
