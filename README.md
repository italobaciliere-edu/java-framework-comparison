# java-framework-comparison
Course completion work in computer science - FUMEC

Para executar essa aplicação é necessário estar com o docker ligado.

Para habilitar o agente de monitoramento (JMX) em uma aplicação Quarkus executada 
a partir do IntelliJ IDEA, você pode configurar as opções de inicialização para o agente JMX na configuração 
da execução da aplicação.

1 - Configurações de Execução:

Certifique-se de que sua aplicação Quarkus está sendo executada em modo de desenvolvimento.
Isso pode ser feito usando o comando ./mvnw quarkus:dev ou ./gradlew quarkusDev, dependendo de como o 
projeto está configurado.

2 - Habilitar o Agente de Monitoramento:

Para permitir o monitoramento pelo VisualVM, você pode adicionar a opção de inicialização do JVM 
-Dcom.sun.management.jmxremote ao iniciar o Quarkus. Na seção de opções de VM (VM Options), adicione 
a opção para habilitar o agente de monitoramento JMX. Adicione algo semelhante a:

    -Dcom.sun.management.jmxremote 

[//]: # (https://pt.quarkus.io/guides/openapi-swaggerui)
Para acessar o swagger da aplicação: 

    http://localhost:8085/swagger-ui/


[//]: # (https://www.youtube.com/watch?v=wWtleILrPnE&list=PLxYgR_cQignDqXT1ErgydPeHj4KQs4Dd5&index=2)
[//]: # (% https://quarkus.io/get-started/)


[//]: # (https://github.com/fmcejudo/quarkus-eureka)
[//]: # (Link da conexão com o eureka)