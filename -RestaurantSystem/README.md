# Microserviço de Eureka Server

Este microserviço implementa um **Eureka Server**, que serve como um servidor de descoberta para microserviços em uma arquitetura baseada em Spring Cloud. O Eureka permite que os serviços se registrem e descubram uns aos outros de forma dinâmica, facilitando a comunicação entre eles sem a necessidade de configuração manual de endereços.

## Índice

- [Visão Geral](#visão-geral)
- [Arquitetura](#Arquitetura)
- [Benefícios](#Benefícios)
- [Tecnologias Utilizadas](#Tecnologias Utilizadas)
- [Como Usar](#Como Usar)
- [Contato](#Contato)

## Visão Geral

O **Eureka** é um componente fundamental em arquiteturas de microserviços, oferecendo uma solução de descoberta de serviços que permite que os aplicativos se registrem e localizem outros serviços em tempo de execução. 
Com o Eureka Server, os serviços podem se comunicar de maneira eficiente e resiliente.

- **Roteamento Dinâmico**: Direciona as requisições para os serviços corretos com base em regras configuráveis.
- **Filtragem**: Permite aplicar filtros para manipular requisições e respostas, como autenticação, logging e manipulação de cabeçalhos.
- **Resiliência**: Integra-se facilmente com bibliotecas como **Resilience4j** para implementar padrões de resiliência, como circuit breakers e retries.
- **Autenticação e Autorização**: Suporta integração com sistemas de autenticação, como OAuth2 e JWT, para proteger os serviços.

## Arquitetura

O Eureka Server atua como um registro centralizado onde os microserviços podem se registrar e consultar. A arquitetura típica é ilustrada a seguir:
```bash
[Microserviço A] --> [Eureka Server] [Microserviço B] --> [Eureka Server] [Microserviço C] --> [Eureka Server]
```
Os microserviços se registram no Eureka Server ao iniciar e podem consultar o servidor para descobrir outros serviços disponíveis.

## Benefícios

- **Descoberta Dinâmica**: Permite que os microserviços se registrem e descubram uns aos outros em tempo real, reduzindo a necessidade de configuração manual.
- **Resiliência**: O Eureka suporta a recuperação de falhas, permitindo que os serviços sejam automaticamente removidos do registro se não estiverem mais disponíveis.
- **Escalabilidade**: Facilita a adição e remoção de instâncias de serviços sem impactar a comunicação entre eles.
- **Integração Simples**: Fácil de integrar com outros componentes do Spring Cloud, como Zuul ou Spring Cloud Gateway.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Cloud Netflix Eureka**: Biblioteca para implementar o servidor de descoberta Eureka.

## Como Usar

O MS Eureka Server está configurado para ser o primeiro a subir na configuração do Docer-compose que estará disponível no diretório raiz dos outros microserviços.
Será configurado com a porta 8761 onde a mesa será setada no Gateway e que fornecerá a porta 8080 para os clients.

## Contato

Para dúvidas ou sugestões, entre em contato:

[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/jorge-maia-dev-java/)
[![E-mail](https://img.shields.io/badge/-Email-0077B5?style=for-the-badge&logo=microsoft-outlook&logoColor=white)](mailto:jsm.java@gmail.com)
[![GitHub](https://img.shields.io/badge/GitHub-0077B5?style=for-the-badge&logo=github&logoColor=white)](https://github.com/j0rg3m414)
[![Blogger](https://img.shields.io/badge/Blogger-FF5722?style=for-the-badge&logo=blogger&logoColor=white)](https://putzcomonaopenseinissoantes.blogspot.com/)
