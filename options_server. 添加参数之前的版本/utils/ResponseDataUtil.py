from typing import TypeVar, Generic

from pydantic.generics import GenericModel

T = TypeVar('T')  # 泛型类型 T


class RestfulModel(GenericModel, Generic[T]):
    code: int
    msg: str
    data: T


class Result:
    # 返回成功的 返回类  自定义消息
    @staticmethod
    def buildSuccess_md(msg, data):
        return RestfulModel(code=200, msg=msg, data=data)

    # 返回成功的返回类
    @staticmethod
    def buildSuccess():
        return RestfulModel(code=200, msg="成功", data=None)

    # 返回类只有信息
    @staticmethod
    def buildSuccess_m(msg):
        return RestfulModel(code=200, msg=msg, data=None)

    # 返回成功，只有数据
    @staticmethod
    def buildSuccess_d(data):
        return RestfulModel(code=200, msg="成功", data=data)

    # 返回失败的返回类 自定义消息
    @staticmethod
    def buildError_d(data):
        return RestfulModel(code=500, msg="失败", data=data)

    # 返回失败的返回类
    @staticmethod
    def buildError_m(msg):
        return RestfulModel(code=500, msg=msg)
