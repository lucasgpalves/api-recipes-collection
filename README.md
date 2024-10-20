# Sistema de Armazenamento de Receitas

Este projeto é um sistema de gerenciamento de receitas culinárias. Ele permite que cozinheiros registrem receitas, degustadores avaliem as receitas e editores filtrem receitas para gerar livros de culinária. O sistema foi desenvolvido para atender às necessidades de um ambiente culinário dinâmico e colaborativo.

## Índice

- [Sobre](#sobre)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Usadas](#tecnologias-usadas)
- [Instalação](#instalação)
- [Uso](#uso)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Sobre

O Sistema de Armazenamento de Receitas é uma aplicação monolítica construída com Spring Boot, projetada para gerenciar receitas culinárias, suas avaliações e a geração de livros de receitas. Originalmente concebido como uma aplicação baseada em microsserviços, o projeto foi consolidado em uma arquitetura monolítica para simplificar a manutenção e o desenvolvimento.

## Funcionalidades

- Registro de receitas por cozinheiros.
- Avaliação de receitas por degustadores.
- Filtros para selecionar receitas para a criação de livros de culinária.
- Geração de livros de receitas em formatos como PDF e DOCX.
- Autenticação e autorização de usuários.
- Interface de administração para gerenciar receitas e avaliações.

## Tecnologias Usadas

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Web](https://docs.spring.io/spring-boot/docs/current/reference/html/web.html)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Jakarta Validation](https://beanvalidation.org/)
- [MySQL](https://www.mysql.com/)
- [Flyway](https://flywaydb.org/)
- [JUnit](https://junit.org/junit5/)
- [Lombok](https://projectlombok.org/)
- [DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools)
- [JWT](https://jwt.io/)
- [Springdoc OpenAPI UI](https://springdoc.org/)

## Instalação

Instruções para instalar o projeto e suas dependências:

1. Clone o repositório:
    ```bash
    git clone https://github.com/lucasgpalves/recipes-collection.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd recipes-collection
    ```

3. Configure o banco de dados MySQL:
    - Crie um banco de dados no MySQL:
      ```sql
      CREATE DATABASE nome_do_banco_de_dados;
      ```
    - Atualize o arquivo `application.properties` com as credenciais do banco de dados MySQL:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco_de_dados
      spring.datasource.username=seu_usuario
      spring.datasource.password=sua_senha
      spring.jpa.hibernate.ddl-auto=update
      spring.flyway.enabled=true
      ```

4. Instale as dependências e compile o projeto:
    ```bash
    ./mvnw install
    ```

## Uso

Instruções para usar o projeto:

1. Inicie o aplicativo:
    ```bash
    ./mvnw spring-boot:run
    ```

2. Acesse a aplicação em `http://localhost:8080`.

3. Acesse a documentação da API em `http://localhost:8080/swagger-ui.html`.

## Contribuição

Instruções para quem quiser contribuir para o projeto:

1. Faça um fork do repositório.
2. Crie uma branch para a sua feature:
    ```bash
    git checkout -b minha-feature
    ```
3. Faça commit das suas mudanças:
    ```bash
    git commit -am 'Adiciona nova feature'
    ```
4. Envie para o repositório remoto:
    ```bash
    git push origin minha-feature
    ```
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).

src
├── main
│   ├── java
│   │   └── com
│   │       └── college
│   │           └── recipes_collection
│   │               ├── RecipesCollectionApplication.java
│   │               ├── config
│   │               │   ├── OpenApiConfig.java
│   │               │   └── SecurityConfig.java
│   │               ├── controllers
│   │               │   ├── AuthController.java
│   │               │   ├── BookController.java
│   │               │   ├── CategoryController.java
│   │               │   ├── GoalController.java
│   │               │   ├── IngredientController.java
│   │               │   ├── MeasurementController.java
│   │               │   ├── RecipeController.java
│   │               │   ├── ReviewController.java
│   │               │   ├── RoleController.java
│   │               │   └── UserController.java
│   │               ├── dto
│   │               │   ├── RecipeNameDTO.java
│   │               │   ├── RecipeVerificationResult.java
│   │               │   ├── requests
│   │               │   │   ├── AuthRequestDTO.java
│   │               │   │   ├── BookRequestDTO.java
│   │               │   │   ├── CategoryRequestDTO.java
│   │               │   │   ├── GoalRequestDTO.java
│   │               │   │   ├── IngredientRequestDTO.java
│   │               │   │   ├── IngredientsRecipeRequestDTO.java
│   │               │   │   ├── MeasurementRequestDTO.java
│   │               │   │   ├── RecipeRequestDTO.java
│   │               │   │   ├── RegisterRequestDTO.java
│   │               │   │   ├── ReviewRequestDTO.java
│   │               │   │   ├── RoleRequestDTO.java
│   │               │   │   └── UserRequestDTO.java
│   │               │   └── responses
│   │               │       ├── BookResponseDTO.java
│   │               │       ├── CategoryResponseDTO.java
│   │               │       ├── GoalProgressResponse.java
│   │               │       ├── GoalResponseDTO.java
│   │               │       ├── IngredientResponseDTO.java
│   │               │       ├── IngredientsRecipeResponseDTO.java
│   │               │       ├── MeasurementResponseDTO.java
│   │               │       ├── RecipeResponseDTO.java
│   │               │       ├── RecipeSummariesDTO.java
│   │               │       ├── ReviewResponseDTO.java
│   │               │       ├── RoleResponseDTO.java
│   │               │       ├── TokenResponseDTO.java
│   │               │       └── UserResponseDTO.java
│   │               ├── events
│   │               │   ├── listeners
│   │               │   │   ├── BookCreatedListener.java
│   │               │   │   └── ReviewCreatedListener.java
│   │               │   └── models
│   │               │       ├── BookCreatedEvent.java
│   │               │       └── ReviewCreatedEvent.java
│   │               ├── exceptions
│   │               │   ├── BookWithoutIsbnException.java
│   │               │   ├── RecipeAlreadyExistsException.java
│   │               │   ├── RecipeInvalidIdsException.java
│   │               │   └── RoleNotFoundException.java
│   │               ├── models
│   │               │   ├── Book.java
│   │               │   ├── Category.java
│   │               │   ├── Goal.java
│   │               │   ├── Ingredient.java
│   │               │   ├── IngredientsRecipe.java
│   │               │   ├── Measurement.java
│   │               │   ├── Recipe.java
│   │               │   ├── Review.java
│   │               │   ├── Role.java
│   │               │   ├── User.java
│   │               │   └── UserAuthentication.java
│   │               ├── repositories
│   │               │   ├── BookRepository.java
│   │               │   ├── CategoryRepository.java
│   │               │   ├── GoalRepository.java
│   │               │   ├── IngredientRepository.java
│   │               │   ├── IngredientsRecipeRepository.java
│   │               │   ├── MeasurementRepository.java
│   │               │   ├── RecipeRepository.java
│   │               │   ├── ReviewRepository.java
│   │               │   ├── RoleRepository.java
│   │               │   ├── UserAuthenticationRepository.java
│   │               │   └── UserRepository.java
│   │               └── services
│   │                   ├── AuthenticationService.java
│   │                   ├── AuthorizationService.java
│   │                   ├── BookService.java
│   │                   ├── CategoryService.java
│   │                   ├── GoalService.java
│   │                   ├── IngredientService.java
│   │                   ├── MeasurementService.java
│   │                   ├── RecipeService.java
│   │                   ├── ReviewService.java
│   │                   ├── RoleService.java
│   │                   ├── TokenService.java
│   │                   └── UserService.java
│   └── resources
│       ├── application.properties
│       ├── db
│       │   └── migration
│       │       ├── V10__create-table-publications.sql
│       │       ├── V11__create-table-goals.sql
│       │       ├── V12__create-table-user-authentication.sql
│       │       ├── V13__insert-user.sql
│       │       ├── V1__create-table-roles.sql
│       │       ├── V2__create-table-users.sql
│       │       ├── V3__create-table-categories.sql
│       │       ├── V4__create-table-ingredients.sql
│       │       ├── V5__create-table-recipes.sql
│       │       ├── V6__create-table-measurements.sql
│       │       ├── V7__create-table-ingredients-recipe.sql
│       │       ├── V8__create-table-reviews.sql
│       │       └── V9__create-table-books.sql
│       └── static
└── test
    └── java
        └── com
            └── college
                └── recipes_collection
                    ├── RecipesCollectionApplicationTests.java
                    └── services
                        └── RecipeServiceTest.java