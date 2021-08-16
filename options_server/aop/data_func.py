from pydantic import BaseModel


class Params(BaseModel):
    items: list = None


from utils.router_utils import get_router

router = get_router()


class Options:
    options = []


'''

这是方法上面的注解，接收的是data的字典
目前版本主要是注解 url、desc、name 用来自动生成options的列表，因为后期可能会加其他字段，因此选用字典的形式进行穿参

工作：
    获取url和desc并添加到现在维护的列表当中去
'''


def func_config(data: dict,read_file_func):
    print("进来了func_config，他的参数有：", data)
    Options.options.append(data)

    def parser_data(func):
        print("进来了parser_data")

        @router.post(data['optUrl'], summary=data['optName'])
        async def wrapper(params: Params):
            print(params)
            # params = args[0]
            desc = data['desc']
            for item in params.items:
                if item['desc'] == desc:
                    # 动态调用adapter

                    # handle_res = func(item)
                    handle_res = func(read_file_func(item['location']))

                    params.items.append(handle_res)
            return params

        return wrapper

    return parser_data
