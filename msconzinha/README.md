# Microserviço de Pedidos e Cardápio de Restaurante

Este é um microserviço Eureka Client que gerencia os pedidos e atualiza o status dos pedidos. Consome uma fila com os pedidos enviados pelos Clients e produz mensagens com mudanças nos status dos pedidos por um Webhook implementado. 

## Índice

- [Visão Geral](#visão-geral)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Funcionalidades](#funcionalidades)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Uso](#uso)
- [Contato](#Contato)

## Visão Geral

Este microserviço é parte de uma arquitetura de microserviços desenvolvido com base em boas práticas que visa a escalabilidade e alta disponibilidade com uma manutenção simplificada. O mesmo não tem comunicaçção com o banco.

## Tecnologias Utilizadas

- **Linguagem**: Java 11
- **Frameworks**: Spring Boot, Spring Eureka Client, Lombok, RabbitMQ
- **API**: RESTful

## Funcionalidades

- **Gerenciamento dos pedidos**:
- Informar produdos em preparo
- Informar produtos prontos para os Garçons
- Notificação pelo garçom de produto entregue

## Estrutura do Projeto
### Converters:
   ```bash
src/main/java/com/restaurante/mscozinha/application/converter
```
### Exceptions:
   ```bash
src/main/java/com/restaurante/mscozinha/application/ex
```
### Application (Services/Resources):
   ```bash
src/main/java/com/restaurante/mscozinha/application
```
### Config:
   ```bash
src/main/java/com/restaurante/mscozinha/configuration
```
### Domain/model:
   ```bash
src/main/java/com/restaurante/mscozinha/domain/model
```
### MQueues:
```bash
src/main/java/com/restaurante/mscozinha/infra/mqueues
```

## Uso

Será o quarto microserviço a ser inicializado na sequência configurada no Docker-compose que ficará disponível na raiz do diretório dos Microserviços.
A porta está configurada para ser gerada radomicamente devido o uso de um MS gateway e todos os clients serão acessíveis pela porta fixada no MS de gateway que será 8080 e sendo o terceiro MS a ser inicializado na ordem
configurada no Docker-compose.

## Exemplos de Endpoints

Considerando que o Server esteja rodando e o Gateway.

### Teste de resposta:
```bash
GET http://localhost:8080/cozinha
```

### Informa mudnça de status do pedido
```bash
POST http://localhost:8080/cozinha/informar-preparo
Param: numPedido
Valor: [Integer]
```

### Informa prato pronto
```bash
POST http://localhost:8080/cozinha/notificacao-prato-pronto
Param: numPedido
Valor: [Integer]
```

### Notifica prato entregue (utilizado pelo Garcom)
```bash
POST http://localhost:8080/cozinha/notificacao-prato-entregue
Param: numPedido
Valor: [Inteiro]
```

## Contato

Para dúvidas ou sugestões, entre em contato:

[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/jorge-maia-dev-java/)
[![E-mail](https://img.shields.io/badge/-Email-0077B5?style=for-the-badge&logo=microsoft-outlook&logoColor=white)](mailto:jsm.java@gmail.com)
[![GitHub](https://img.shields.io/badge/GitHub-0077B5?style=for-the-badge&logo=github&logoColor=white)](https://github.com/j0rg3m414)
[![Blogger](https://img.shields.io/badge/Blogger-FF5722?style=for-the-badge&logo=blogger&logoColor=white)](https://putzcomonaopenseinissoantes.blogspot.com/)
