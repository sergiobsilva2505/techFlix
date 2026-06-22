# 🎬 Techflix

## ⚙️ Antes de executar o projeto

O projeto precisa de uma instância do MongoDB executando na porta 27017 para funcionar, na raiz do projeto existe um arquivo do docker compose que cuida dessa dependência caso necessário.

## 🏗️ Arquitetura e decisões técnicas

O projeto foi feito seguindo principalmente a clean architecture com alguns conceitos de DDD. No código temos 3 camadas principais representadas pelos pacotes domain, application e adapter, cada um funcionando como uma camada da clean architecture e mantendo dependências apenas de camadas mais internas. Internamente em cada pacote os arquivos são separados por domínio, agrupando por exemplo todos os UseCases relacionados a vídeo em um único pacote.

A aplicação da clean architecture foi feita baseada em nossa interpretação, e assim decidimos aplicar uma forma simplificada, sem a criação de todas as interfaces presentes em uma aplicação mais completa, o nosso foco foi no que vimos como mais importante para o projeto atual, como as interfaces de gateways e ports de entrada e a separação em camadas evitando dependências de camadas externas.

Durante o desenvolvimento a principal decisão técnica que surgiu foi como faríamos para armazenar e disponibilizar os arquivos de vídeo, depois de pequenos testes fazendo o upload direto para o servidor da aplicação e também para o S3 usando um simulador no docker acabamos decidindo salvar o arquivo direto no banco de dados, assim poderíamos utilizar da capacidade do MongoDB para acessar o arquivo de forma reativa e fazer o streaming direto para o cliente.

Outro desafio técnico que surgiu foi como implementar os testes de integração, começamos usando um banco integrado em memória, mas logo depois decidimos trocar por uma representação mais próxima da realidade da aplicação. A decisão final foi criar os testes com a ajuda do framework Testcontainers, assim conseguimos chegar mais próximo da infraestrutura de produção do banco.

## 🛠️ Tecnologias

- Como linguagem base usamos ☕ Java na versão 21 LTS.
- O Framework utilizado foi o 🍃 Spring Boot que simplifica a configuração e o desenvolvimento.
    - Spring Web MVC para tratar as requisições HTTP, fazer o mapeamento de URLs e a comunicação entre a camada de apresentação e a camada de negócio.
    - Spring WebFlux para suportar requisições não bloqueantes e reativas, principalmente no streaming dos vídeos.
    - O Spring Validation foi utilizado para validar os dados de entrada da API, para assegurar a integridade dos dados.
    - O Spring Data MongoDB foi utilizado para facilitar a persistência dos dados.
    - O Spring Data Reactive MongoDB foi utilizado para facilitar a criação e gerenciamento dos dados de forma não bloqueante.

## 🔧 Ferramentas

- 💡 IntelliJ IDEA
- 🌙 Insomnia
- 🐙 Git
- 📦 Maven
- 🤖 Copilot
- 🚀 Spring Initializr
- 🍃 MongoDB

## 📖 Guia de uso

- ### 👤 API de Usuários:

  <details>
    <summary>➕ Cadastrar um usuário</summary>
    ...
  </details>

  <details>  
   <summary>🔍 Retornar um usuário específico</summary>
    ...
  </details>

  <details>  
   <summary>📋 Listar Usuários</summary>
    ...
  </details>

- ### 🏷️ API de Categoria:
  <details>
   <summary>➕ Cadastrar uma Categoria</summary>
    ...
  </details>

  <details>
    <summary>🔍 Buscar uma categoria</summary>
    ...
  </details>

- ### 🎥 API de Vídeo:

  <details>
    <summary>⬆️ Fazer o upload do vídeo</summary>
    ...
  </details>

  <details>
  <summary>📢 Publicar um vídeo</summary>
    ...
  </details>

  <details>
  <summary>✏️ Atualizar um vídeo</summary>
    ...
  </details>

  <details>
  <summary>🔍 Buscar um vídeo</summary>
    ...
  </details>

  <details>
  <summary>📋 Buscar todos os vídeos</summary>
    ...
  </details>

  <details>
  <summary>🔎 Buscar vídeos (com filtros)</summary>
    ...
  </details>

  <details>
  <summary>🗑️ Deletar um vídeo</summary>
    ...
  </details>

  <details>
    <summary>💡 Recomendações</summary>
    ...
  </details>

  <details>
    <summary>▶️ Assistir Vídeo</summary>
    ...
  </details>

  <details>
    <summary>📊 Relatório de vídeos</summary>
    ...
  </details>

- ### 🔖 API de Bookmark:
  
  <details>
    <summary>➕ Criar um Bookmark</summary>
    ...
  </details>
  
  <details>
    <summary>🗑️ Deletar um Bookmark</summary>
    ...
  </details>
  
  <details>
    <summary>📋 Retornar todos os bookmarks</summary>
    ...
  </details>

  <details>
    <summary>🔍 Retornar um bookmark por id</summary>
    ...
  </details>
