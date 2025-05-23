<h1>Descrição do Projeto</h1>

# OdontoGenda - Documentação Técnica Completa

## Sumário

1. [Apresentação](#apresentação)
2. [Visão Geral](#visão-geral)
3. [Funcionalidades Principais](#funcionalidades-principais)
4. [Pré-requisitos](#pré-requisitos)
5. [Configuração do Projeto](#configuração-do-projeto)

   * [Clonando o Repositório](#clonando-o-repositório)
   * [Variáveis de Ambiente](#variáveis-de-ambiente)
6. [Dependências](#dependências)
7. [Estrutura do Projeto](#estrutura-do-projeto)
8. [Instalação e Execução](#instalação-e-execução)
9. [Configurações Detalhadas](#configurações-detalhadas)
10. [Endpoints e Fluxos](#endpoints-e-fluxos)
11. [Considerações Finais](#considerações-finais)


<h4>Integrantes</h4>

Lucas Bastos - 553771<br/>
Erick Lopes - 553927<br/>
Marcelo Galli - 553654

## Apresentação

O projeto desenvolvido é uma aplicação web baseada no padrão MVC utilizando Spring Boot. Ele permite o cadastro e autenticação de dois tipos de usuários – Clientes (pacientes) e Dentistas – com uma validação específica no cadastro: o nome de usuário para clientes deve iniciar com "U" e para dentistas com "D". A aplicação oferece telas dinâmicas construídas com Thymeleaf, permitindo ao usuário:

* Selecionar a opção de cadastro (Cliente ou Dentista) a partir da tela inicial.<br/>
* Realizar o cadastro com a validação de prefixo, onde uma exceção customizada é lançada caso a regra não seja atendida, retornando uma mensagem de alerta.<br/>
* Efetuar o login, que identifica o tipo de usuário com base no prefixo do nome de usuário e redireciona para uma listagem correspondente (clientes ou dentistas).<br/>
* Visualizar as listagens dos usuários cadastrados conforme o tipo selecionado.<br/>
* A aplicação integra com um banco de dados Oracle, onde as entidades estão mapeadas utilizando JPA/Hibernate. A configuração da conexão é feita através do arquivo application.properties, e o projeto foi configurado para ser executado com Gradle.<br/>

<h3>Tecnologias Utilizadas</h3>

* Java & Spring Boot: Plataforma principal para construção da aplicação, facilitando a criação de microserviços e aplicações web robustas.<br/>
* Gradle: Sistema de build que gerencia dependências e o processo de compilação/execução da aplicação.<br/>

  * Spring Data JPA: Para a abstração e gerenciamento das operações de persistência e mapeamento objeto-relacional (ORM).<br/>
* Thymeleaf: Motor de templates que possibilita a criação de páginas HTML dinâmicas e integradas com os dados da aplicação.<br/>
* Oracle Database: Banco de dados utilizado para armazenar as informações dos usuários, configurado via JDBC.<br/>
* MVC (Model-View-Controller): Padrão arquitetural que organiza a aplicação em camadas, facilitando a manutenção e escalabilidade.<br/>
* Exceção Customizada & ControllerAdvice: Mecanismos implementados para validação de regras de negócio (ex.: prefixo dos usuários) e tratamento global de exceções, proporcionando mensagens de erro amigáveis ao usuário.<br/>

## Visão Geral

OdontoGenda é uma aplicação **Spring Boot** com **Thymeleaf** que gerencia cadastros de clientes e dentistas, fornecendo autenticação com perfis distintos, internacionalização, mensageria, monitoramento e integração com IA para sugestões de horários.

## Funcionalidades Principais

* Autenticação com **Spring Security** (clientes e dentistas)
* **Internacionalização** (Português e Inglês)
* **Mensageria** com RabbitMQ (envio de eventos ao cadastrar usuário)
* **Monitoramento** via Spring Boot **Actuator**
* **Inteligência Artificial** (Spring AI + OpenAI ChatClient)

## Pré-requisitos

* Java 17
* Gradle 8.x
* RabbitMQ rodando em `localhost:5672`
* Banco de dados Oracle (ou H2 para testes em memória)
* Chave de API OpenAI (variável `OPENAI_API_KEY`)

## Configuração do Projeto

### Clonando o Repositório

```bash
git clone <URL_DO_REPO>
cd api-with-thymeleaf
```

### Variáveis de Ambiente

Defina a chave de API do OpenAI antes de rodar:

```bash
export OPENAI_API_KEY=your_api_key_here
```

## Dependências

* Spring Boot Starter Web
* Spring Boot Starter Thymeleaf
* Spring Boot Starter Data JPA
* Spring Boot Starter Security
* Spring Boot Starter Actuator
* Spring Boot Starter AMQP
* Spring AI Starter Model OpenAI
* Oracle JDBC e H2

## Estrutura do Projeto

```
api-with-thymeleaf/
├─ src/
│  ├─ main/
│  │  ├─ java/com/example/odontogenda/
│  │  │  ├─ ai/           → Serviço e Controller de IA
│  │  │  ├─ auth/         → Interface UsuarioBase
│  │  │  ├─ config/       → WebConfig (i18n)
│  │  │  ├─ messaging/    → config, producer, consumer
│  │  │  ├─ monitoring/   → HealthIndicator, InfoContributor
│  │  │  ├─ security/     → SecurityConfig, UserDetailsServiceImpl
│  │  │  ├─ controllers/  → Login, Cadastro, Listagem, Default, AiController
│  │  │  ├─ models/       → Cliente, Dentista, DTO de mensagem
│  │  │  ├─ repositories/ → JpaRepositories
│  │  │  └─ services/     → ClienteService, DentistaService, AiService
│  │  └─ resources/
│  │     ├─ templates/    → Thymeleaf (login.html, listagem.html, etc.)
│  │     ├─ static/       → CSS e JS
│  │     ├─ messages*.properties
│  │     └─ application.properties
└─ build.gradle
```

## Instalação e Execução

1. Build:

   ```bash
   ./gradlew clean build
   ```
2. Executar:

   ```bash
   ./gradlew bootRun
   ```
3. Acesse: `http://localhost:8080/login`

## Configurações Detalhadas

### Spring Security

* Configurado em `SecurityConfig.java` com `SecurityFilterChain`.
* Perfis: `ROLE_CLIENTE`, `ROLE_DENTISTA`.
* `UserDetailsServiceImpl` carrega usuário via `findByUsuario`.

### Internacionalização (i18n)

* `WebConfig.java` define `MessageSource`, `LocaleResolver` (cookie) e interceptor `?lang=`.
* Arquivos `messages.properties` e `messages_en.properties`.
* Templates usam `th:text="#\{chave\}"`.

### Mensageria (RabbitMQ)

* `RabbitConfig.java` define fila, exchange e binding.
* `UsuarioProducer` dispara mensagem no cadastro.
* `UsuarioConsumer` lê e loga evento.

### Monitoramento (Actuator)

* Dependência `spring-boot-starter-actuator`.
* Endpoints expostos em `/actuator` (health, info, metrics, etc.).
* `CustomInfoContributor` e `DatabaseHealthIndicator` adicionam detalhes.

### Inteligência Artificial (Spring AI)

* Dependência `spring-ai-starter-model-openai`.
* `AiService` usa `ChatClient` para `CompletionRequest`.
* `AiController` expõe `/ia/sugestao`.

## Endpoints e Fluxos

| Endpoint                             | Método | Descrição                          |
| ------------------------------------ | ------ | ---------------------------------- |
| `/login`                             | GET    | Formulário de login                |
| `/login`                             | POST   | Processa autenticação              |
| `/cadastro/cliente`                  | POST   | Cadastra cliente + envia mensagem  |
| `/cadastro/dentista`                 | POST   | Cadastra dentista + envia mensagem |
| `/listagem?tipo={cliente\|dentista}` | GET    | Lista usuários conforme perfil     |
| `/default`                           | GET    | Redireciona pós-login              |
| `/actuator/health`                   | GET    | Health check                       |
| `/actuator/info`                     | GET    | Informações da aplicação           |
| `/ia/sugestao?contexto=xxx`          | GET    | Retorna sugestão de IA             |

## Considerações Finais

Este documento cobre toda a estrutura e configurações necessárias para atender aos requisitos da 4ª Sprint: aplicação full MVC com autenticação, i18n, mensageria, monitoramento e IA. Qualquer dúvida ou necessidade de ajustes, entre em contato com a equipe de desenvolvimento.



Link vídeo YT: https://youtu.be/lBkLdMIKzfw
