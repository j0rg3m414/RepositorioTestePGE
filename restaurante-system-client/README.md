# Microserviço de Pedidos e Cardápio de Restaurante

Este é um microserviço Eureka Client que gerencia pedidos e o cardápio de um restaurante. O objetivo deste projeto é fornecer uma API RESTful que permita a criação, consulta e gerenciamento de pedidos, além de interagir com o cardápio do restaurante. Conversa com outro Microserviço que atende a cozinha por meio de mensageria RabbitMQ.

## Índice

- [Visão Geral](#visão-geral)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Funcionalidades](#funcionalidades)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Uso](#uso)
- [Testes](#testes)
- [Contato](#Contato)

## Visão Geral

Este microserviço é parte de uma arquitetura de microserviços desenvolvido com base em boas práticas que visa a escalabilidade e alta disponibilidade com uma manutenção simplificada. Ele é responsável por:

- Gerenciar o cardápio do restaurante (Listagem de produtos).
- Criar e gerenciar pedidos feitos pela figura do garçom.
- Consultar  e modificar o status dos pedidos.

## Tecnologias Utilizadas

- **Linguagem**: Java 11
- **Frameworks**: Spring Boot, Spring Eureka Client, Lombok, Flyway, RabbitMQ, Spring Data JPA 
- **Banco de Dados**: PostgreSQL
- **API**: RESTful
- **Testes**: JUnit5, Mockito

## Funcionalidades

- **Cardápio**:
    - Listar todos os itens do cardápio.
- **Pedidos**:
    - Criar um novo pedido.
    - Consultar detalhes de um pedido.
    - Atualizar o status de um pedido.
    - Listar todos os pedidos.

## Estrutura do Projeto
### Converters:
   ```bash
src/main/java/com/restaurante/restaurante_system_client/application/converter
```
### Exceptions:
   ```bash
src/main/java/com/restaurante/restaurante_system_client/application/ex
```
### Representations (Services/Resources):
   ```bash
src/main/java/com/restaurante/restaurante_system_client/application/representation
```
### Config:
   ```bash
src/main/java/com/restaurante/restaurante_system_client/config
```
### Enumns:
   ```bash
src/main/java/com/restaurante/restaurante_system_client/domain/enumns
```
### Domains:
   ```bash
src/main/java/com/restaurante/restaurante_system_client/domain
```
### MQueues:
   ```bash
src/main/java/com/restaurante/restaurante_system_client/infra/mqueue
```
### Repositories:
   ```bash
C:\testePraticoPGE\restaurante-system-client\src\main\java\com\restaurante\restaurante_system_client\repository
```
### Migrations:
   ```bash
src/main/resources/db/migration
```
### Testes:

Menu (Listagem de produtos - cardápio):
   ```bash
src/test/java/com/restaurante/restaurante_system_client/menu
```
### Listagem dos pedidos e Criação de pedidos:
   ```bash
src/test/java/com/restaurante/restaurante_system_client/pedido
```
## Uso

Será o segundo microserviço a ser inicializado na sequência configurada no Docker-compose que ficará disponível na raiz do diretório dos Microserviços.
Ao iniciar pela primeira vez, o Hibernate está configurado para gerar a estrutura de tabelas 
e o Flyway se encarregará dar a carga inicial. A porta está configurada para ser gerada radomicamente devido o uso de um MS gateway
e todos os clients serão acessíveis pela porta fixada no MS de gateway que será 8080 e sendo o terceiro MS a ser inicializado na ordem 
configurada no Docker-compose.

## Exemplos de Endpoints

### Listar Itens do Cardápio:

```bash
GET http://localhost:8080/menu
```

### Criar um Pedido:
```bash
POST http://localhost:8080/pedidos
```
Content-Type: application/json
```bash
{
	"idProdutos" : [3,2,2],
	"garcom" : 1,
	"mesa" : 1
}
```

### Listar todos os pedidos

```bash
GET http://localhost:8080/menu
```

### Buscar Pedido por número de um pedido

```bash
GET http://localhost:8080/pedidos/obterPedido
Param: numPedido
Valor: [Inteiro]
```

## Contato

Para dúvidas ou sugestões, entre em contato:

[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/jorge-maia-dev-java/)
[![E-mail](https://img.shields.io/badge/-Email-0077B5?style=for-the-badge&logo=microsoft-outlook&logoColor=white)](mailto:jsm.java@gmail.com)
[![GitHub](https://img.shields.io/badge/GitHub-0077B5?style=for-the-badge&logo=github&logoColor=white)](https://github.com/j0rg3m414)
[![Blogger](https://img.shields.io/badge/Blogger-FF5722?style=for-the-badge&logo=blogger&logoColor=white)](https://putzcomonaopenseinissoantes.blogspot.com/)
