-- Inserção de dados iniciais para testes

-- Clientes
INSERT INTO cliente (nome, cpf, telefone, email, endereco) VALUES 
('João Silva', '123.456.789-00', '48999990000', 'joao@email.com', 'Rua das Flores, 123, Centro'),
('Maria Santos', '987.654.321-00', '48988887777', 'maria@email.com', 'Av. Principal, 456, Bairro Novo'),
('Pedro Oliveira', '111.222.333-44', '48977776666', 'pedro@email.com', 'Rua do Comércio, 789, Centro');

-- Entregadores
INSERT INTO entregador (nome, telefone, disponivel) VALUES 
('Carlos Mendes', '48966665555', true),
('Ana Paula', '48955554444', true),
('Roberto Alves', '48944443333', false);

-- Produtos - Pizzas
INSERT INTO produto (nome, descricao, preco, categoria, disponivel) VALUES 
('Pizza Calabresa', 'Calabresa, cebola, mussarela e orégano', 39.90, 'PIZZA', true),
('Pizza Margherita', 'Tomate, mussarela, manjericão e azeite', 35.90, 'PIZZA', true),
('Pizza Portuguesa', 'Presunto, ovos, cebola, mussarela e azeitona', 42.90, 'PIZZA', true),
('Pizza 4 Queijos', 'Mussarela, gorgonzola, parmesão e provolone', 44.90, 'PIZZA', true),
('Pizza Frango com Catupiry', 'Frango desfiado, catupiry e milho', 41.90, 'PIZZA', true);

-- Produtos - Bebidas
INSERT INTO produto (nome, descricao, preco, categoria, disponivel) VALUES 
('Coca-Cola 2L', 'Refrigerante Coca-Cola 2 litros', 8.50, 'BEBIDA', true),
('Guaraná Antarctica 2L', 'Refrigerante Guaraná 2 litros', 7.50, 'BEBIDA', true),
('Água Mineral 500ml', 'Água mineral sem gás', 3.00, 'BEBIDA', true),
('Suco de Laranja 1L', 'Suco natural de laranja', 12.00, 'BEBIDA', true);

-- Produtos - Sobremesas
INSERT INTO produto (nome, descricao, preco, categoria, disponivel) VALUES 
('Brownie com Sorvete', 'Brownie quente com sorvete de creme', 15.90, 'SOBREMESA', true),
('Pudim de Leite', 'Pudim caseiro de leite condensado', 12.90, 'SOBREMESA', true),
('Torta de Limão', 'Torta de limão com merengue', 14.90, 'SOBREMESA', true);

