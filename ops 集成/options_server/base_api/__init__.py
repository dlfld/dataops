'''
base_api是这个框架基本的api，
也就是获取options，以后的连接到注册中心的系统内置api

'''
import os
__all__ = [item for item in os.walk('base_api')][0][1]
print(__all__)