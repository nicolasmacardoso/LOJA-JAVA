# Exemplos de Requisições - API Pizzaria

Este arquivo contém exemplos práticos de requisições para testar a API.

## Base URL
```
http://localhost:8080/api/v1
```

---

## 1. Clientes

### Criar Cliente
```http
POST /api/v1/clientes
Content-Type: application/json

{
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "telefone": "48999990000",
  "email": "joao@email.com",
  "endereco": "Rua das Flores, 123, Centro"
}
```

### Listar Todos os Clientes
```http
GET /api/v1/clientes
```

### Buscar Cliente por ID
```http
GET /api/v1/clientes/1
```

### Atualizar Cliente
```http
PUT /api/v1/clientes/1
Content-Type: application/json

{
  "nome": "João Silva Santos",
  "cpf": "123.456.789-00",
  "telefone": "48999991111",
  "email": "joao.silva@email.com",
  "endereco": "Rua das Flores, 123, Centro, Blumenau"
}
```

### Deletar Cliente
```http
DELETE /api/v1/clientes/1
```

---

## 2. Produtos

### Criar Pizza
```http
POST /api/v1/produtos
Content-Type: application/json

{
  "nome": "Pizza Calabresa",
  "descricao": "Calabresa, cebola, mussarela e orégano",
  "preco": 39.90,
  "categoria": "PIZZA",
  "disponivel": true
}
```

### Criar Bebida
```http
POST /api/v1/produtos
Content-Type: application/json

{
  "nome": "Coca-Cola 2L",
  "descricao": "Refrigerante Coca-Cola 2 litros",
  "preco": 8.50,
  "categoria": "BEBIDA",
  "disponivel": true
}
```

### Criar Sobremesa
```http
POST /api/v1/produtos
Content-Type: application/json

{
  "nome": "Brownie com Sorvete",
  "descricao": "Brownie quente com sorvete de creme",
  "preco": 15.90,
  "categoria": "SOBREMESA",
  "disponivel": true
}
```

### Listar Todos os Produtos
```http
GET /api/v1/produtos
```

### Listar Produtos Disponíveis
```http
GET /api/v1/produtos/disponiveis
```

### Buscar Produto por ID
```http
GET /api/v1/produtos/1
```

### Atualizar Produto
```http
PUT /api/v1/produtos/1
Content-Type: application/json

{
  "nome": "Pizza Calabresa Especial",
  "descricao": "Calabresa, cebola, mussarela, orégano e azeitona",
  "preco": 42.90,
  "categoria": "PIZZA",
  "disponivel": true
}
```

### Marcar Produto como Indisponível
```http
PUT /api/v1/produtos/1
Content-Type: application/json

{
  "nome": "Pizza Calabresa",
  "descricao": "Calabresa, cebola, mussarela e orégano",
  "preco": 39.90,
  "categoria": "PIZZA",
  "disponivel": false
}
```

---

## 3. Entregadores

### Criar Entregador
```http
POST /api/v1/entregadores
Content-Type: application/json

{
  "nome": "Carlos Mendes",
  "telefone": "48966665555",
  "disponivel": true
}
```

### Listar Todos os Entregadores
```http
GET /api/v1/entregadores
```

### Listar Entregadores Disponíveis
```http
GET /api/v1/entregadores/disponiveis
```

### Buscar Entregador por ID
```http
GET /api/v1/entregadores/1
```

### Atualizar Entregador
```http
PUT /api/v1/entregadores/1
Content-Type: application/json

{
  "nome": "Carlos Mendes",
  "telefone": "48966665555",
  "disponivel": false
}
```

---

## 4. Pedidos

### Criar Pedido de Delivery
```http
POST /api/v1/pedidos
Content-Type: application/json

{
  "clienteId": 1,
  "isDelivery": true,
  "taxaEntrega": 5.00,
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 2
    },
    {
      "produtoId": 6,
      "quantidade": 1
    }
  ]
}
```

### Criar Pedido no Salão (sem taxa de entrega)
```http
POST /api/v1/pedidos
Content-Type: application/json

{
  "clienteId": 1,
  "isDelivery": false,
  "taxaEntrega": 0.00,
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 1
    },
    {
      "produtoId": 7,
      "quantidade": 2
    }
  ]
}
```

### Listar Todos os Pedidos
```http
GET /api/v1/pedidos
```

### Buscar Pedido por ID
```http
GET /api/v1/pedidos/1
```

### Atualizar Status do Pedido (método 1)
```http
PUT /api/v1/pedidos/1/status?status=PREPARANDO
```

### Atualizar Status do Pedido (método 2)
```http
PUT /api/v1/pedidos/1/status?status=SAIU_ENTREGA
```

### Atualizar Pedido (Status + Entregador)
```http
PUT /api/v1/pedidos/1
Content-Type: application/json

{
  "entregadorId": 1,
  "status": "SAIU_ENTREGA"
}
```

### Marcar Pedido como Entregue
```http
PUT /api/v1/pedidos/1/status?status=ENTREGUE
```

### Cancelar Pedido
```http
PUT /api/v1/pedidos/1/status?status=CANCELADO
```

### Deletar Pedido Cancelado
```http
DELETE /api/v1/pedidos/1
```
**Nota:** Só funciona se o pedido estiver com status `CANCELADO`

---

## 5. Fluxo Completo de Exemplo

### Passo 1: Verificar Clientes Disponíveis
```http
GET /api/v1/clientes
```

### Passo 2: Verificar Produtos Disponíveis
```http
GET /api/v1/produtos/disponiveis
```

### Passo 3: Criar Pedido
```http
POST /api/v1/pedidos
Content-Type: application/json

{
  "clienteId": 1,
  "isDelivery": true,
  "taxaEntrega": 5.00,
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 1
    },
    {
      "produtoId": 6,
      "quantidade": 2
    }
  ]
}
```

### Passo 4: Verificar Pedido Criado
```http
GET /api/v1/pedidos/1
```

### Passo 5: Atualizar Status para PREPARANDO
```http
PUT /api/v1/pedidos/1/status?status=PREPARANDO
```

### Passo 6: Verificar Entregadores Disponíveis
```http
GET /api/v1/entregadores/disponiveis
```

### Passo 7: Atribuir Entregador e Marcar como SAIU_ENTREGA
```http
PUT /api/v1/pedidos/1
Content-Type: application/json

{
  "entregadorId": 1,
  "status": "SAIU_ENTREGA"
}
```

### Passo 8: Marcar como ENTREGUE
```http
PUT /api/v1/pedidos/1/status?status=ENTREGUE
```

---

## 6. Exemplos de Erros

### Tentar Criar Pedido com Produto Indisponível
```http
POST /api/v1/pedidos
Content-Type: application/json

{
  "clienteId": 1,
  "isDelivery": true,
  "taxaEntrega": 5.00,
  "itens": [
    {
      "produtoId": 999,
      "quantidade": 1
    }
  ]
}
```
**Resposta esperada:** 404 Not Found - Produto não encontrado

### Tentar Deletar Pedido Não Cancelado
```http
DELETE /api/v1/pedidos/1
```
**Resposta esperada:** 400 Bad Request - Apenas pedidos cancelados podem ser deletados

### Tentar Criar Cliente com CPF Duplicado
```http
POST /api/v1/clientes
Content-Type: application/json

{
  "nome": "Outro Cliente",
  "cpf": "123.456.789-00",
  "telefone": "48988888888",
  "email": "outro@email.com",
  "endereco": "Rua B, 456"
}
```
**Resposta esperada:** 400 Bad Request - CPF já cadastrado

---

## 7. Status do Pedido - Valores Válidos

- `PENDENTE` - Pedido criado, aguardando preparo
- `PREPARANDO` - Pedido em preparação
- `SAIU_ENTREGA` - Pedido saiu para entrega
- `ENTREGUE` - Pedido entregue
- `CANCELADO` - Pedido cancelado

## 8. Categoria de Produto - Valores Válidos

- `PIZZA` - Pizzas
- `BEBIDA` - Bebidas
- `SOBREMESA` - Sobremesas

---

## Dica: Usando cURL

### Exemplo de criação de pedido com cURL:
```bash
curl -X POST http://localhost:8080/api/v1/pedidos \
  -H "Content-Type: application/json" \
  -d '{
    "clienteId": 1,
    "isDelivery": true,
    "taxaEntrega": 5.00,
    "itens": [
      {
        "produtoId": 1,
        "quantidade": 2
      }
    ]
  }'
```

---

**Nota:** Todos os exemplos assumem que a aplicação está rodando em `http://localhost:8080`

