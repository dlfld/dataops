+ 整理装饰器的原理、画图、调用过程   

+ ```
  @func_config配置的详细信息在前端也是可以获取到的，尽量详细描述。
  ```

+ 装饰器 读取文件和写文件

+ demo run起来

+ 周一

+ 返回值









# 装饰器原理

+ 在执行一个方法之前需要执行的操作。

  ```python
  def use_logging(func):
    def wrapper(*args, **kwargs):
      return func(*args, **kwargs)
    return wrapper
  
  def bar():
     print('i am bar')
  bar = use_logging(bar)
  bar()
  # 简化之后的写法就是在bar方法上加注解
  @use_logging
  def bar():
    print('i am bar')
  #他就会在调用bar之前调用use_logging方法，然后用use_logging方法来调用bar
  ```

+ 如果需要在装饰器中参数的传入的话

  ```python
  #这一层（func_config）data表示的就是在注解上传入的参数。并且这个方法（func_config）是在项目一开始运行的时候就会执行
  def func_config(data: dict):
    #这一层也是在项目开始运行的时候就会执行
      def parser_data(func):
        #这一层就是在具体的方法调用的时候才会执行，传入的参数func就是添加func_config注解的方法，这个方法在wrapper中调用并动态的填入参数和获取返回值
          async def wrapper(params: Params):
              print(params)
              # params = args[0]
              desc = data['desc']
              for item in params.items:
                  if item['desc'] == desc:
                      #这一步就是调用添加了func_config注解的方法并获取他的返回值handle_res
                      handle_res = func(item)
                      #对结果进行封装
                      params.items.append(handle_res)
              #返回结果
              return params
          return wrapper
      return parser_data
    
  ```

+ 实际效果

  ```python
  def func_config(data: dict):
      print("进来了func_config，他的参数有：",data)
      #在项目启动之后有多少个方法上添加了这个注解这个方法就会运行多少次，并且每个方法注解上的配置都会通过data传过来。
      #这个时候封装了一个全局的模块表，获取到data之后把data添加到表里面，并且python端web服务会有一个接口向前端提供模块信息（返回的是全局模块表内的内容）因此这个装饰器中配置的参数（data里面的，也就是方法上的注解中配置的信息）都会全部返回给前端
      Options.options.append(data)
      def parser_data(func):
          print("进来了parser_data")
          #把这个fast api的装饰器加到wapper上，这样就可以通过url调用的时候进入wapper wapper对参数进行修改之后再调用自己装饰器中传入的方法
          @router.post(data['optUrl'],summary=data['optName'])
          async def wrapper(params: Params):
              print(params)
              # params = args[0]
              desc = data['desc']
              for item in params.items:
                  if item['desc'] == desc:
                      # 动态调用adapter
  
                      handle_res = func(item)
  
                      params.items.append(handle_res)
              return params
  
          return wrapper
  
      return parser_data
    
    
    
  进来了func_config，他的参数有： {'optUrl': '/demo', 'optDesc': 'demo', 'optName': '一个demo'}
  进来了parser_data
  进来了func_config，他的参数有： {'optUrl': '/add', 'optDesc': '执行加法操作', 'optName': '加法', 'desc': 'start desc', 'adapter': 'asd'}
  进来了parser_data
  进来了func_config，他的参数有： {'optUrl': '/min', 'optDesc': '实现减操作', 'optName': '减法', 'desc': 'start desc'}
  进来了parser_data
  进来了func_config，他的参数有： {'optUrl': '/rid', 'optDesc': '执行乘法操作', 'optName': '乘法'}
  进来了parser_data
  进来了func_config，他的参数有： {'optUrl': '/div', 'optDesc': '执行除法操作', 'optName': '除法'}
  进来了parser_data
  进来了func_config，他的参数有： {'optUrl': '/sum  ', 'optDesc': '求和', 'optName': '求和'}
  进来了parser_data
  
  
  ```

  

