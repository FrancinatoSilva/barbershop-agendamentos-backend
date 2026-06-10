<div align="center">

# ✂️ Barbershop Agendamentos — Backend

**API REST para gerenciamento de agendamentos de barbearia.**  
Construída com Spring Boot 4, expõe endpoints para cadastro de clientes, serviços e criação de agendamentos com validação de conflito de horário.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.6-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Wrapper-C71A36?style=flat-square&logo=apachemaven&logoColor=white)
![H2](https://img.shields.io/badge/H2-In--Memory-blue?style=flat-square)
![Status](https://img.shields.io/badge/status-MVP-yellow?style=flat-square)

</div>

---

## Índice

- [Sobre](#sobre)
- [Stack](#stack)
- [Arquitetura](#arquitetura)
- [Pré-requisitos](#pré-requisitos)
- [Rodando localmente](#rodando-localmente)
- [Console H2](#console-h2)
- [API Reference](#api-reference)
    - [Clientes](#clientes)
    - [Serviços](#serviços)
    - [Agendamentos](#agendamentos)
- [Schemas](#schemas)
- [Erros](#erros)
- [Roadmap](#roadmap)

---

## Sobre

Projeto em construção. Este repositório contém apenas o **backend** da aplicação — o frontend será desenvolvido em repositório separado.

O objetivo do MVP é validar o fluxo central: cadastrar clientes e serviços, e criar agendamentos com verificação de conflito de horário.

---

## Stack

| Tecnologia | Versão | Papel |
|---|---|---|
| Java | 17 | Linguagem base |
| Spring Boot | 4.0.6 | Framework principal |
| Spring Data JPA | — | Persistência / ORM |
| Hibernate | — | Implementação JPA |
| H2 | — | Banco em memória (dev) |
| Lombok | — | Redução de boilerplate |
| Maven Wrapper | — | Build e dependências |

---

## Arquitetura

A aplicação segue arquitetura em camadas padrão do Spring:

```
HTTP Request
     │
     ▼
┌─────────────┐
│  Controller │  @RestController — recebe requisições, delega ao service
└──────┬──────┘
       │
       ▼
┌─────────────┐
│   Service   │  Regras de negócio (validação de conflito, busca de entidades)
└──────┬──────┘
       │
       ▼
┌─────────────┐
│ Repository  │  JpaRepository — acesso ao banco de dados
└──────┬──────┘
       │
       ▼
┌─────────────┐
│   H2 DB     │  Banco em memória (dados perdidos ao reiniciar)
└─────────────┘
```

**Pacotes do projeto:**

```
br.com.natodev.gerenciadordeagendamentosbarbershop
├── controller/      # Endpoints REST
├── service/         # Regras de negócio
├── repository/      # Interfaces JPA
├── domain/          # Entidades e enums
├── dto/             # Objetos de transferência de dados
└── exception/       # Tratamento global de erros
```

> **Tratamento de erros:** o `GlobalExceptionHandler` captura `IllegalArgumentException` e retorna `400 Bad Request` com `{"erro": "mensagem"}`.

---

## Pré-requisitos

- **JDK 17+** — verifique com `java -version`
- **Git** — para clonar o repositório
- Maven Wrapper incluso — não requer instalação separada

---

## Rodando localmente

**1. Clone o repositório**

```bash
git clone https://github.com/FrancinatoSilva/barbershop-agendamentos-backend.git
cd barbershop-agendamentos-backend
```

**2. Suba a aplicação**

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

**3. Verifique**

A API estará disponível em `http://localhost:8080`.

```bash
curl http://localhost:8080/clientes
```

> ⚠️ O banco é em memória — todos os dados são perdidos ao encerrar a aplicação.

---

## Console H2

Disponível em `http://localhost:8080/h2-console` com as credenciais abaixo:

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:barbeariadb` |
| Usuário | `sa` |
| Senha | *(vazio)* |

---

## API Reference

Base URL: `http://localhost:8080`

---

### Clientes

#### `POST /clientes`
Cria um novo cliente.

**Request body:**
```json
{
  "nome": "Lucas Alves",
  "telefone": "(85) 99999-1234",
  "email": "lucas@email.com"
}
```

**Response `201 Created`:**
```json
{
  "id": 1,
  "nome": "Lucas Alves",
  "telefone": "(85) 99999-1234",
  "email": "lucas@email.com"
}
```

---

#### `GET /clientes`
Lista todos os clientes cadastrados.

**Response `200 OK`:**
```json
[
  {
    "id": 1,
    "nome": "Lucas Alves",
    "telefone": "(85) 99999-1234",
    "email": "lucas@email.com"
  }
]
```

---

### Serviços

#### `POST /servicos`
Cria um novo serviço no catálogo.

**Request body:**
```json
{
  "descricao": "Corte degradê",
  "preco": 35.00,
  "tempoEstimadoMinutos": 40
}
```

**Response `201 Created`:**
```json
{
  "id": 1,
  "descricao": "Corte degradê",
  "preco": 35.00,
  "tempoEstimadoMinutos": 40
}
```

---

#### `GET /servicos`
Lista todos os serviços disponíveis.

**Response `200 OK`:**
```json
[
  {
    "id": 1,
    "descricao": "Corte degradê",
    "preco": 35.00,
    "tempoEstimadoMinutos": 40
  }
]
```

---

### Agendamentos

> **Regra de negócio:** não é possível criar dois agendamentos com o mesmo `dataHora`. Conflitos retornam `400 Bad Request`.

#### `POST /agendamentos`
Cria um novo agendamento.

**Request body:**
```json
{
  "clienteId": 1,
  "servicoId": 1,
  "dataHora": "2025-08-15T14:00:00"
}
```

**Response `201 Created`:**
```json
{
  "id": 1,
  "cliente": {
    "id": 1,
    "nome": "Lucas Alves",
    "telefone": "(85) 99999-1234",
    "email": "lucas@email.com"
  },
  "servico": {
    "id": 1,
    "descricao": "Corte degradê",
    "preco": 35.00,
    "tempoEstimadoMinutos": 40
  },
  "dataHora": "2025-08-15T14:00:00",
  "status": "PENDENTE"
}
```

**Response `400 Bad Request`:**
```json
{
  "erro": "Este horário já está reservado. Por favor, escolha outro."
}
```

---

#### `GET /agendamentos`
Lista todos os agendamentos com clientes e serviços embutidos.

**Response `200 OK`:** array do mesmo objeto acima.

---

## Schemas

### `Cliente`

| Campo | Tipo | Obrigatório | Descrição |
|---|---|---|---|
| `id` | `Long` | auto | Gerado automaticamente |
| `nome` | `String` | ✓ | Nome completo |
| `telefone` | `String` | ✓ | Telefone / WhatsApp |
| `email` | `String` | — | Endereço de e-mail |

### `Servico`

| Campo | Tipo | Obrigatório | Descrição |
|---|---|---|---|
| `id` | `Long` | auto | Gerado automaticamente |
| `descricao` | `String` | ✓ | Nome do serviço |
| `preco` | `BigDecimal` | ✓ | Valor cobrado |
| `tempoEstimadoMinutos` | `Integer` | — | Duração estimada |

### `Agendamento`

| Campo | Tipo | Obrigatório | Descrição |
|---|---|---|---|
| `id` | `Long` | auto | Gerado automaticamente |
| `cliente` | `Cliente` | ✓ | Definido via `clienteId` no request |
| `servico` | `Servico` | ✓ | Definido via `servicoId` no request |
| `dataHora` | `LocalDateTime` | ✓ | ISO-8601: `yyyy-MM-ddTHH:mm:ss`. Deve ser único. |
| `status` | `StatusAgendamento` | auto | Definido como `PENDENTE` na criação |

### `StatusAgendamento`

| Valor | Descrição |
|---|---|
| `PENDENTE` | Estado inicial após criação |
| `CONFIRMADO` | Agendamento confirmado |
| `CONCLUIDO` | Atendimento realizado |
| `CANCELADO` | Agendamento cancelado |

> As transições de status ainda não estão expostas via endpoint — os estados estão declarados e prontos para as próximas versões.

---

## Erros

Todos os erros de regra de negócio retornam `400 Bad Request` com o formato:

```json
{
  "erro": "Mensagem descritiva do problema"
}
```

| Mensagem | Causa |
|---|---|
| `Este horário já está reservado. Por favor, escolha outro.` | Conflito de `dataHora` |
| `Cliente com ID: {n} não foi encontrado.` | `clienteId` inválido |
| `Serviço com ID {n} não foi encontrado` | `servicoId` inválido |

---

## Roadmap

- [ ] Autenticação com Spring Security + JWT
- [ ] Endpoints para transição de status (confirmar, concluir, cancelar)
- [ ] Entidade `Barbeiro` vinculada aos agendamentos
- [ ] Migração de H2 para PostgreSQL com Flyway
- [ ] Bean Validation nos campos dos requests
- [ ] Documentação interativa com SpringDoc / OpenAPI (Swagger UI)
