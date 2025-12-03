🍕 API de Pizzaria / Delivery

API REST desenvolvida em Spring Boot, projetada para gerenciamento completo de pizzaria e sistema de entregas.

📋 Visão Geral

Este projeto oferece uma solução completa para operações de pizzarias, permitindo:

👥 Cadastro e gestão de clientes

🍽️ Administração de produtos (pizzas, bebidas, sobremesas)

📦 Criação e acompanhamento de pedidos

🚴 Gerenciamento de entregadores

💰 Cálculo automático de valores

📊 Histórico de pedidos

🛠 Tecnologias Utilizadas

Java 17

Spring Boot 3.2.0

Spring Data JPA

Spring Validation

H2 Database (desenvolvimento)

PostgreSQL (produção)

Lombok

MapStruct

Springdoc OpenAPI (Swagger)

📁 Estrutura do Projeto
com.exemplo.pizzaria
├── config
├── domain
│   ├── entity          # Entidades JPA
│   ├── repository      # Repositórios
│   ├── service         # Regras de negócio
│   ├── exception       # Exceções personalizadas
│   └── enums           # Enumeradores
├── dto
│   ├── request         # DTOs de entrada
│   └── response        # DTOs de saída
├── mapper              # Mapeamento com MapStruct
└── resource            # Controladores REST

🚀 Como Executar
Pré-requisitos

Java 17+

Maven 3.6+ ou Gradle

Passos

Clone o repositório ou extraia o ZIP

Acesse a pasta do projeto:

cd back-end


Execute a aplicação:

Maven

mvn spring-boot:run


Gradle

./gradlew bootRun

Acesso

API: http://localhost:8080

Swagger UI: http://localhost:8080/swagger-ui.html

H2 Console: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:pizzaria

Usuário: sa

Senha: (vazio)

🔌 Endpoints
Base URL: /api/v1
👥 Clientes
Método	Endpoint	Descrição
GET	/clientes	Lista todos
GET	/clientes/{id}	Busca por ID
POST	/clientes	Cria cliente
PUT	/clientes/{id}	Atualiza
DELETE	/clientes/{id}	Remove
🍽️ Produtos
Método	Endpoint	Descrição
GET	/produtos	Lista todos
GET	/produtos/disponiveis	Lista apenas disponíveis
GET	/produtos/{id}	Busca por ID
POST	/produtos	Cria
PUT	/produtos/{id}	Atualiza
DELETE	/produtos/{id}	Remove
🚴 Entregadores
Método	Endpoint	Descrição
GET	/entregadores	Lista todos
GET	/entregadores/disponiveis	Lista disponíveis
GET	/entregadores/{id}	Busca
POST	/entregadores	Cria
PUT	/entregadores/{id}	Atualiza
📦 Pedidos
Método	Endpoint	Descrição
GET	/pedidos	Lista
GET	/pedidos/{id}	Busca por ID
POST	/pedidos	Cria novo pedido
PUT	/pedidos/{id}	Atualiza (status/entregador)
PUT	/pedidos/{id}/status?status=STATUS	Atualiza status
DELETE	/pedidos/{id}	Remove (somente CANCELADO)
📝 Exemplos de Requisição
Criar Cliente
{
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "telefone": "48999990000",
  "email": "joao@email.com",
  "endereco": "Rua das Flores, 123, Centro"
}

Criar Produto
{
  "nome": "Pizza Calabresa",
  "descricao": "Calabresa, cebola, mussarela e orégano",
  "preco": 39.90,
  "categoria": "PIZZA",
  "disponivel": true
}

Criar Pedido
{
  "clienteId": 1,
  "isDelivery": true,
  "taxaEntrega": 5.00,
  "itens": [
    { "produtoId": 1, "quantidade": 2 },
    { "produtoId": 6, "quantidade": 1 }
  ]
}

Atualizar Status
PUT /api/v1/pedidos/1/status?status=PREPARANDO

⚙️ Regras de Negócio
Criar Pedido

Valida disponibilidade dos produtos

Calcula subtotal por item

Soma total + taxa de entrega

Exclusão

Só permite excluir pedidos CANCELADOS

Atualização de Status

IMPOSSÍVEL alterar status de ENTREGUE ou CANCELADO

Fluxo recomendado:
PENDENTE → PREPARANDO → SAIU_ENTREGA → ENTREGUE

Validações

CPF e e-mail únicos

Produto indisponível não pode ser adicionado

Entregador deve estar disponível

🗃️ Modelo de Dados
Entidades

Cliente

Produto

Entregador

Pedido

ItemPedido

Relacionamentos

Cliente 1 → * Pedido

Entregador 1 → * Pedido

Pedido 1 → * ItemPedido

Produto 1 → * ItemPedido

Enums

StatusPedido: PENDENTE, PREPARANDO, SAIU_ENTREGA, ENTREGUE, CANCELADO

Categoria: PIZZA, BEBIDA, SOBREMESA

🧪 Dados de Teste (pré-carregados)

3 clientes

3 entregadores (2 disponíveis)

5 pizzas

4 bebidas

3 sobremesas

⚠️ Erros e Respostas

Exemplo:

{
  "timestamp": "2025-01-30T20:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Mensagem de erro",
  "errors": {
    "campo": "Erro específico do campo"
  }
}


Códigos HTTP relevantes:

200 – OK

201 – Criado

400 – Erro de validação

404 – Não encontrado

409 – Conflito

500 – Erro interno

⚙️ Configuração (PostgreSQL – Produção)
spring.datasource.url=jdbc:postgresql://localhost:5432/pizzaria
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

📚 Documentação

Swagger UI: http://localhost:8080/swagger-ui.html

OpenAPI JSON: http://localhost:8080/api-docs

💻 Desenvolvimento

Este projeto segue boas práticas:

Arquitetura em camadas

DTOs para isolamento

MapStruct para mapeamento

Validações com Bean Validation

Exceptions centralizadas

Regras nos services

Versionamento /api/v1

📄 Licença

Projeto desenvolvido para fins acadêmicos.
