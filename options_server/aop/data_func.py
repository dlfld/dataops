class Options:
    options = []


'''

这是方法上面的注解，接收的是data的字典
目前版本主要是注解 url、desc、name 用来自动生成options的列表，因为后期可能会加其他字段，因此选用字典的形式进行穿参

工作：
    获取url和desc并添加到现在维护的列表当中去
'''


def func_config(data: dict):
    print(data)
    Options.options.append(data)

    def parser_data(func):
        def wrapper(*args, **kwargs):
            return func(*args)

        return wrapper

    return parser_data



# def run():
#     print("i'm run")
