FROM lpicanco/java11-alpine
ADD target/task_handle.jar task_handle.jar
EXPOSE 18001
ENTRYPOINT ["java","-jar","/task_handle.jar"]