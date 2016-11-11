#Solução de micro serviços para processamento de arquivos

![alt tag](https://github.com/emmanuelneri/solucao-processamento-arquivo/blob/master/microservices-processamento-arquivo.png)

#Tecnologias
- Java 8
- Spring Boot 1.4.0
- MongoDB 3.3.0
- Postgres 9.4

#Configuração
- Criar base collection no MongoDB
  - db.createCollection("arquivos")
- Criar base no Postgres
  - create table nfes

#Execução
mvn spring-boot:run
