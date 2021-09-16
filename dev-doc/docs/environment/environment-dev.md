# 项目开发和部署的相关环境
## Java 端
+ JDK11

## Python端
+ Python3.9

## 前端
+ Vue2

## 开发需要启动的相关环境
 + nacos 2.x版本 docker启动 
 ```bash 
 docker run --env MODE=standalone --name nacos -d -p 8848:8848 nacos/nacos-server
 ```
 + zookeeper
```bash
    docker run -d --name zookeeper -p 2181:2181 -t wurstmeister/zookeeper
```
+ kafka
```bash
    docker run -d --name kafka \
    -p 9092:9092 \
    -e KAFKA_BROKER_ID=0 \
    -e KAFKA_ZOOKEEPER_CONNECT=10.0.0.101:2181 \
    -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://10.0.0.101:9092 \
    -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 wurstmeister/kafka
```
