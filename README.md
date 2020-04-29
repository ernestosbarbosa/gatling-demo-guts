# Gatling Demonstration - GUTS-RS

Projeto de exemplo de uso do [Gatling](https://gatling.io/) para criação e execução de testes de performance.

## Estrutura de pastas

📦gatling-demo-guts  
 ┣ 📂src  
 ┃ ┣ 📂test  
 ┃ ┃ ┗ 📂scala  
 ┃ ┃ ┃ ┣ 📜Engine.scala  
 ┃ ┃ ┃ ┣ 📜IDEPathHelper.scala  
 ┃ ┃ ┃ ┗ 📜Recorder.scala  
 ┃ ┃ ┗ 📂resources  
 ┃ ┃ ┃ ┣ 📂bodies  
 ┃ ┃ ┃ ┃ ┗ 📜recorder.conf  
 ┃ ┃ ┃ ┣ 📂data  
 ┃ ┃ ┃ ┃ ┗ 📜recorder.conf  
 ┃ ┃ ┃ ┣ 📜gatling.conf  
 ┃ ┃ ┃ ┣ 📜logback.xml  
 ┃ ┃ ┃ ┗ 📜recorder.conf  
 ┣ 📜pom.xml  
 ┗ 📜README.md  

## Clonando o projeto

Para ter o projeto na sua máquina é necessário clonar ele usando o comando `git clone`. Siga os seguintes passos para clonar o projeto:
1. Abra o gitbash
2. Navegue até o local onde deseja que o projete fique
3. Cole o comando `git clone projeto.git`  
  
Pronto, o projeto agora está clonado.

## IDE

Como IDE sugiro o [IntelliJ](https://www.jetbrains.com/idea/).

## Testes

Para executar os testes pela IDE é possível executar o arquivo `Engine.scala`.

Para configurar a quantidade de usuários, bem como o tipo de carga a ser realizada, é necessário ajustar o arquivo `SimulacaoApi.scala`.

### Execução por linha de comando

Para executar os testes pelo terminal pode utilizar o comando:

```shell
mvn gatling:test -Dgatling.simulationClass=simulation.SimulationApi
```

### Gatling Recorder

É possível gravar suas requisições e criar um script de testes de forma automática utilizando o [Gatling Recorder](https://gatling.io/docs/current/http/recorder/), iniciando o mesmo pelo comando:

```shell
mvn gatling:recorder
```

