-- Adicionar marca
INSERT INTO marca (nome) VALUES ('Rei');
INSERT INTO marca (nome) VALUES ('PurOvos');
INSERT INTO marca (nome) VALUES ('Paracatu');
INSERT INTO marca (nome) VALUES ('Toddy');

INSERT INTO endereco (pais, estado, cidade, bairro, cep, logradouro, numero, complemento) VALUES
('Brasil', 'SP', 'São Paulo', 'Pinheiros', '05401-000', 'Rua dos Pinheiros', '123', 'Apto 42'),
('Brasil', 'RJ', 'Rio de Janeiro', 'Copacabana', '22041-001', 'Avenida Atlântica', '1500', 'Bloco B'),
('Brasil', 'MG', 'Belo Horizonte', 'Savassi', '30140-010', 'Rua Pernambuco', '85', NULL),
('Brasil', 'PR', 'Curitiba', 'Batel', '80420-000', 'Avenida do Batel', '2040', 'Sala 301');

-- Adicionar fornecedor
INSERT INTO fornecedor (nome, cnpj, telefone, email,endereco_id) VALUES ('Empresa tribom', '00000000000100', '3899999999', 'tribom@email.com',1);
INSERT INTO fornecedor (nome, cnpj, telefone, email,endereco_id) VALUES ('Mercado BH', '00000000000101', '3188888888', 'bh@email.com',2);
INSERT INTO fornecedor (nome, cnpj, telefone, email,endereco_id) VALUES ('Laticinios Paracatu', '00000000000102', '3877777777', 'paracatu@email.com',3);
INSERT INTO fornecedor (nome, cnpj, telefone, email,endereco_id) VALUES ('Lacta', '00000000000103', '1166666666', 'lacta@email.com',4);

-- Adicionar categoria
INSERT INTO categoria (nome) VALUES ('farinaceos');
INSERT INTO categoria (nome) VALUES ('proteinas');
INSERT INTO categoria (nome) VALUES ('lacticinios');
INSERT INTO categoria (nome) VALUES ('bebida açucarada');

-- Farinha (Preço 6.00, Medida Kg, Marca 1, Categoria 1, Fornecedor 1)
INSERT INTO produto (nome, preco, medida, marca_id, categoria_id, fornecedor_id)
VALUES ('Farinha de trigo', 6.00, 'Kg', 1, 1, 1);

-- Ovo (Preço 0.50, Medida Un, Marca 2, Categoria 2, Fornecedor 2)
INSERT INTO produto (nome, preco, medida, marca_id, categoria_id, fornecedor_id) 
VALUES ('Ovo', 0.50, 'Un', 2, 2, 2);

-- Leite (Preço 6.00, Medida LT, Marca 3, Categoria 3, Fornecedor 3)
INSERT INTO produto (nome, preco, medida, marca_id, categoria_id, fornecedor_id) 
VALUES ('Leite', 6.00, 'LT', 3, 3, 3);

-- Achocolatado (Preço 15.00, Medida Kg, Marca 4, Categoria 4, Fornecedor 4)
INSERT INTO produto (nome, preco, medida, marca_id, categoria_id, fornecedor_id) 
VALUES ('Achocolatado toddy', 15.00, 'Kg', 4, 4, 4);

-- Adicionar Receita
INSERT INTO receita (nome,modo_de_preparo) VALUES ('Bolo de Chocolate Simples', 'Misture a farinha, o achocolatado e os ovos. Adicione o leite aos poucos. Asse por 40 minutos a 180°C.');

-- Inserir ingredientes na tabela de junção
INSERT INTO receita_ingrediente (receita_id, produto_id, percapita) VALUES (1,1, 0.50);
INSERT INTO receita_ingrediente (receita_id, produto_id, percapita) VALUES (1,2, 3.00);
INSERT INTO receita_ingrediente (receita_id, produto_id, percapita) VALUES (1,3, 0.25);
INSERT INTO receita_ingrediente (receita_id, produto_id, percapita) VALUES (1,4, 0.20);


