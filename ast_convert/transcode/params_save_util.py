def global_param_init():  # 初始化
    global func_dict
    global class_def_dict
    global class_func_list

    # 存储当前源文件中的方法，key为方法名 value为方法节点
    func_dict = dict({})
    # 保存当前源代码内的类代码
    class_def_dict = dict({})
    # 保存类方法列表，如果扫描到的方法名等于类方法名就跳过这个方法的处理
    class_func_list = set([])


def get_func_dict():
    return func_dict


def set_func_dict(value):
    global func_dict
    func_dict = value


def get_class_def_dict():
    return class_def_dict


def set_class_def_dict(value):
    global class_def_dict
    class_def_dict = value


def get_class_func_list():
    return class_func_list


def set_class_func_list(value):
    global class_func_list
    class_func_list = value
