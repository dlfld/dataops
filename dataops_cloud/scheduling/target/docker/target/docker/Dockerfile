FROM lpicanco/java11-alpine
ADD target/scheduling.jar scheduling.jar
EXPOSE 18002
ENTRYPOINT ["java","-jar","/scheduling.jar"]