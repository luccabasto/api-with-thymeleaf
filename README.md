<h1>Descrição do Projeto</h1>

O projeto desenvolvido é uma aplicação web baseada no padrão MVC utilizando Spring Boot. Ele permite o cadastro e autenticação de dois tipos de usuários – Clientes (pacientes) e Dentistas – com uma validação específica no cadastro: o nome de usuário para clientes deve iniciar com "U" e para dentistas com "D". A aplicação oferece telas dinâmicas construídas com Thymeleaf, permitindo ao usuário:
* Selecionar a opção de cadastro (Cliente ou Dentista) a partir da tela inicial.<br/>
* Realizar o cadastro com a validação de prefixo, onde uma exceção customizada é lançada caso a regra não seja atendida, retornando uma mensagem de alerta.<br/>
* Efetuar o login, que identifica o tipo de usuário com base no prefixo do nome de usuário e redireciona para uma listagem correspondente (clientes ou dentistas).<br/>
* Visualizar as listagens dos usuários cadastrados conforme o tipo selecionado.<br/>
* A aplicação integra com um banco de dados Oracle, onde as entidades estão mapeadas utilizando JPA/Hibernate. A configuração da conexão é feita através do arquivo application.properties, e o projeto foi configurado para ser executado com Gradle.<br/>

<h3>Tecnologias Utilizadas</h3>

* Java & Spring Boot: Plataforma principal para construção da aplicação, facilitando a criação de microserviços e aplicações web robustas.<br/>
* Gradle: Sistema de build que gerencia dependências e o processo de compilação/execução da aplicação.<br/>
*Spring Data JPA: Para a abstração e gerenciamento das operações de persistência e mapeamento objeto-relacional (ORM).<br/>
* Thymeleaf: Motor de templates que possibilita a criação de páginas HTML dinâmicas e integradas com os dados da aplicação.<br/>
* Oracle Database: Banco de dados utilizado para armazenar as informações dos usuários, configurado via JDBC.<br/>
* MVC (Model-View-Controller): Padrão arquitetural que organiza a aplicação em camadas, facilitando a manutenção e escalabilidade.<br/>
* Exceção Customizada & ControllerAdvice: Mecanismos implementados para validação de regras de negócio (ex.: prefixo dos usuários) e tratamento global de exceções, proporcionando mensagens de erro amigáveis ao usuário.<br/>

<h4>Integrantes</h4>

Lucas Bastos - 553771<br/>
Erick Lopes - 553927<br/>
Marcelo Galli - 553654
