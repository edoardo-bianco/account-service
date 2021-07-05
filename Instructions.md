# account-service Project Instructions
https://github.com/edoardo-bianco/account-service
Projeto com crud completo Restful, OpenApi/ Swagger; Service e Repository
 ainda não tenho incluído a configuração de banco mas os dados são simulados
 como implementação do repositório. Coloquei o código no meu github:
 https://github.com/edoardo-bianco/account-service.git
 
 Utilizei como editor o Intelli J  e Java 11 como JDK, 
 sendo assim é só fazer o git clone do repositório e importar no Intelli J (baixarr o AdoptOpenJDK eu utilizei a jdk-11.0.10+9  ) o  projeto quarkus
 
 Para rodar a api em um terminal [no mesmo IntelliJ ] entrar na root do projeto e executar o comando maven: mvn compile quarkus:dev ou mvnw compile quarkus:dev caso não tenham instalado o maven
 sso vai permitir usar Live coding in dev mod (ou seja pega automaticamente as alterações). Feito isso é possível acessar o swagger da api à url: http://localhost:8080/accounts-swagger-ui/  e chamar um serviço rest de bem vindo com:  http://localhost:8080/resteasy/hello
 para testar a API ( crud) criei uma collection postman e um  ppt onde explica como criar tudo o que está acima e com referências a documentações e livros baixáveis gratuitamente do site da redhat (muito uteis )