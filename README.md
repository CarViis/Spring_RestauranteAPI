# Restaurante API - Projeto Final (Minicurso Spring)

API REST de exemplo para gerenciamento de comidas de um restaurante, desenvolvida como projeto final do minicurso de introducao a programacao Back-End com Spring (PETCC/UFRN).

## Tecnologias

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- H2 Database (em memoria)
- Lombok
- Swagger/OpenAPI (springdoc)
- Maven Wrapper

## Funcionalidades atuais

- Listar comidas cadastradas
- Cadastrar nova comida
- Atualizar comida existente
- Deletar comida por ID
- Popular banco automaticamente com dados iniciais (foods e users)

## Estrutura principal

```text
src/main/java/com/petcc/restaurante/
	controller/FoodController.java
	service/FoodService.java
	repository/FoodRepository.java
	model/Food.java
	model/User.java

src/main/resources/
	application.properties
	data.sql
```

## Requisitos

- JDK 21 instalado e configurado no PATH

Observacao: o projeto usa `<java.version>21</java.version>` no Maven. Se voce executar com uma versao menor, a compilacao falhara.

## Como executar

No Linux/macOS:

```bash
cd /workspaces/RestauranteAPI_ProjetoFinalMinicursoSpring
chmod +x mvnw
./mvnw spring-boot:run
```

Se nao quiser ajustar permissao, rode com:

```bash
sh mvnw spring-boot:run
```

No Windows:

```bat
mvnw.cmd spring-boot:run
```

A aplicacao sobe por padrao em:

- http://localhost:8080

## Documentacao da API (Swagger)

Com a aplicacao em execucao, acesse:

- UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## Banco H2

Configuracao atual (application.properties):

- URL JDBC: `jdbc:h2:mem:foodDB`
- Usuario: `fonseca`
- Senha: vazia

Console web:

- http://localhost:8080/h2-console

Dica: para conectar no console, use a mesma URL JDBC configurada acima.

## Endpoints disponiveis

Base URL: `http://localhost:8080`

### 1) Listar comidas

- Metodo: `GET`
- Rota: `/foods/get`

Exemplo:

```bash
curl -X GET http://localhost:8080/foods/get
```

### 2) Cadastrar comida

- Metodo: `POST`
- Rota: `/foods/save`
- Body JSON:

```json
{
	"title": "Lasanha",
	"price": 39.9,
	"image": "https://exemplo.com/lasanha.jpg"
}
```

Exemplo:

```bash
curl -X POST http://localhost:8080/foods/save \
	-H "Content-Type: application/json" \
	-d '{"title":"Lasanha","price":39.9,"image":"https://exemplo.com/lasanha.jpg"}'
```

### 3) Atualizar comida

- Metodo: `PUT`
- Rota: `/foods/update`
- Body JSON (com `id` obrigatorio):

```json
{
	"id": 1,
	"title": "Hamburguer Artesanal",
	"price": 34.9,
	"image": "https://exemplo.com/hamburguer.jpg"
}
```

Exemplo:

```bash
curl -X PUT http://localhost:8080/foods/update \
	-H "Content-Type: application/json" \
	-d '{"id":1,"title":"Hamburguer Artesanal","price":34.9,"image":"https://exemplo.com/hamburguer.jpg"}'
```

### 4) Deletar comida

- Metodo: `DELETE`
- Rota: `/foods/delete`
- Parametro: `id` (query param)

Exemplo:

```bash
curl -X DELETE "http://localhost:8080/foods/delete?id=1"
```

## Dados iniciais

Ao iniciar a aplicacao, o script `data.sql`:

- recria a tabela `foods` e insere comidas de exemplo
- recria a tabela `users` e insere o usuario:
	- login: `admin`
	- senha: `12345`

## Testes

Para rodar testes:

```bash
sh mvnw test
```

Se ocorrer erro de compilacao com mensagem parecida com `release version 21 not supported`, ajuste seu Java para a versao 21.
