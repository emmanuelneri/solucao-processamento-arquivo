#Solução de micro serviços para processamento de arquivos

![alt tag](https://github.com/emmanuelneri/solucao-processamento-arquivo/blob/master/microservices-processamento-arquivo.png)

#Tecnologias
- Java 8
- Spring Boot 1.4.0
- MongoDB 3.3.0
- Postgres 9.4
- ActiveMQ 5.12.0

#Configuração
- Criar base collection no MongoDB
  - db.createCollection("arquivos")
- Criar Queue no ActiveMQ
 - http://localhost:8161/admin/queues.jsp
 - Criar Queue com o nome "arquivo.queue"
- Criar base no Postgres
  - create database processamento-arquivo

#Execução

Infraestrutura:
 - iniciar Postgres
 - iniciar MongoDB
 - iniciar ActiveMQ

Aplicação: 
 - leitor: mvn spring-boot:run
 - processador: mvn spring-boot:run
 - disponibilizador: mvn spring-boot:run



** em desenvolvimento
