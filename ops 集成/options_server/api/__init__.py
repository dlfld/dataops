'''
api 插件，计算端模块所集成的计算方法等都在api下
'''
import os
__all__ = [item for item in os.walk('api')][0][1]
print(__all__)