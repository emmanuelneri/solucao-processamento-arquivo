#Solução de micro serviços para processamento de arquivos

![alt tag](https://github.com/emmanuelneri/solucao-processamento-arquivo/blob/master/microservices-processamento-arquivo.png)

#Tecnologias
- Java 8
- Spring Boot 1.5.2
- MongoDB 3.3.0
- Postgres 9.4
- ActiveMQ 5.12.0

#Configuração do Ambiente
- Criar base collection no MongoDB
  - db.createCollection("notaFiscalXml")
- Criar Queue no ActiveMQ
 - Acesso visual ao admin do ActiveMQ: http://localhost:8161/admin/queues.jsp
 - Criar Queues com os nomes "nota.fiscal.queuee" e "nota.fiscal.erro.queue"
- Criar base no Postgres
  - create database processamento-arquivo
- Configurar pasta para leitura dos arquivos
  - Configurar o properties solucao-processamento-arquivo/leitor/src/main/resources/leitor.properties 
  
#Execução

Infraestrutura:
 - iniciar Postgres
   - localhost:5432
 - iniciar MongoDB
   - localhost:27017
 - iniciar ActiveMQ
   - tcp://localhost:61616

Aplicação: 
 - leitor: mvn spring-boot:run
 - processador: mvn spring-boot:run
 - disponibilizador: mvn spring-boot:run
 
 #Utilização
 
- leitor: Apenas é executado em background, lendo as notas do diretório configurado e enviando para fila e amazenando no MongoDB.
- processador: Apenas é executado em background, recebendo arquivos da fila, processando-os e persistindo no Postgres. 
- disponibilizador: Pode ser acessado via APIs, onde disponibiliza os serviços abaixo para consulta dos dados armezados durante o processo.
    - http://localhost:8080/nota-fiscal
    - http://localhost:8080/nota-fiscal/{cnpjEmitente}/{numeroNotaFiscal}
    - http://localhost:8080/arquivos
    - http://localhost:8080/arquivos/{id}


 
