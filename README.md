# Techflix

## Arquitetura e decisões técnicas

O projeto foi feito seguindo principalmente a clean architecture com alguns conceitos de DDD. No código temos 3 camadas principais representadas pelos pacotes domain, application e adapter, cada um funcionando como uma camada da clean architecture e mantendo dependências apenas de camadas mais internas. Internamente em cada pacote os arquivos são separados por domínio, agrupando por exemplo todos os UseCases relacionados a vídeo em um único pacote.

A aplicação da clean architecture foi feita baseada em nossa interpretação, e assim decidimos aplicar uma forma simplificada, sem a criação de todas as interfaces presentes em uma aplicação mais completa, o nosso foco foi no que vimos como mais importante para o projeto atual, como as interfaces de gateways e ports de entrada e a separação em camadas evitando dependências de camadas externas.

Durante o desenvolvimento a primeira decisão técnica maior que surgiu foi como faríamos para armazenar os arquivos de vídeo, depois de pequenos testes fazendo o upload direto para o servidor da aplicação e também para o S3 usando um simulador no docker acabamos decidindo salvar o arquivo direto no banco de dados, assim poderíamos utilizar da capacidade do MongoDB de acessar o arquivo de forma reativa e fazer o streaming direto para o cliente.

Outro desafio técnico que surgiu foi como implementar os testes de integração, começamos usando um banco integrado em memória, mas logo depois decidimos trocar por uma representação mais próxima da realidade da aplicação. A decisão final foi criar os testes com a ajuda do framework Testcontaines, assim conseguimos chegar mais próximo da infraestrutura de produção do banco.

## Tecnologias

- Como linguagem base usamos Java na versão 21 LTS.
- O Framework utilizado foi o Spring Boot que simplifica a configuração e o desenvolvimento.
    - Spring Web MVC para tratar as requisições HTTP, fazer o mapeamento de URLs e a comunicação entre a camada de apresentação e a camada de negócio.
    - Spring WebFlux para supoertar requisições não bloqueantes e reativas, principalmente no streaming dos vídeos.
    - O Spring Validation foi utilizado para validar os dados de entrada da API, para assegurar a integridade dos dados.
    - O Spring Data MongoDB foi utilizado para facilitar a persistência dos dados.
    - O Spring Data Reactive MongoDB foi utilizado para facilitar a criação e gerenciamento dos dados de forma não bloqueante.

## Ferramentas

- IntelliJ IDEA
- Insonmia
- Git
- Maven
- Copilot
- Spring initializr
- MongoDB

## Guia de uso

- ### API de Usuários:

  <details>
    <summary>Cadastrar um usuário</summary>

    - POST: http://localhost:8080/users/
      - Request:
        ```bash
          curl -X POST 'localhost:8080/users' -H 'Content-Type: application/json' \
          --data '{
              "name": "João Augusto de Oliveira",
              "email": "joaoaugusto@gmail.com",
              "password": "12345678"
          }'
        ```
      - Response 201:
        No body returned for response
  </details>

  <details>  
   <summary>Retornar um usuário específico</summary>

    - GET: http://localhost:8080/users/{id} *(id do usuário buscado)*
      - Request:
        ```bash
          curl -X GET 'http://localhost:8080/users/65b56126f46a7a218eb91131'
        ```
      - Response 200:
        ```json
          {
            "name": "Sérgio",
            "email": "mail@m.com"
          }
        ```
      - Response 404:
        ```json
          {
            "type": "about:blank",
            "title": "Bad Request",
            "status": 400,
            "detail": "User not found",
            "instance": "/users/65b56126f46a7a218eb91131"
          }
        ```
  </details>

  <details>  
   <summary>Listar Usuários</summary>

    - GET: http://localhost:8080/users/
      - Request:
        ```bash
          curl --request GET \
          --url http://localhost:8080/users
        ```
        
      - Response 200:
        ```json
          {
            "content": [
              {
                "name": "Sérgio",
                "email": "sergio@m.com"
              },
              {
                "name": "Lucas",
                "email": "lucas@m.com"
              },
              {
                "name": "Kelly",
                "email": "kelly@m.com"
              }
            ],
            "totalPages": 1,
            "totalElements": 3,
            "currentPage": 0,
            "elementsPerPage": 10
        }
        ```
  </details>

- ### API de Categoria:
  <details>
   <summary>Cadastrar uma Categoria</summary>

    - POST: http://localhost:8080/categories
      - Request:
        ```bash
          curl -X POST 'localhost:8080/categories' \
          -H 'Content-Type: application/json' \
          --data '{
              "name": "animation"
            }'
        ```
      - Response 201:
          No body returned for response
          
      - Response 400
        ```json
          {
            "type": "about:blank",
            "title": "Bad Request",
            "status": 400,
            "detail": "Category name can't be empty or null.",
            "instance": "/categories"
          }
        ```
    </details>

  <details>
    <summary>Buscar uma categoria</summary>
    
    - GET: http://localhost:8080/categories/{id} *(id da categoria buscada)*
        - Request:
          ```bash
            curl -X GET 'localhost:8080/categories/65abbc60252b6124cbb4c9fd'
          ```
        - Response 200:
          ```json
            {
              "name" : "animation"
            }
          ```
    
        - Response 404:
          ```json
              {
                "type": "about:blank",
                "title": "Bad Request",
                "status": 400,
                "detail": "Category not found",
                "instance": "/categories/animation23"
              }
          ```
  </details>

- ### API de Vídeo:

  <details>
    <summary>Fazer o upload do vídeo</summary>

    - POST: http://localhost:8080/videos/upload
        - Request:
          ```bash
            curl -X POST 'localhost:8080/videos/upload' \
            -H 'Content-Type: multipart/form-data' \
            -F file="@/path/to/file.mp4"
          ```
        - Response 200:
          ```json
            {
              "id": "65b3bd864d06ff4adef6d2a1",
              "name": "file.mp4",
              "contentType": "video/mp4",
              "size": 2107842
            }
          ```
        - Response 400
          ```json
            {
              "type": "about:blank",
              "title": "Bad Request",
              "status": 400,
              "detail": "Video not found",
              "instance": "/videos/1"
            }
          ```
  </details>

  <details>
  <summary>Publicar um video</summary>

  - POST: http://localhost:8080/videos/
      - Request:
        ```bash
          curl -X POST 'localhost:8080/videos' \
          -H 'Content-Type: application/json' \
          --data '{
              "fileId": "65b3bd864d06ff4adef6d2a1",
              "title": "Fish king 2 - making of",
              "description": "The king of fish production",
                "categoryNames": ["animation"]
          }'
        ```
      - Response 201
        No body returned for response
  </details>

  <details>
  <summary>Atualizar um video</summary>

  - PUT: http://localhost:8080/videos/{id}
      - Request:
        ```bash
          curl -X PUT 'localhost:8080/videos/65b3bd864d06ff4adef6d2a1' \
          -H 'Content-Type: application/json' \
          --data '{
              "title": "Fish king 2 - making of",
              "description": "The king of fish production",
                "categoryNames": ["animation"]
          }'
        ```
      - Response 201
        ```json
        {
          "id": "65b3bd864d06ff4adef6d2a1",
          "title": "Fish king 2 - making of",
          "description": "The king of fish production",
          "categories": [
            "animation"
          ],
          "likes": 0,
          "views": 0,
          "uri": "/videos/play/65b705f190b63d64994e1794",
          "publicationDate": "2024-01-29T14:35:29.458Z"
        }
        ```
  </details>

  <details>
  <summary>Buscar um Video</summary>

  - GET: http://localhost:8080/videos/{id} *(id do vídeo buscado)*
      - Request
        ```bash
          curl -X GET 'localhost:8080/videos/65abbc65252b6124cbb4c9fe'
        ```
      - Response 200
        ```json
            {
              "id": "65b3bd864d06ff4adef6d2a1",
              "title": "Fish king 2 - making of",
              "description": "The king of fish production",
              "categories": [
              "animation"
              ],
              "likes": 0,
              "views": 0,
              "uri": "/videos/play/65b3bd864d06ff4adef6d2a1",
              "publicationDate": "2024-01-27T18:57:13.805"
            }
        ```
        
      - Response 400
        ```json
          {
                "type": "about:blank",
                "title": "Bad Request",
                "status": 400,
                "detail": "Video not found",
                "instance": "/videos/65abbc65252b6124cbb4c9fe4"
          }
        ```
  </details>

  <details>
  <summary>Buscar todos os videos</summary>

  - GET: http://localhost:8080/videos
      - Request:
        ```bash
          curl -X GET 'localhost:8080/videos'
        ```
      - Response 200
        ```json
          {
          "content": [
                    {
                        "id": "65abbc65252b6124cbb4c9fe",
                        "title": "Dumbo 2",
                        "description": "Dumbo video 2",
                        "categoryName": "animation",
                        "uri": "/videos/play/65abbc65252b6124cbb4c9fe",
                        "publicationDate": "2024-01-20T09:28:21.754"
                    },
                    {
                        "id": "65ae42b1c377515c8b0b6649",
                        "title": "Dumbo 2",
                        "description": "Dumbo video 2",
                        "categoryName": "animation2",
                        "uri": "/videos/play/65ae42b1c377515c8b0b6649",
                        "publicationDate": "2024-01-22T07:25:53.935"
                    }
            ],
            "totalPages": 1,
            "totalElements": 2,
            "currentPage": 0,
            "elementsPerPage": 10
            }
        ```
  </details>

  <details>
  <summary>Deletar um vídeo</summary>

  - DELETE: http://localhost:8080/videos/{videoId}
    - Request:
      ```bash
        curl -X 'DELETE' \
          'http://localhost:8080/videos/65b3bd864d06ff4adef6d2a1' \
          -H 'accept: */*'
      ```

    - Response 204:
        No body returned for response
  </details>

  <details>
    <summary>Recomendações</summary>

    - GET: http://localhost:8080/{userId}/recomendations
      - Request:
        ```bash
            curl --request GET \
            --url http://localhost:8080/videos/65b317223da0ad4a44b1978f/recommendations
        ```

      - Response 200:
        ```json 
        [
          {
              "id": "65b3180b3da0ad4a44b1979a",
              "title": "Kung fu Bunny",
              "description": "Leaving the house",
              "categories": [
                  "animation",
                  "ninja",
                  "action"
              ],
              "likes": 0,
              "views": 0,
              "uri": "/videos/play/65b3180b3da0ad4a44b1979a",
              "publicationDate": "2024-01-25T23:26:02.138"
          },
          {
              "id": "65b318ce3da0ad4a44b1979d",
              "title": "Fish king 2",
              "description": "The king of fish part 2",
              "categories": [
                  "documentary",
                  "fish",
                  "ninja",
                  "action"
              ],
              "likes": 0,
              "views": 1,
              "uri": "/videos/play/65b318ce3da0ad4a44b1979d",
              "publicationDate": "2024-01-25T23:28:41.706"
          },
          {
              "id": "65b3bd864d06ff4adef6d2a1",
              "title": "Fish king 2 - making of",
              "description": "The king of fish production",
              "categories": [
                  "documentary",
                  "fish",
                  "ninja",
                  "action"
              ],
              "likes": 0,
              "views": 0,
              "uri": "/videos/play/65b3bd864d06ff4adef6d2a1",
              "publicationDate": "2024-01-26T11:13:06.823"
          },
          {
              "id": "65b318453da0ad4a44b1979b",
              "title": "Fish king",
              "description": "The king of fish",
              "categories": [
                  "documentary",
                  "fish",
                  "action"
              ],
              "likes": 0,
              "views": 0,
              "uri": "/videos/play/65b318453da0ad4a44b1979b",
              "publicationDate": "2024-01-25T23:28:54.21"
          }
        ]
        ```
  </details>

  <details>
    <summary>Assistir Vídeo</summary>
  
    - POST: http://localhost:8080/play/{id}
      - Request:
        ```bash
            curl --request GET \
            --url http://localhost:8080/videos/play/65b318ce3da0ad4a44b1979d \
            --header 'Range: bytes=0-500'
        ```
      - Response 200:
        ```
          ftypmp42mp42mp41isomavc1�moovlmvhdۡ�ۡ��@!iods���O�������trak\tkhdۡ�ۡ��@�8$edtselst��mdia mdhdۡ�ۡ��U�6hdlrvideL-SMASH Video Handler-minfvmhd$dinfdrefurl �stbl�stsd
        ```
  </details>

- ### API de Bookmark:
  
  <details>
    <summary>Criar um Bookmark</summary>

    - POST: http://localhost:8080/bookmarks/video/{videoId}/user/{userId}
        - Request:
          ```bash
            curl --request POST \
            --url http://localhost:8080/bookmarks/video/65b3bd864d06ff4adef6d2a1/user/65abbc3b252b6124cbb4c9fc \
            --header 'Content-Type: application/json' \
          ```
        - Response 200: OK

    - DELETE: http://localhost:8080//bookmarks/video/{videoId}/user/{userId}
        - Request:
          ```bash
            curl -X 'DELETE' \
            'http://localhost:8080/bookmarks/video/65b3bd864d06ff4adef6d2a1/user/65b7ce27b018d560abdfdce5' \
            -H 'accept: */*'
          ```
        - Response 200: OK
  
    <summary>Retornar todos os bookmarks/summary>

    - GET: http://localhost:8080/bookmarks?page=0&size=10
        - Request:
          ```bash
            curl -X 'GET' \
            'http://localhost:8080/bookmarks?page=0&size=10' \
            -H 'accept: */*'
          ```
        - Response 200: 
          ```json
          {
              "content": [
                {
                  "id": "65b7cef2b018d560abdfdce7",
                  "user": {
                    "name": "Camilo",
                    "email": "camilo@m.com"
                  },
                  "video": {
                    "id": "65b3bd864d06ff4adef6d2a1",
                    "title": "Fish king 2 - making of",
                    "description": "The king of fish production",
                    "categories": [
                      "anime2"
                    ],
                    "likes": 5,
                    "views": 0,
                    "uri": "/videos/play/65b3bd864d06ff4adef6d2a1",
                    "publicationDate": "2024-01-29T13:01:15.487"
                  }
                },
                {
                  "id": "65b7cf21b018d560abdfdce8",
                  "user": {
                    "name": "Sérgio",
                    "email": "mail@m.com"
                  },
                  "video": {
                    "id": "65b3bd864d06ff4adef6d2a1",
                    "title": "Fish king 2 - making of",
                    "description": "The king of fish production",
                    "categories": [
                      "anime2"
                    ],
                    "likes": 6,
                    "views": 0,
                    "uri": "/videos/play/65b3bd864d06ff4adef6d2a1",
                    "publicationDate": "2024-01-29T13:01:15.487"
                  }
                }
              ],
              "totalPages": 1,
              "totalElements": 2,
              "currentPage": 0,
              "elementsPerPage": 10
            }
          ```

    <summary>Retornar um bookmark por id</summary>

    - GET: http://localhost:8080/bookmarks/{bookmarkId}}
        - Request:
          ```bash
            curl -X 'GET' \
              'http://localhost:8080/bookmarks/65b7cef2b018d560abdfdce7' \
              -H 'accept: */*'
          ```
        - Response 200: OK
  </details>
