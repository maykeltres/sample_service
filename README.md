
```
   ___        _   _                       _     _
  / / /\     | \ | |                     (_)   | |
 /\/ /  \    |  \| | ___  ___   __ _ _ __ _  __| |
 \/\/    \   | . ` |/ _ \/ _ \ / _` | '__| |/ _` |
 /\/\    /   | |\  |  __/ (_) | (_| | |  | | (_| |
 \/\ \  /    |_| \_|\___|\___/ \__, |_|  |_|\__,_|
  \_\_\/                       |___/
  
```

# Micro Serviço

Projeto de micro serviço construído de acordo com os padrões definidos pelo time de arquitetura da Neogrid.

## Comandos

### Build do projeto

Para realizar o build do projeto no seu ambiente de desenvolvimento utilize o comando maven:

```
	mvn clean install
```

### Execução local do micro serviço

Para facilitar um teste inicial do micro-serviço gerado você pode utilizar um banco de dados em memória (H2) executando o comando:

```
	mvn spring-boot:run "-Drun.profiles=h2"
```

Para executar localmente o micro serviço criado utilize o comando maven:

```
	mvn spring-boot:run "-Drun.profiles=local"
```

Para mais informações sobre como executar a sua aplicação consulte a documentação ["Running Your Application"][spring-boot-running] do Spring Boot.

### Spring Profiles

#### Profile local

Para simplificar o desenvolvimento local de aplicações este profile vem pré-configurado para:
- Desabilitar o cliente de descoberta de serviços (_Eureka_)
- Desabilitar o uso de configurações externas a aplicação (_Config Server_)
- Configurar as propriedades `spring.datasource` e `spring.jpa` de acesso ao:
    - Banco de dados: `SQL Server`
    - Servidor: `localhost`
    - Porta: `1040`
    - Base de dados: `car-db`
    - Usuário/senha: `liquibase`

Você pode criar esta base de dados e conceder os acessos necessários ao usuário ou modificar estas informações no descritor `application.yaml` para os valores desejados.

#### Profile h2

Para facilitar o teste inicial do micro serviço este profile vem pré-configurado para habilitar o uso do banco de dados em memória _H2_ e a sua interface gráfica [H2 Console][localhost-h2-console] que permite acessar a base de dados.

Na tela inicial da interface gráfica informe no campo _JDBC URL_ o valor `jdbc:h2:mem:car-db` e clique no botão _Connect_ para realizar o acesso.

Para mais informações consulte o [tutorial][h2-tutorial] do H2.

<!-- Identifiers, in alphabetical order -->

[spring-boot-running]: https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html
[localhost-h2-console]: http://localhost:8080/h2-console
[h2-tutorial]: http://www.h2database.com/html/tutorial.html