Link apresentação no TDC 2017 - Florianópolis: https://www.slideshare.net/emmanuelnerisouza/criando-uma-arquitetura-escalavel-para-processamento-de-arquivos-com-micro-servicos-e-spring-boot

# Solução de micro serviços para processamento de arquivos

Este projeto é uma POC originada de um estudo que visa escalar soluções de processamento de arquivos em termos de sistemas e não apenas de infraestrutura, com isso, foi utilizados conceitos de decomposição de software proposto pelas arquiteturas de micro serviços para atingir uma estrutura de software flexível como demonstrado na imagem a seguir. 

![alt tag](https://github.com/emmanuelneri/solucao-processamento-arquivo/blob/master/arquivo/microservices-processamento-arquivo.png)

# Tecnologias
- Java 8
- Spring Boot 1.5.2
    - spring-boot-starter
    - spring-boot-starter-activemq
    - spring-boot-starter-data-mongodb
    - spring-boot-starter-data-jpa
    - spring-boot-starter-validation
    - spring-boot-starter-web
    - spring-boot-starter-jdbc
- Tomcat (Embedded no Spring Boot)
- MongoDB 3.3.0
- Postgres 9.4
- ActiveMQ 5.12.0

# Configuração de ambiente

- Pasta de leitura dos arquivos
  -  Criar pasta para leitura dos arquivos
     - Crias as pastas **novos**, **bkp** e **erros**, exemplo:
         - /Users/emmanuelneri/Documents/arquivos/novos/
         - /Users/emmanuelneri/Documents/arquivos/bkp/
         - /Users/emmanuelneri/Documents/arquivos/erros/
  -  Configurar pasta para leitura dos arquivos
        - Configurar o properties solucao-processamento-arquivo/leitor/src/main/resources/leitor.properties
- Armazenamentos dos arquivos xmls
  - Inicializar MongoDB
    - ```./mongod```
    - Disponível no endereço: localhost:27017
  - Criar collection no MongoDB
    - ```db.createCollection("notaFiscalXml")```
    - A estrutura da collection NotaFiscalXml será criada pela aplicação leitor
- Fila de processamento
  - Inicializar ActiveMQ
    - ```sh /activemq console```
    - Disponível no endereço: localhost:61616
  - Criar fila(Queue) 
    - Acesso visual ao admin do ActiveMQ: http://localhost:8161/admin/queues.jsp
    - Criar duas filas
      - nota.fiscal.queuee
      - nota.fiscal.erro.queue
- Armazenamentos das notas fiscais processadas
   - Inicializar Postgres
      - ```server.log start```
      - Disponível no endereço: localhost:5432
   - Criar schema
      - create database processamento-arquivo
    - Tabelas
      - As tabelas serão criadas quando executar a aplicação processador
      
# Execução

A execução das aplicações são feitas através do de um comando Maven que envoca a inicialização do Spring Boot.

- leitor
    -  cd /solucao-processamento-arquivo/leitor
    - ```mvn spring-boot:run```
 - processador
    -  cd /solucao-processamento-arquivo/processador
    - ```mvn spring-boot:run```
 - disponibilizador
    -  cd /solucao-processamento-arquivo/disponibilizador
    - ```mvn spring-boot:run```
 
 # Utilização
 
- leitor: Apenas é executado em background, lendo as notas do diretório configurado e enviando para fila e amazenando no MongoDB.
- processador: Apenas é executado em background, recebendo arquivos da fila, processando-os e persistindo no Postgres. 
- disponibilizador: Pode ser acessado via APIs, onde disponibiliza os serviços abaixo para consulta dos dados armezados durante o processo.
    - http://localhost:8080/nota-fiscal
    - http://localhost:8080/nota-fiscal/{cnpjEmitente}/{numeroNotaFiscal}
    - http://localhost:8080/arquivos
    - http://localhost:8080/arquivos/{id}


 
