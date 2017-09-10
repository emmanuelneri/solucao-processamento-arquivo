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
- Spring Integration 4.3.9.RELEASE    
- Tomcat (Embedded no Spring Boot)
- MongoDB 3.3.0
- Postgres 9.4
- ActiveMQ 5.12.0

# Configuração de ambiente

- Pasta de leitura dos arquivos
  -  Criar pasta para leitura dos arquivos
     - /Users/emmanuelneri/Documents/arquivos/
  -  Configurar pasta para leitura dos arquivos
        - Configurar o properties solucao-processamento-arquivo/leitor/src/main/resources/leitor.properties
- Armazenamentos dos arquivos xmls
  - Inicializar MongoDB
    - ```./mongod```
    - Disponível no endereço: localhost:27017
  - Criar collection no MongoDB (não obrigatório)
    - ```db.createCollection("notaFiscalXml")```
    - A estrutura da collection NotaFiscalXml será criada pela aplicação leitor
    - Obs: Caso não criado a collection, a aplicação irá criar
- Fila de processamento
  - Inicializar ActiveMQ
    - ```sh /activemq console```
    - Disponível no endereço: localhost:61616
  - Criar fila(Queue) (não obrigatório)
    - Acesso visual ao admin do ActiveMQ: http://localhost:8161/admin/queues.jsp
    - Criar duas filas
      - nota.fiscal.queuee
      - nota.fiscal.erro.queue
    - Obs: Caso não criado a fila, a aplicação irá criar
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
    
# Configuração de ambiente com Docker

1 - Execute o build das aplicações e as construções dsa imagens nos 3 projetos

- leitor
    -  cd /solucao-processamento-arquivo/leitor
    - ```mvn clean package dockerfile:build```
- processador
    -  cd /solucao-processamento-arquivo/processador
    - ```mvn clean package dockerfile:build```
- disponibilizador
    -  cd /solucao-processamento-arquivo/disponibilizador
    - ```mvn clean package dockerfile:build```
    
2 - Execute o Docker compose para inicializar o ambiente
  
 -  cd /solucao-processamento-arquivo
 - ```docker-compose up```
 
Pronto! Todo ambiente será criando através de containers Dockers:

```docker ps```

| CONTAINER ID | IMAGE                                      |   COMMAND                |  PORTS                                           |   NAMES                                            |
| ------------ | ------------------------------------------ | ------------------------ | ------------------------------------------------ | -------------------------------------------------- |
| 2a1871b1a2da | processamento-arquivo/disponibilizador-app |   "sh -c 'java -Djav..." | 0.0.0.0:8080->8080/tcp                           | solucaoprocessamentoarquivo_disponibilizador-app_1 |
| 0f7c14c12a5a | processamento-arquivo/processador-app      |   "sh -c 'java -Djav..." |                                                  | solucaoprocessamentoarquivo_processador-app_1      |
| ce9666f82895 | processamento-arquivo/leitor-app           |   "sh -c 'java -Djav..." |                                                  | solucaoprocessamentoarquivo_leitor-app_1           |
| d5f1b181c2c7 | mongo:3.5                                  |   "docker-entrypoint..." | 0.0.0.0:27017->27017/tcp                         | solucaoprocessamentoarquivo_file-db_1              |
| ede31b44344e | postgres:9.6                               |   "docker-entrypoint..." | 0.0.0.0:5432->5432/tcp                           | solucaoprocessamentoarquivo_nota-fiscal-db_1       |
| e5064a06626a | rmohr/activemq:5.12.0                      |   "/bin/bash -c 'bin..." | 0.0.0.0:8161->8161/tcp, 0.0.0.0:61616->61616/tcp | solucaoprocessamentoarquivo_queue_1                |
 
Observação: Os arquivos para serem processados podem ser copiados para o container do Leitor com o seguinte comando ```docker cp nf.xml solucaoprocessamentoarquivo_leitor-app_1:/tmp/arquivos/nf.xml```