## Tete prático PGE - Gerenciador de pedidos

## Índice

- [Visão Geral](#visão-geral)
- [Arquitetura](#Arquitetura)
- [Tecnologias Utilizadas](#Tecnologias Utilizadas)
- [Como Rodar](#Como Rodar)
- [Contato](#Contato)

## Visão Geral

A implementação dos microserviços foi pensado da seguinte forma, tenho uma ou mais instâncias de um MS Eureka client (restaurante-system-client) que está rodando em um MS Eureka Server (-RestaurantSystem) que tem uma porta fixa e o mesmo passa por um gateway (MS-CLOUD-GATEWAY) que também tem uma porta fixa que faz o balanceamento de carga das requisições. A comunicação entre os MS é feita por mensageria usando RabbitMQ (em Docker), por onde são enviados os pedidos entre o MS Client (restaurante-system-client) com portas geradas automaticamente pois quem vai gerenciar isso é o Gateway e o MS Cozinha (msconzinha) com a porta configurada da mesma forma que o outro Client. As filas são geradas programaticamente por meio de arquivo de configuração com os seus respectivos Publishers e Subscribers, tando do lado do MS Client (restaurante-system-client) quanto do MS Cozinha (msconzinha). Os dados são gravados (no caso o MS Client que tem a conexão com o banco) em um banco relacional, no caso o Posgres (imagem Docker) foi usado, as tabelas e cargas iniciais são realizados por meio do Flyway e migrations localizados dentro do MS Client (restaurante-system-client) que são disparadas ao subir o mesmo. Cada MS tem seu Dockerfile que baixa da imagem maven tagueada com a versão do java usada (java 11) e é executado o comando para o mavem gerar o package (.jar), tem um outro build no mesmo arquivo que copia o .jar para o diretório raiz e é executado com o java para subir a aplicação. O Docker compose está configurado com a ordem correta de subida dos microserviços, RabbitMQ, postgres, criado uma network para comunicação entre os containers. Infelizmente não consegui implementar a parte do front como cliente, mas pode ser usado o Insomnia ou Postman para realizar os testes dos endpoints. Caso queiram, pode usar um PGAdmin para se conectar com o banco e ter acesso aos registros gerados. Ao longo deste documento haverá instruções. Cada microserviço tem um README.

## Arquitetura

O microserviço de gateway se posiciona entre os clientes e os microserviços da aplicação. Ele intercepta todas as requisições, aplica a lógica de roteamento e, se necessário, comos segue:
[Usuário] 
  \__requisições--> 
          [Gateway (Spring Cloud Gateway)] -->
                            \__> [Server (Eureka Server)] -->
                                           \__> [Client (Eureka Client)]--> [Banco (PostGres)]        [Cozinha (Eureka Client)] 
                                                                 \__JSON<--> [Mensageria (RabbitMQ)] <-->JSON__/
## Tecnologias Utilizadas

- **Java**: 11 (2021.0.1)
- **Spring Boot**: framework para desenvolvimento de aplicações Java.
- **Spring Cloud Gateway**: framework para construção de gateways de API e Load Balancer.
- **Eureka Server**: ferramenta que ajuda a gerenciar serviços em um sistema distribuído, como uma aplicação que é composta por várias partes que se comunicam entre si
- **Eureka Client**: Com isso o gateway será incluido no Eureka Server com uma porta padrão 8080 e irá registrar os clients.
- **Lombok**: biblioteca para Java que ajuda os desenvolvedores a escreverem menos código diminuindo o boilerplate.
- **Flyway**: ferramenta que ajuda desenvolvedores a gerenciar mudanças no banco de dados de uma aplicação.
- **RabbitMQ**: ferramenta que ajuda diferentes partes de um sistema a se comunicarem entre si.
- **Spring Data JPA**: gerencia toda parte transacional com o banco com JPA e Hibernate como ORM.

## Como Rodar

Todo o sistema está dokerizado, cada microserviço tem seu Dockerfile e na Raiz principais onde se concentram todos os microserviços tem o Docker compose que ao rodar subirá todos os microserviçoes na ordem correta de dependencia. Após a finalização da subida, pode usar um client http para realizar as requisições aqui descritas para realizar os testes dos endpoins propostos no documento de requisitos. Após fazer o clone de todo projeto, no diretório raiz terá o Docker-compose.

Considerando que o Docker esteja devidamente rodando, segue:

Subindo os microserviços:
```bash
docker-compose up -d
```

Para encerrar, basta digitar o comando 
```bash
docker-compose down
```

Após a subida dos microserviços, seguem os endpoints disponíveis:

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

Para dúvidas, entre em contato:

[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/jorge-maia-dev-java/)
[![E-mail](https://img.shields.io/badge/-Email-0077B5?style=for-the-badge&logo=microsoft-outlook&logoColor=white)](mailto:jsm.java@gmail.com)
[![GitHub](https://img.shields.io/badge/GitHub-0077B5?style=for-the-badge&logo=github&logoColor=white)](https://github.com/j0rg3m414)
[![Blogger](https://img.shields.io/badge/Blogger-FF5722?style=for-the-badge&logo=blogger&logoColor=white)](https://putzcomonaopenseinissoantes.blogspot.com/)
