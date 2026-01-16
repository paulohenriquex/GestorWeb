
CREATE TABLE marca(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE categoria(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE endereco (
    id BIGSERIAL PRIMARY KEY,
    pais VARCHAR(255),
    estado VARCHAR(255),
    cidade VARCHAR(100),
    bairro VARCHAR(100),
    cep VARCHAR(10),
    logradouro VARCHAR(255),
    numero VARCHAR(10),
    complemento VARCHAR(255)
);
CREATE TABLE fornecedor(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    telefone VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cnpj VARCHAR(20) NOT NULL UNIQUE,
    endereco_id BIGINT,
    CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE produto(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    medida VARCHAR(50) NOT NULL,
    marca_id BIGINT,
    categoria_id BIGINT,
    fornecedor_id BIGINT,
    CONSTRAINT fk_marca FOREIGN KEY (marca_id) REFERENCES marca(id),
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id),
    CONSTRAINT fk_fornecedor FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id)
);

CREATE TABLE receita(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    modo_de_preparo TEXT NOT NULL
);

CREATE TABLE receita_ingrediente(
    id BIGSERIAL PRIMARY KEY,
    receita_id BIGINT,
    produto_id BIGINT,
    percapita DECIMAL NOT NULL,
    CONSTRAINT fk_receita FOREIGN KEY (receita_id) REFERENCES receita(id) ON DELETE CASCADE,
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produto(id)
);