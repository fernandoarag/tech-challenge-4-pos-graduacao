# Product Management API

## Descrição

Esta API permite o gerenciamento de clientes, incluindo operações de CRUD e utiliza OpenAPI para documentação automatizada.

## Tecnologias Utilizadas

- **Spring Boot 3.4.2**: Framework para o desenvolvimento da aplicação.
- **Java 21**: Linguagem de programação utilizada.
- **OpenAPI**: Para a documentação e geração automática de código.
- **Lombok**: Para reduzir o código boilerplate (getters, setters, construtores).
- **H2**: Banco de dados em memória para armazenamento das informações.

## Pré-requisitos

- **JDK 21** ou superior.
- **Maven**: Para o gerenciamento de dependências e build.

## Instalação

1. **Clonar o Repositório:**

```bash
  git clone https://github.com/fernandoarag/customers-api.git
```

### 🚀 Opções de Build e Execução

Abaixo seguem duas opções de build e execução da aplicação: Dockerfile e Maven.

## Build com Docker
Para construir a aplicação utilizando o Docker, execute o seguinte comando no diretório do projeto:

```bash
  docker compose build
```

Após a construção da imagem, você pode executar a aplicação com o comando:

```bash
  docker run -p 8080:8080 customers-api-app
```

## Executando a Aplicação

2. Compilar o Projeto:

```bash
   mvn clean install
```

3. Executar a Aplicação:

```bash
   mvn spring-boot:run
```

A aplicação estará disponível em http://localhost:8080/api/customermanagement/v1.

## Endpoints

### Gerenciamento de Produtos

**GET** _/customers_: Retorna todos os clientes cadastrados.
- **Parâmetros**:
  - **`(CustomerFilter)`**:
    ```json
      {
        "id": "Long",
        "name": "String",
        "email": "String",
        "phone": "String",
        "cellPhone": "String",
        "zipCode": "String",
        "neighborhood": "String",
        "city": "String",
        "state": "String"
      }
    ```
- **200 OK**: Lista de clientes.
- **500 Internal Server Error**: Erro interno do servidor.

**POST** _/customers_: Cria um novo cliente.
- **Body**: `(CustomerRequestDTO)`:
  ```json
    {
      "name": "String",
      "email": "String",
      "phone": "String",
      "cellPhone": "String",
      "zipCode": "String",
      "address": "String",
      "addressNumber": "String",
      "neighborhood": "String",
      "city": "String",
      "state": "String",
      "complement": "String"
    }
  ```
- **201 Created**: Cliente criado com sucesso.
- **400 Bad Request**: Requisição inválida.
- **500 Internal Server Error**: Erro interno do servidor.

**PUT** _/customers/{id}_: Atualiza um cliente pelo ID.
- **Parâmetros**:
    - **Path**: `id` (integer, obrigatório)
    - **Body**: Objeto `CustomerUpdateRequestDTO`:
      ```json
      {
        "name": "String",
        "phone": "String",
        "cellPhone": "String",
        "zipCode": "String",
        "address": "String",
        "addressNumber": "String",
        "neighborhood": "String",
        "city": "String",
        "state": "String",
        "complement": "String"
      }
      ```
- **200 OK**: Cliente atualizado com sucesso.
- **400 Bad Request**: Requisição inválida.
- **404 Not Found**: Cliente não encontrado.
- **500 Internal Server Error**: Erro interno do servidor.

**DELETE BY ID** _/customers/id/{id}_: Remove um cliente pela ID.
- **Parâmetro**: `id` (integer, obrigatório)
- **204 No Content**: Cliente removido com sucesso.
- **404 Not Found**: Cliente não encontrado.
- **500 Internal Server Error**: Erro interno do servidor.

**DELETE BY EMAIL** _/customers/email/{email}_: Remove um cliente pelo email.
- **Parâmetro**: `email` (string, obrigatório)
- **204 No Content**: Cliente removido com sucesso.
- **404 Not Found**: Cliente não encontrado.
- **500 Internal Server Error**: Erro interno do servidor.

## Documentação OpenAPI
A documentação da API pode ser acessada, após inciar o servidor, em:

```bash
  http://localhost:8080/api/customermanagement/v1/swagger-ui/index.html
```