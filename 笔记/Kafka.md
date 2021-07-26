# 消息系统原理

+ 一个消息系统负责将数据从一个应用传递到另一个应用，应用只需要关注数据，无需关注数据在两个或多个应用间是如何传递的

  <hr/>

   ## 点对点消息传递

  + 在点对点消息系统中，消息持久化到一个队列中。此时，将有一个或多个消费者获取消费队列中的数据。但是一条消息只能被消费一次。
  + 当一个消费者消费了队列中的某条数据之后，该条数据则从消息队列中删除。
  + 该模式即使有多个消费者同时消费数据，也能保证数据处理的顺序。
  + 基于推送模型的消息系统，由消息代理记录消费状态。

  

  ## 发布订阅消息传递

  - 在发布-订阅消息系统中，消息被持久化到一个topic中。
  - 消费者可以订阅一个或多个topic，消费者可以消费该topic中的所有数据，同一条数据可以被多个消费者消费，数据消费后不会立马删除
  - 在发布-订阅消息系统中，消息的生产者称为发布者，消费者称为订阅者。
  - kafka采取拉取模型（poll)由自己控制消费速度，以及消费进度，消费者可以按照任意的偏移量进行消费。

# Kafka 的优点



解耦 冗余 扩展性 灵活性&峰值处理能力 可恢复性 可恢复性 顺序保证	缓冲	异步通信



# Kafka系统架构

## broker

Kafka 集群包含一个或多个服务器，服务器节点称为broker

## topic

- 每条发布到Kafka集群的消息都有一个类别，这个类别被称为topic
- 类似于数据库的表名或者Es的index
- 物理上不同的topic的消息被分开存储
- 逻辑上一个topic的消息虽然保存于一个或多个broker上但用户只需指定消息的Topic即可生产或消费数据而不必关心数据存于何处

## Partition

- topic中的数据分割为一个或者多个partition
- 每个topic至少有一个partition，每当生产者生产数据的时候，根据分配策略，选择分区，然后将消息追加到指定的分区末尾（队列）
- 每条消息都会有一个自增的编号
- 每个partition中的数据使用多个segment文件存储
- partition中的数据是有序的，不同partition间的数据丢失了数据的顺序
- 如果topic有多个partition 消费数据时就不能保证数据的顺讯，严格保证消息的消费顺序的场景下，需要讲partition的数量设置为1

## leader

- 每个partition有多个副本，其中有且仅有一个作为leader，leader是当前负责数据读写的partition

## follower

- Follower 跟随leader，所有写请求都通过leader路由，数据变更会广播给所有的follower，follower与leader保持数据同步，
- 如果leader失效，则将从follower中选举出一个新的leader 
- 当follower挂掉、卡住或者同步太慢，leader会把这个follower从in sync replicas（ISR）列表中删除，重新创建一个follower

## replication

- Kafka的replication复制机制是其可靠性的保证，即为每个分区数据提供多个副本。

## producer

- 生产者即数据的发布者，该角色将消息发不到Kafka的topic中

## consumer

- 消费者可以从broker中读取数据，消费者可以消费多个topic中的数据

## consumer group

- 每个consumer属于一个特定的consumer group（可为每个consumer指定group name 若不指定group name 则属于默认的group）
- 将多个消费者集中到一起去处理某一个topic中的数据，可以更快的提高数据的消费能力
- 整个消费者组共享一组偏移量（防止数据被重复读取）因为一个topic有多个分区

## offect偏移量

- 可以唯一的标识一条消息
- 偏移量决定读取数据的位置，不会有线程安全的问题，消费者通过偏移量来决定下次读取的消息
- 消息被消费之后，并不会马上删除，这样多个业务就可以重复使用kafka的消息
- 我们某一个业务也可以通过修改偏移量达到重新读取消息的目的，偏移量由用户控制
- 消息最终还是会被删除的 生命周期是一周

## zookeeper

- Kafka通过zookeeper来存储集群的meta信息



# 数据的安全性

## producer delivery guarantee

- 0 at least one 消息绝不会丢，但可能会重复传输
- 1 at most once 消息可能会丢，但绝不会重复传输
- 3 exactly once 每条消息肯定会被传输一次且仅传输一次

produces 可以选择是否为数据的写入接收ack，有以下几种ack的选项：

+ acks=0  producer在ISR中的leader已成功接收到的数据并确认后发送下一条message

+ acks=1 这意味着producer无需等待broker的确认而继续发送下一批消息

+ acks=all  producer需要等到ISR中所有的Follower都确认收到数据后才算完成一次发送。可靠性最高

  

