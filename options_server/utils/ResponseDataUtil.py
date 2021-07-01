from typing import TypeVar, Generic

from pydantic.generics import GenericModel

T = TypeVar('T')  # 泛型类型 T


class RestfulModel(GenericModel, Generic[T]):
    code: int
    msg: str
    data: T


class Result():
    # 返回成功的 返回类  自定义消息
    def buildSuccess_md(self, msg, data):
        return RestfulModel(code=200, msg=msg, data=data)

    # 返回成功的返回类
    def buildSuccess(self):
        return RestfulModel(code=200, msg="成功", data=None)

    # 返回类只有信息
    def buildSuccess_m(self, msg):
        return RestfulModel(code=200, msg=msg, data=None)

    # 返回成功，只有数据
    def buildSuccess_d(self, data):
        return RestfulModel(code=200, msg="成功", data=data)

    # 返回失败的返回类 自定义消息
    def buildError_d(self, data):
        return RestfulModel(code=500, msg="失败", data=data)

    # 返回失败的返回类
    def buildError_m(self, msg):
        return RestfulModel(code=500, msg=msg)
