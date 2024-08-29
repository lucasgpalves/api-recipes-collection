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