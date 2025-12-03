# API Pizzaria / Delivery

API REST desenvolvida em **Spring Boot** para gerenciamento de pizzaria e sistema de delivery.

## ?? Sobre o Projeto

Sistema completo para gerenciar:
- ? Cadastro e gerenciamento de clientes
- ? Cadastro de produtos (pizzas, bebidas, sobremesas) com controle de disponibilidade
- ? Criação e acompanhamento de pedidos
- ? Atribuição de entregadores para delivery
- ? Cálculo automático de valores
- ? Histórico de pedidos

## ??? Tecnologias

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Validation**
- **H2 Database** (desenvolvimento)
- **PostgreSQL** (produção)
- **Lombok**
- **MapStruct**
- **Springdoc OpenAPI** (Swagger)

## ?? Estrutura do Projeto

```
com.exemplo.pizzaria
?? config
?? domain
?  ?? entity          # Entidades JPA
?  ?? repository      # Repositórios
?  ?? service         # Lógica de negócio
?  ?? exception       # Exceções customizadas
?  ?? enums           # Enumeradores
?? dto
?  ?? request         # DTOs de entrada
?  ?? response        # DTOs de saída
?? mapper             # MapStruct mappers
?? resource           # Controllers REST
```

## ?? Como Executar

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+

### Passos

1. **Clone o repositório** (ou extraia o arquivo ZIP)

2. **Navegue até o diretório do projeto**
   ```bash
   cd back-end
   ```

3. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```
   
   Ou usando Gradle:
   ```bash
   ./gradlew bootRun
   ```

4. **Acesse a aplicação**
   - API: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - H2 Console: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:pizzaria`
     - Usuário: `sa`
     - Senha: (vazio)

## ?? Endpoints da API

Base URL: `http://localhost:8080/api/v1`

### Clientes

- `GET /clientes` - Lista todos os clientes
- `GET /clientes/{id}` - Busca cliente por ID
- `POST /clientes` - Cria novo cliente
- `PUT /clientes/{id}` - Atualiza cliente
- `DELETE /clientes/{id}` - Deleta cliente

### Produtos

- `GET /produtos` - Lista todos os produtos
- `GET /produtos/disponiveis` - Lista produtos disponíveis
- `GET /produtos/{id}` - Busca produto por ID
- `POST /produtos` - Cria novo produto
- `PUT /produtos/{id}` - Atualiza produto
- `DELETE /produtos/{id}` - Deleta produto

### Entregadores

- `GET /entregadores` - Lista todos os entregadores
- `GET /entregadores/disponiveis` - Lista entregadores disponíveis
- `GET /entregadores/{id}` - Busca entregador por ID
- `POST /entregadores` - Cria novo entregador
- `PUT /entregadores/{id}` - Atualiza entregador

### Pedidos

- `GET /pedidos` - Lista todos os pedidos
- `GET /pedidos/{id}` - Busca pedido por ID
- `POST /pedidos` - Cria novo pedido
- `PUT /pedidos/{id}` - Atualiza pedido (status e entregador)
- `PUT /pedidos/{id}/status?status=STATUS` - Atualiza apenas o status
- `DELETE /pedidos/{id}` - Deleta pedido (apenas se CANCELADO)

## ?? Exemplos de Requisições

### Criar Cliente

```json
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

### Criar Produto

```json
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

### Criar Pedido

```json
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

### Atualizar Status do Pedido

```
PUT /api/v1/pedidos/1/status?status=PREPARANDO
```

### Atualizar Pedido (Status + Entregador)

```json
PUT /api/v1/pedidos/1
Content-Type: application/json

{
  "entregadorId": 1,
  "status": "SAIU_ENTREGA"
}
```

## ?? Regras de Negócio

1. **Criação de Pedido**:
   - Valida se todos os produtos estão disponíveis
   - Calcula automaticamente o subtotal de cada item
   - Calcula o total do pedido (soma dos itens + taxa de entrega)

2. **Exclusão de Pedido**:
   - Apenas pedidos com status `CANCELADO` podem ser deletados

3. **Atualização de Status**:
   - Não é possível alterar status de pedidos `ENTREGUE` ou `CANCELADO`
   - Fluxo sugerido: `PENDENTE` ? `PREPARANDO` ? `SAIU_ENTREGA` ? `ENTREGUE`

4. **Validações**:
   - CPF e Email únicos para clientes
   - Produtos indisponíveis não podem ser adicionados ao pedido
   - Entregador deve estar disponível para ser atribuído

## ??? Modelo de Dados

### Entidades Principais

- **Cliente**: Dados do cliente (nome, CPF, telefone, email, endereço)
- **Produto**: Produtos do cardápio (nome, descrição, preço, categoria, disponibilidade)
- **Entregador**: Dados do entregador (nome, telefone, disponibilidade)
- **Pedido**: Pedido do cliente (cliente, entregador, itens, status, total)
- **ItemPedido**: Item do pedido (produto, quantidade, preço unitário, subtotal)

### Relacionamentos

- Cliente 1 --- * Pedido
- Entregador 1 --- * Pedido
- Pedido 1 --- * ItemPedido
- Produto 1 --- * ItemPedido

### Enums

- **StatusPedido**: `PENDENTE`, `PREPARANDO`, `SAIU_ENTREGA`, `ENTREGUE`, `CANCELADO`
- **Categoria**: `PIZZA`, `BEBIDA`, `SOBREMESA`

## ?? Dados de Teste

O projeto já vem com dados iniciais carregados automaticamente:

- 3 clientes de exemplo
- 3 entregadores (2 disponíveis)
- 5 pizzas
- 4 bebidas
- 3 sobremesas

## ?? Tratamento de Erros

A API retorna erros padronizados:

```json
{
  "timestamp": "2025-01-30T20:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Mensagem de erro",
  "errors": {
    "campo": "Erro específico do campo"
  }
}
```

Códigos HTTP:
- `200` - Sucesso
- `201` - Criado
- `400` - Bad Request (validação)
- `404` - Not Found
- `409` - Conflict (violação de integridade)
- `500` - Internal Server Error

## ?? Configuração

### application.properties

O arquivo está configurado para usar H2 em memória. Para usar PostgreSQL em produção:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/pizzaria
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## ?? Documentação

A documentação completa da API está disponível via Swagger:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

## ?? Desenvolvimento

Projeto desenvolvido para trabalho acadêmico, seguindo boas práticas:

- ? Arquitetura em camadas
- ? DTOs para isolamento de entidades
- ? Mappers (MapStruct) para conversão
- ? Validações com Bean Validation
- ? Tratamento centralizado de exceções
- ? Regras de negócio nos services
- ? Versionamento de API (`/api/v1`)

## ?? Licença

Este projeto foi desenvolvido para fins acadêmicos.

---

**Desenvolvido com ?? usando Spring Boot**

