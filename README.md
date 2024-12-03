# <h1 align="center"> MICROSSERVIÇO DE PRODUTOS </h1>

## Sumário
1. [Visão Geral](#visão-geral)
2. [Arquitetura](#arquitetura)
3. [Design dos Serviços](#design-dos-serviços)
4. [Endpoints da API](#endpoints-da-api)
5. [Guia de Implantação](#guia-de-implantação)
6. [Guia de Uso](#guia-de-uso)

---

## 1. Visão Geral

**Descrição**:

Este projeto é parte do **Tech Challenge**, uma atividade integrada e obrigatória para consolidação dos conhecimentos adquiridos em todas as disciplinas da pós-graduação de Arquitetura Java da FIAP. O desafio visa o desenvolvimento de um sistema de **Gerenciamento de Pedidos Integrado com Spring e Microsserviços**, explorando as vantagens dessa arquitetura para construir um sistema robusto, escalável e modular.

Este projeto foi realizado em grupo, composto pelos alunos **Eliane , Valter e Paula**, com o objetivo de aplicar os conceitos aprendidos em um ambiente colaborativo, prático e realista de desenvolvimento.

**Propósito e Objetivo**:

O objetivo do sistema é permitir o gerenciamento completo de pedidos, abrangendo desde a gestão de clientes e produtos até o processamento e entrega dos pedidos. A arquitetura é orientada a microsserviços, cada um responsável por uma parte específica do sistema, o que facilita a manutenção, escalabilidade e distribuição de tarefas entre a equipe. O sistema visa:

- **Gestão eficiente de pedidos**: Controle completo do fluxo de pedidos, desde a criação até a entrega final.
- **Modularidade**: Divisão das responsabilidades em diferentes microsserviços, cada um gerenciando uma parte do processo (clientes, produtos, pedidos e logística).
- **Escalabilidade e Desacoplamento**: Cada microsserviço opera de forma autônoma e possui comunicação assíncrona, permitindo maior resiliência e manutenção simplificada.
- **Utilização de tecnologias de ponta**: Implementação de práticas recomendadas e tecnologias avançadas como Spring Boot, Spring Data JPA, Spring Batch e Spring Cloud Stream para comunicação de eventos.

**Funcionalidades Principais**:

1. **Microsserviço de Gerenciamento de Clientes**: Realiza operações CRUD para cadastro, atualização e consulta de clientes.
2. **Microsserviço de Catálogo de Produtos**: Responsável por gerenciar o catálogo e estoque de produtos, além de permitir a importação em massa de dados de produtos via Spring Batch.
3. **Microsserviço de Gestão de Pedidos**: Gerencia todo o ciclo de vida dos pedidos, incluindo o processamento e integração com o serviço de logística para entrega dos produtos.
4. **Microsserviço de Logística de Entrega**: Responsável por coordenar a logística de entrega, desde a atribuição de entregadores até o rastreamento das entregas em tempo real, fornecendo atualizações para os clientes.

---

## 2. Arquitetura

Este projeto adota uma arquitetura de microsserviços, distribuindo responsabilidades em módulos independentes e desacoplados, cada um gerenciando aspectos distintos do sistema de gerenciamento de pedidos. Cada microsserviço opera de forma autônoma, comunicando-se diretamente via APIs REST, garantindo escalabilidade e permitindo a manutenção modular.

### Principais Componentes da Arquitetura

1. **API Gateway**: Atua como ponto de entrada para todas as requisições dos clientes, roteando chamadas para os microsserviços específicos e oferecendo funcionalidades como autenticação e balanceamento de carga. Implementado com Spring Cloud Gateway, facilita a comunicação e segurança do sistema.
2. **Microsserviço de Gerenciamento de Clientes**: Responsável pelo gerenciamento de dados dos clientes, incluindo funcionalidades de CRUD (criação, leitura, atualização e exclusão) para o cadastro de clientes.
3. **Microsserviço de Catálogo de Produtos**: Encapsula a lógica do catálogo de produtos, permitindo a consulta e atualização de informações dos produtos e controle de estoque. Inclui uma funcionalidade de carga em massa, implementada com Spring Batch, para atualização do inventário a partir de arquivos CSV ou outros formatos.
4. **Microsserviço de Gestão de Pedidos**: Centraliza a lógica de pedidos, gerenciando a criação, processamento e atualização dos mesmos. Esse serviço realiza integração com o serviço de clientes e produtos para validações e manutenção de estoque.
5. **Microsserviço de Logística de Entrega**: Encabeça as operações de entrega, como a coordenação de entregadores e o gerenciamento do status de entrega dos pedidos.
6. **Banco de Dados (PostgreSQL)**: Cada microsserviço possui seu próprio esquema no banco de dados para assegurar o isolamento dos dados e facilitar a escalabilidade. A persistência de dados é gerenciada por meio de Spring Data JPA.

### Diagrama de Arquitetura ms produtos

https://miro.com/app/board/uXjVLDN8bq0=/?share_link_id=982478938819


---

## 3. Design dos Serviços

Descreva a arquitetura dos serviços, destacando os principais módulos e suas funções. Inclua dependências externas e as tecnologias usadas (ex., Spring Boot, Hibernate, etc.).

**Exemplo de Estrutura dos Serviços**:

- **Autenticação**: Gerencia login e controle de acesso.
- **Gestão de Sessões**: Manipula o início e término das sessões de estacionamento.
- **Gestão de Pagamentos**: Lida com o processamento e verificação dos pagamentos.
- **Notificações**: Envia notificações de status para os usuários (via email/SMS).

---

## 4. Endpoints da API

Abaixo estão listados os principais endpoints da API, organizados por funcionalidade. Cada endpoint possui uma descrição breve, o método HTTP e parâmetros, quando aplicável.

### Clientes

| Método | Endpoint | Descrição | Parâmetros | Corpo da Requisição |
| --- | --- | --- | --- | --- |
| **POST** | `/cliente` | Cria um novo cliente | - | `ClienteDTO` |
| **PUT** | `/cliente` | Atualiza dados de um cliente | - | `ClienteDTO` |
| **DELETE** | `/cliente/{id}` | Exclui um cliente por ID | `id` (ID do cliente) | - |
| **GET** | `/cliente` | Lista todos os clientes | - | - |
| **GET** | `/cliente/cpf/{cpf}` | Obtém cliente pelo CPF | `cpf` (CPF do cliente) | - |
| **GET** | `/cliente/id/{id}` | Obtém cliente por ID | `id` (ID do cliente) | - |

**Exemplos de uso com `ClienteDTO`:**

- **Criar Cliente** (`POST /cliente`):
    
    ```json
    {
        "id": null,
        "nome": "João Silva",
        "cpf": "123.456.789-00",
        "email": "joao.silva@exemplo.com",
        "senha": "senhaSegura123"
    }
    ```
    
- **Atualizar Cliente** (`PUT /cliente`):
    
    ```json
    {
        "id": 1,
        "nome": "João Silva",
        "cpf": "123.456.789-00",
        "email": "joao.silva@exemplo.com",
        "senha": "novaSenhaSegura123"
    }
    ```
    

### Produtos

| Método | Endpoint | Descrição | Parâmetros | Corpo da Requisição |
| --- | --- | --- | --- | --- |
| **POST** | `/produtos` | Cria um novo produto | - | `ProdutoDTO` |
| **PUT** | `/produtos` | Atualiza dados de um produto | - | `ProdutoDTO` |
| **DELETE** | `/produtos/{id}` | Exclui um produto por ID | `id` (ID do produto) | - |
| **GET** | `/produtos` | Lista todos os produtos | - | - |
| **GET** | `/produtos/{id}` | Obtém produto por ID | `id` (ID do produto) | - |

**Exemplo de `ProdutoDTO` para criar ou atualizar produto**:

```json
{
    "id": null,
    "nome": "Produto Exemplo",
    "preco": 49.90
}
```

---

## 5. Guia de Implantação


### Requisitos

- **Java**: Versão 11 ou superior
- **Maven**: Para gerenciar as dependências
- **Docker** : Para executar o banco de dados em um container  e o RabbitMQ.

### Passos para Implantação

1. Clone o repositório:
    
    ```bash
    
    git clone https://github.com/usuario/repo.git](https://github.com/Grupo-19/msProduto_TechChalenge)
    cd repo
    
    ```
    

4. Inicie o aplicativo:
    
    ```bash
    mvn spring-boot:run
    
    ```
    
5.  Inicie o banco de dados usando Docker:
    
    ```bash
    
    docker run --name postgres-db -e POSTGRES_PASSWORD=123456 -p 5432:5432 -d postgres
    
    ```
6. inicie o RabbitMQ usando o Docker

```
docker run --name rabbitmq -p 5672:5672 -p 15672:15672 -d rabbitmq:3-management
```

    

---

## 6. Guia de Uso

Inclua instruções para que os usuários possam testar e utilizar a aplicação.

### Como Iniciar uma Sessão

1. Enviar uma requisição `POST` para o endpoint `/api/sessoes` com o `condutor_id` e `parquimetro_id`.
2. O servidor retornará o ID da sessão iniciada.

### Como Realizar um Pagamento

1. Enviar uma requisição `POST` para `/api/pagamentos` com o `sessao_id` e `valor` do pagamento.
2. Consultar o status de pagamento usando `GET /api/pagamentos/{id`
