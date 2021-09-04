# Python 计算端内置api文档

> ## Config配置文件工具类  

  > #### ConfigGet类 (获取配置文件内容)
   + get_data_file_path()
      + 作用：获取数据文件保存的绝对路径
      + 类型：静态方法
      + 参数：无
      + 返回值：数据文件保存的绝对路径 eg：/usr/data
   + <del>get_model_save_path()
      + 作用：获取训练的模型文件保存的位置
      + 类型：静态方法
      + 参数：无
      + 返回值：模型文件保存的绝对路径 eg：/usr/data</del>
   + get_server_host()
      + 作用：获取本机的ip和端口
      + 类型：静态方法
      + 参数：无
      + 返回值：本机ip和端口 eg：http://127.0.0.1:8000

> ## 文件操作工具类

> #### FileWriters类 (系统内部集成的写数据到文件的工具类)
   + write_data_frame_csv(data_frame)
     + 作用：将一个DadaFrame写入csv文件
     + 类型：静态方法
     + 参数：pandas的DataFrame对象
     + 返回值：FileMessage(file_full_path, file_name)对象
   + save_params(params)
     + 作用：将传入的参数（任意类型）使用joblib包的dump方法存为一个.params文件
     + 类型：静态方法
     + 参数：需要保存的数据（一个对象）
     + 返回值：FileMessage(file_full_path, file_name)对象

> #### FileReaders (系统内部集成的读数据的工具类)
   + read_csv(file_full_path)
     + 作用：读取一个csv文件并转化成一个dataFrame
     + 类型：静态方法
     + 参数：csv文件的绝对路径
     + 返回值：DataFrame对象
   + read_csv_by_csv(file_full_path)
     + 作用：读取一个csv文件并以列表的方式返回
     + 类型：静态方法
     + 参数：csv文件的绝对路径
     + 返回值：列表格式的csv数据
   + read_params(file_full_path)
     + 作用：读取一个.params的文件并返回其结果
     + 类型：静态方法
     + 参数：params文件的绝对路径
     + 返回值：存params时的值信息

> ## 实体类

> #### FileMessage (文件信息实体类)
   + file_full_path：文件的绝对路径（包括文件名)
   + file_name：文件名
