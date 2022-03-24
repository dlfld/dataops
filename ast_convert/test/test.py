import ast
import sys
from _ast import Return
from typing import Any

import astunparse
from astroid import Import, ImportFrom
import copy

from transcode.class_func_handle import ClassTransformer
from transcode.params_save_util import get_func_dict, get_class_def_dict, set_func_dict, set_class_def_dict, \
    global_param_init


# 自己读取的方式
def read_by_self(r_node):
    """
     获取扫描当前项目所有的方法和类，并且将他们所需要import的包存起来
    :param r_node:
    :return:
    """
    # 保存当前py文件的import语句节点
    import_nodes = []
    # 首先扫描所有的import语句，将这些语句保存到一个import语句节点列表中去
    for node in r_node.body:
        node_name = type(node).__name__
        if node_name == "Import" or node_name == "ImportFrom":
            import_nodes.append(copy.deepcopy(node))

    # 遍历每一个node
    for node in r_node.body:
        node_name = type(node).__name__
        print(node_name)
        # 如果是方法定义的节点
        if node_name == "FunctionDef":
            # 这儿要用深拷贝，不然node节点是相同的，方法参数修改时方法内的方法代码也会被修改
            func_dict = get_func_dict()
            item = {
                "imports": import_nodes,
                "func": copy.deepcopy(node)
            }
            func_dict[node.name] = item
            set_func_dict(func_dict)


        if node_name == "ClassDef":
            class_def_dict = get_class_def_dict()
            item = {
                "imports": import_nodes,
                "class": copy.deepcopy(node)
            }
            class_def_dict[node.name] = item
            set_class_def_dict(class_def_dict)


def get_components(code, save_path):
    # 首先遍历出当前源文件的方法字典
    r_node = ast.parse(code)
    # print(astunparse.dump(r_node))
    read_by_self(r_node)


if __name__ == '__main__':
    global_param_init()
    file_path = "../resource/envtest.py"
    save_path = "/Users/dailinfeng/Desktop/实验室项目/kubeflow/ast_convert/resource/res.py"
    with open(file_path) as f:
        code = f.read()
    get_components(code, save_path)
