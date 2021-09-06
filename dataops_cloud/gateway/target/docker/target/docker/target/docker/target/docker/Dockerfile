FROM lpicanco/java11-alpine
ADD target/gateway.jar gateway.jar
EXPOSE 18003
ENTRYPOINT ["java","-jar","/gateway.jar"]

