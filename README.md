# Techflix

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

## Documentacao das APIs

- ### API de Usuários:

  <details>
    <summary>Cadastrar um usuário</summary>

    - POST: http://localhost:8080/users/
        - Request:
          ```bash
            curl -X POST 'localhost:8080/users' -H 'Content-Type: application/json' \
            --data '{
                "name": "João Augusto de Oliveira",
                "email": "password",
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
            curl -X GET 'http://localhost:8080/users/65abbc3b252b6124cbb4c9fc'
          ```
        - Response 200:
          ```json
            {
              "id": "65abbc3b252b6124cbb4c9fc",
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
              "instance": "/users/65abbc3b252b6124cbb4c9f3c"
            }
          ```
  </details>

  <details>
    <summary>Retornar uma lista de usuários</summary>

    - GET: http://localhost:8080/users
        - Request:
          ```bash
            curl -X GET 'http://localhost:8080/users'
          ```
          - Response 200:
            ```json
             {
                "content": [
                    {
                        "id": "65abbc3b252b6124cbb4c9fc",
                        "name": "Sérgio",
                        "email": "mail@m.com"
                    },
                    {
                        "id": "65adc8285620df7cbd75b7fe",
                        "name": "Sérgio",
                        "email": "mail2@m.com"
                    },
                    {
                        "id": "65adc84c5620df7cbd75b7ff",
                        "name": "Sérgio",
                        "email": "mail2@m.com"
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
                  "name": "animation",
              }'
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

    - GET: http://localhost:8080/categories/{name} *(nome da categoria buscada)*
        - Request:
          ```bash
            curl -X GET 'localhost:8080/categories/animation'
          ```
        - Response 200:
          ```json
            {
              "name" : "animation"
            }
          ```
  </details>


- ### API de Vídeo:

  <details>
    <summary>Cadastrar um video</summary>

    - POST: http://localhost:8080/videos/
        - Request:
          ```bash
            curl -X POST 'localhost:8080/videos' \
            -H 'Content-Type: application/json' \
            --data '{
                "fileId": "65abbc65252b6124cbb4c9fe",
                "title": "Dumbo 2",
                "description": "Dumbo video 2",
                "categoryName": "animation",
            }'
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
    <summary>Buscar um Video</summary>

    - GET: http://localhost:8080/videos/{id} *(id do vídeo buscado)*
        - Request
          ```bash
            curl -X GET 'localhost:8080/videos/65abbc65252b6124cbb4c9fe'
          ```
          - Response 200
            ```json
            {
                "id": "65abbc65252b6124cbb4c9fe",
                "title": "Dumbo 2",
                "description": "Dumbo video 2",
                "categoryName": "animation",
                "uri": "/videos/play/65abbc65252b6124cbb4c9fe",
                "publicationDate": "2024-01-20T09:28:21.754"
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

- ### API de Bookmark:

  <details>
    <summary>Fazer um Bookmark</summary>

    - POST: http://localhost:8080/video/{videoId}/user/{userId}
        - Request:
          ```bash
            curl -X POST 'localhost:8080/bookmarks/video/65abbc65252b6124cbb4c9fe/user/65abbc3b252b6124cbb4c9fc' \
            -H 'Content-Type: application/json' 
          ```
  </details>
