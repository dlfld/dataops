# 项目整体逻辑
## 前端
+ 前端有两个接口的配置。
    第一个接口dev-api是对接的java的网关，主要的接口就是提交画出来的流程图数据、获取已经集成的节点信息。
    第二个接口是dataapi是对接python端的api，主要的功能就是上传需要处理的数据文件到python服务器所在的物理机上。
+ 前端流程图使用的是 <a href="https://github.com/joyceworks/flowchart-vue"> flowchart</a>
+ UI组件库使用的是Element-ui
+ 在前端向后端提交流程图数据的之前还需要经过一层<b>解释层</b>,解释层的主要作用就是对节点信息进行拓扑排序。
+ 前端的配置

## Java端
+ Java使用的是SpringCloud分布式的结构
+ Taskhandle主要是对接前端，获取前端提交的流程图数据，对前端提交的流程图数据和数据文件的元数据信息进行封装。封装完成之后一个流程图便是一个task实体类，一个Task把它看作是一个任务，调度端一次性对任务进行调度完成。
+ Taskhandle对Task任务封装完成之后使用Kafka发送消息到Schedule（调度）端进行调度，调度端在Kafka中收到Task之后对Task中的节点信息进行调度和参数表的更新。
+ 在对一次的任务调度完成之后暂时使用的是qq机器人通知的模式（这个得删掉） 因此暂时没有操作完成之后的通知。
+ 目前schedule对Python端的web服务调用使用的是http的方式并且使用的是Java的Hutool工具类来完成具体的http请求。
+ 目前数据结果的返回方式都是使用的存数据文件然后发送下载链接的方式，因此数据文件位置的配置在配置文件中。
+ Java中配置了实验室服务器的docker，在maven中打包的时候会自动打包到服务器的docker中，（这个得删掉，或者是改成公司服务器）

## Python端
+ Python端使用的web服务框架是Fast Api
+ 在run.py中引入了api和base_api
    + api是后面模块集成的时候所放的包。
    + base_api是项目框架中需要写的接口。
    + 这两个包引入了，但是没有用，如果删掉会报错。因为fastapi添加web接口的时候用的不同的router所以要在run中添加代码，为了去掉这一步操作所以写了一个工具类（router_utils.py）使得全部的接口都是使用同一个router。
+ 在api包下面，每一个接口都是一个模块向外提供的服务，因为前端要展示模块的信息，因此需要统计api下所有的接口和接口的一些信息，因此引入了装饰器（主要的使用方式就类似于aop吧，在项目启动的时候扫描到所有的模块接口和模块的信息，并封装到一个模块信息表里面，当前端对模块信息进行请求的时候就返回这个模块信息表）  具体的操作在aop这个文件夹下。
+ 除了@func_config注解有统计功能之外还需要对输入的元数据信息进行必要的处理和对计算模块计算完成之后的返回信息进行必要的处理。具体代码在aop/data_func.py中
+ 项目的配置文件在static/config.ini中读取配置文件每一项直接封装为一个方法，在/utils/config_parse_util.py中。