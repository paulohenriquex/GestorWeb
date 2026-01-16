CREATE TABLE marca

CREATE TABLE produto(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    medida VARCHAR(50) NOT NULL,
    marca_id INTEGER,
    categoria_id INTEGER,
    fornecedor_id INTEGER,

    -- Denifição das chaves estrangeiras (Constrains)
    CONSTRAINS fk_marca FOREING KEY (marca_id) REFERENCES marca(id)
    CONSTRAINS fk_categoria FOREING KEY (categoria_id) REFERENCES categoria(id)
    CONSTRAINS fk_fornecedor FOREING KEY (fornecedor_id) REFERENCES fornecedor(id)
);
