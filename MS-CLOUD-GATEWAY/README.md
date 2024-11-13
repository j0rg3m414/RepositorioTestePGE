## Microserviço de Gateway

Este microserviço implementa um gateway de API utilizando o **Spring Cloud Gateway**, que atua como um ponto de entrada para todas as requisições em uma arquitetura de microserviços. O gateway é responsável por rotear as requisições para os serviços apropriados, aplicar políticas de segurança, realizar balanceamento de carga e fornecer uma interface unificada para os consumidores da API.

## Índice

- [Visão Geral](#visão-geral)
- [Arquitetura](#Arquitetura)
- [Benefícios](#Benefícios)
- [Tecnologias Utilizadas](#Tecnologias Utilizadas)
- [Como Usar](#Como Usar)
- [Contato](#Contato)

## Visão Geral

O **Spring Cloud Gateway** é uma solução simples e eficaz para gerenciar o tráfego entre os microserviços. Com suporte a funcionalidades como:

- **Roteamento Dinâmico**: Direciona as requisições para os serviços corretos com base em regras configuráveis.
- **Filtragem**: Permite aplicar filtros para manipular requisições e respostas, como autenticação, logging e manipulação de cabeçalhos.
- **Resiliência**: Integra-se facilmente com bibliotecas como **Resilience4j** para implementar padrões de resiliência, como circuit breakers e retries.
- **Autenticação e Autorização**: Suporta integração com sistemas de autenticação, como OAuth2 e JWT, para proteger os serviços.

## Arquitetura

O microserviço de gateway se posiciona entre os clientes e os microserviços da aplicação. Ele intercepta todas as requisições, aplica a lógica de roteamento e, se necessário, realiza transformações nas mensagens antes de encaminhá-las. A arquitetura típica é ilustrada a seguir:
[Cliente] --> [Spring Cloud Gateway] --> [Microserviço A] | --> [Microserviço B] | --> [Microserviço C]

## Benefícios

- **Simplicidade**: Centraliza o gerenciamento de requisições em um único ponto, simplificando a comunicação entre os serviços.
- **Escalabilidade**: Facilita a adição de novos serviços e a modificação de rotas sem impactar os clientes.
- **Segurança**: Permite a implementação de autenticação e autorização em um único lugar, protegendo todos os microserviços.
- **Monitoramento**: Integra-se com ferramentas de monitoramento e observabilidade para rastrear o desempenho e a saúde dos serviços.

## Tecnologias Utilizadas

- **Java**: 11 (2021.0.1)
- **Spring Boot**: (2.6.5) Framework para desenvolvimento de aplicações Java.
- **Spring Cloud Gateway**: Framework para construção de gateways de API.
- **Eureka Client**: Com isso o gateway será incluido no Eureka Server com uma porta padrão 8080 e irá registrar os clients

## Como Usar

Será o terceiro microserviço a ser inicializado na sequência configurada no Docker-compose que ficará disponível na raiz do diretório dos Microserviços.

## Contato

Para dúvidas ou sugestões, entre em contato:

[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/jorge-maia-dev-java/)
[![E-mail](https://img.shields.io/badge/-Email-0077B5?style=for-the-badge&logo=microsoft-outlook&logoColor=white)](mailto:jsm.java@gmail.com)
[![GitHub](https://img.shields.io/badge/GitHub-0077B5?style=for-the-badge&logo=github&logoColor=white)](https://github.com/j0rg3m414)
[![Blogger](https://img.shields.io/badge/Blogger-FF5722?style=for-the-badge&logo=blogger&logoColor=white)](https://putzcomonaopenseinissoantes.blogspot.com/)
