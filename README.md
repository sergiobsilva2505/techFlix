# 🎬 Techflix

<div align="center">

![Java](https://img.shields.io/badge/Java-21-ED8800?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.0-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-7.0.4-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Testcontainers](https://img.shields.io/badge/Testcontainers-2496ED?style=for-the-badge&logo=docker&logoColor=white)

*Plataforma de streaming de vídeos construída com Java 21 + Spring Boot e arquitetura limpa*

</div>

---

## Sumário

- [Pré-requisitos](#-pré-requisitos)
- [Como executar](#-como-executar)
- [Arquitetura](#-arquitetura-e-decisões-técnicas)
- [Tecnologias](#-tecnologias)
- [Ferramentas](#-ferramentas)
- [API Reference](#-guia-de-uso-da-api)

---

## ✅ Pré-requisitos

- Java 21+
- Docker e Docker Compose (para o MongoDB)
- Maven 3.x

---

## 🚀 Como executar

1. Suba o MongoDB com Docker Compose (arquivo na raiz do projeto):

```bash
docker compose up -d
```

2. Execute a aplicação:

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## 🏗️ Arquitetura e decisões técnicas

O projeto segue **Clean Architecture** com influências de **DDD**, organizado em três camadas principais:

```
src/
└── main/
    └── java/
        └── com.techflix/
            ├── domain/       # Entidades, regras de negócio, interfaces de gateway
            ├── application/  # Casos de uso (UseCases)
            └── adapter/      # Controllers, repositórios, configurações externas
```

Cada camada depende apenas de camadas mais internas. Dentro de cada pacote, os arquivos são agrupados por domínio (ex.: todos os UseCases de vídeo em um único pacote).

A implementação é simplificada intencionalmente: mantemos as interfaces de gateway e ports de entrada como elementos centrais, sem criar todas as abstrações de uma arquitetura mais verbosa.

### Armazenamento de vídeo

Após avaliar upload direto para servidor e para S3 (via simulador Docker), optamos por **armazenar os arquivos de vídeo diretamente no MongoDB**. Isso permite explorar a capacidade reativa do banco para fazer streaming do vídeo diretamente ao cliente, sem intermediários.

### Testes de integração

Iniciamos com banco em memória e migramos para **Testcontainers**, que provisiona um container MongoDB real durante os testes — aproximando o ambiente de teste da infraestrutura de produção.

---

## 🛠️ Tecnologias

| Tecnologia | Uso |
|---|---|
| **Java 21 LTS** | Linguagem base |
| **Spring Boot 4.1.0** | Framework principal |
| **Spring Web MVC** | Mapeamento de rotas HTTP e camada de apresentação |
| **Spring WebFlux** | Requisições reativas e não bloqueantes (streaming de vídeo) |
| **Spring Validation** | Validação de dados de entrada da API |
| **Spring Data MongoDB** | Persistência de dados |
| **Spring Data Reactive MongoDB** | Persistência não bloqueante |
| **Testcontainers** | Testes de integração com container real |

---

## 🔧 Ferramentas

| Ferramenta | Finalidade |
|---|---|
| IntelliJ IDEA | IDE |
| Insomnia | Testes de API |
| Git | Controle de versão |
| Maven | Build e dependências |
| GitHub Copilot | Assistência de código |
| Spring Initializr | Scaffolding do projeto |
| MongoDB Compass | Visualização do banco |

---

## 📖 Guia de uso da API

### 👤 Usuários

<details>
<summary>➕ Cadastrar um usuário</summary>

**POST** `/users`

```json
{
  "name": "João Silva",
  "email": "joao@email.com"
}
```

**Resposta:** `201 Created`

```json
{
  "id": "64f1a2b3c4d5e6f7a8b9c0d1",
  "name": "João Silva",
  "email": "joao@email.com"
}
```

</details>

<details>
<summary>🔍 Buscar um usuário por ID</summary>

**GET** `/users/{id}`

**Resposta:** `200 OK`

```json
{
  "id": "64f1a2b3c4d5e6f7a8b9c0d1",
  "name": "João Silva",
  "email": "joao@email.com"
}
```

</details>

<details>
<summary>📋 Listar todos os usuários</summary>

**GET** `/users`

**Resposta:** `200 OK` — array de usuários.

</details>

---

### 🏷️ Categorias

<details>
<summary>➕ Cadastrar uma categoria</summary>

**POST** `/categories`

```json
{
  "name": "Tecnologia"
}
```

**Resposta:** `201 Created`

</details>

<details>
<summary>🔍 Buscar uma categoria por ID</summary>

**GET** `/categories/{id}`

</details>

---

### 🎥 Vídeos

<details>
<summary>⬆️ Upload de vídeo</summary>

**POST** `/videos/upload`

- Content-Type: `multipart/form-data`
- Campo: `file` (arquivo de vídeo)

**Resposta:** `201 Created` com o ID do vídeo criado.

</details>

<details>
<summary>📢 Publicar um vídeo</summary>

**PATCH** `/videos/{id}/publish`

Altera o status do vídeo para publicado, tornando-o visível para os usuários.

</details>

<details>
<summary>✏️ Atualizar metadados do vídeo</summary>

**PUT** `/videos/{id}`

```json
{
  "title": "Novo título",
  "description": "Nova descrição",
  "categoryId": "64f1a2b3c4d5e6f7a8b9c0d2"
}
```

</details>

<details>
<summary>🔍 Buscar um vídeo por ID</summary>

**GET** `/videos/{id}`

</details>

<details>
<summary>📋 Listar todos os vídeos</summary>

**GET** `/videos`

</details>

<details>
<summary>🔎 Buscar vídeos com filtros</summary>

**GET** `/videos?title=java&categoryId=xxx&page=0&size=10`

Parâmetros disponíveis: `title`, `categoryId`, `page`, `size`.

</details>

<details>
<summary>🗑️ Deletar um vídeo</summary>

**DELETE** `/videos/{id}`

**Resposta:** `204 No Content`

</details>

<details>
<summary>💡 Recomendações</summary>

**GET** `/videos/recommendations?userId={userId}`

Retorna vídeos recomendados com base no histórico do usuário.

</details>

<details>
<summary>▶️ Assistir vídeo (streaming)</summary>

**GET** `/videos/{id}/stream`

Realiza streaming reativo do vídeo diretamente do MongoDB. Suporta o header `Range` para reprodução parcial.

</details>

<details>
<summary>📊 Relatório de vídeos</summary>

**GET** `/videos/report`

Retorna estatísticas de visualizações, publicações e categorias.

</details>

---

### 🔖 Bookmarks

<details>
<summary>➕ Criar um bookmark</summary>

**POST** `/bookmarks`

```json
{
  "userId": "64f1a2b3c4d5e6f7a8b9c0d1",
  "videoId": "64f1a2b3c4d5e6f7a8b9c0d3"
}
```

</details>

<details>
<summary>🗑️ Deletar um bookmark</summary>

**DELETE** `/bookmarks/{id}`

**Resposta:** `204 No Content`

</details>

<details>
<summary>📋 Listar todos os bookmarks de um usuário</summary>

**GET** `/bookmarks?userId={userId}`

</details>

<details>
<summary>🔍 Buscar um bookmark por ID</summary>

**GET** `/bookmarks/{id}`

</details>
