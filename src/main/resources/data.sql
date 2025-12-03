-- Limpar dados existentes para evitar duplicatas
DELETE FROM item_pedido;
DELETE FROM pedido;
DELETE FROM cliente;
DELETE FROM produto;
DELETE FROM entregador;

-- Inser��o de dados iniciais para testes

-- Clientes
INSERT INTO cliente (nome, cpf, telefone, email, endereco, data_cadastro) VALUES
('Jo�o Silva', '123.456.789-00', '48999990000', 'joao@email.com', 'Rua das Flores, 123, Centro', now()),
('Maria Santos', '987.654.321-00', '48988887777', 'maria@email.com', 'Av. Principal, 456, Bairro Novo', now()),
('Pedro Oliveira', '111.222.333-44', '48977776666', 'pedro@email.com', 'Rua do Com�rcio, 789, Centro', now());

-- Entregadores
INSERT INTO entregador (nome, telefone, disponivel) VALUES 
('Carlos Mendes', '48966665555', true),
('Ana Paula', '48955554444', true),
('Roberto Alves', '48944443333', false);

-- Produtos - Pizzas
INSERT INTO produto (nome, descricao, preco, categoria, disponivel) VALUES 
('Pizza Calabresa', 'Calabresa, cebola, mussarela e or�gano', 39.90, 'PIZZA', true),
('Pizza Margherita', 'Tomate, mussarela, manjeric�o e azeite', 35.90, 'PIZZA', true),
('Pizza Portuguesa', 'Presunto, ovos, cebola, mussarela e azeitona', 42.90, 'PIZZA', true),
('Pizza 4 Queijos', 'Mussarela, gorgonzola, parmes�o e provolone', 44.90, 'PIZZA', true),
('Pizza Frango com Catupiry', 'Frango desfiado, catupiry e milho', 41.90, 'PIZZA', true);

-- Produtos - Bebidas
INSERT INTO produto (nome, descricao, preco, categoria, disponivel) VALUES 
('Coca-Cola 2L', 'Refrigerante Coca-Cola 2 litros', 8.50, 'BEBIDA', true),
('Guaran� Antarctica 2L', 'Refrigerante Guaran� 2 litros', 7.50, 'BEBIDA', true),
('�gua Mineral 500ml', '�gua mineral sem g�s', 3.00, 'BEBIDA', true),
('Suco de Laranja 1L', 'Suco natural de laranja', 12.00, 'BEBIDA', true);

-- Produtos - Sobremesas
INSERT INTO produto (nome, descricao, preco, categoria, disponivel) VALUES 
('Brownie com Sorvete', 'Brownie quente com sorvete de creme', 15.90, 'SOBREMESA', true),
('Pudim de Leite', 'Pudim caseiro de leite condensado', 12.90, 'SOBREMESA', true),
('Torta de Lim�o', 'Torta de lim�o com merengue', 14.90, 'SOBREMESA', true);

