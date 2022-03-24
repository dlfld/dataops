import ast
import copy
import os
from platform import node
import astunparse
from class_func_handle import ClassTransformer
from func_call_handle import FuncCallTransformer
from generate_ast import transfer, CodeTransformer, pre_ergodic
from params_save_util import global_param_init, get_class_def_dict, set_class_def_dict, get_func_dict, \
    set_func_dict

import sys
sys.setrecursionlimit(6000)

def pre_search_py_file(path):
    """
            读取当前项目中所有的类和所有的方法，把这些类和方法分别保存，以供后用
    :param path:
    :return:
    """
    # 首先遍历当前目录所有文件及文件夹
    file_list = os.listdir(path)
    # 准备循环判断每个元素是否是文件夹还是文件，是文件的话，把名称传入list，是文件夹的话，递归
    for file in file_list:
        # 利用os.path.join()方法取得路径全名，并存入cur_path变量，否则每次只能遍历一层目录
        cur_path = os.path.join(path, file)
        # 判断是否是文件夹
        if os.path.isdir(cur_path):
            pre_search_py_file(cur_path)
        elif file.endswith(".py") and not file.endswith("_res.py"):
            with open(cur_path) as f:
                code = f.read()
            r_node = ast.parse(code)
            print("读取文件" + cur_path)
            # 这个方法的作用是读取当前项目中所有的类和所有的方法，并将他们保存到一个具体的地方
            pre_ergodic(r_node)

            # print('转换了文件' + save_path)


def search_py_file(path):
    # 首先遍历当前目录所有文件及文件夹
    file_list = os.listdir(path)
    # 准备循环判断每个元素是否是文件夹还是文件，是文件的话，把名称传入list，是文件夹的话，递归
    for file in file_list:
        # 利用os.path.join()方法取得路径全名，并存入cur_path变量，否则每次只能遍历一层目录
        cur_path = os.path.join(path, file)
        # 判断是否是文件夹
        if os.path.isdir(cur_path):
            search_py_file(cur_path)
        elif file.endswith(".py") and not file.endswith("_res.py"):
            # 如果是py文件就对其进行处理
            save_path = os.path.join(path, file.replace(".py", "_res.py"))
            print('转换文件' + cur_path)
            # transfer(cur_path, save_path)
            try:
                transfer(cur_path, save_path)
            except Exception as e:
                print(e)


if __name__ == '__main__':
    global_param_init()
    # file_path = "/Users/dailinfeng/Desktop/实验室项目/kubeflow/ast_convert/resource/inittest"
    # file_path = "/Users/dailinfeng/Desktop/小项目/auto-sklearn"
    # file_path = "/Users/dailinfeng/Desktop/小项目/scikit-learn"
    file_path = "/home/dlf/testCode/scikit-learn"
    # file_path = "/home/dlf/testCode/ast_convert/resource"
    # 传入空的list接收文件名，获取当前项目所有的fun和class
    pre_search_py_file(file_path)
    print("pre_search_py_file 结束")
    # 遍历类的节点map 对类的每一个方法进行处理，具体处理内容就是将类中方法所依赖的方法引进来并将其依赖的第三方包引入
    class_def_dict = get_class_def_dict()
    
    for node in class_def_dict.keys():
        transformer = ClassTransformer()
        visit = transformer.visit(class_def_dict[node]['class'])
        class_def_dict[node]['class'] = visit
    print("class 引用结束")
    # 扫描每一个方法，将每一个方法所调用的方法都获取到并添加到方法节点中
    new_func_dict = dict({})
    func_dict = get_func_dict()
    funcCallTransformer = FuncCallTransformer()
    for node in func_dict.keys():
        # 重制参数 ，如果不重制的话会出问题  另一个没有调用A方法的方法会被判定为调用了A方法，将A方法引入
        funcCallTransformer.call_func = set([])
        # 设置当前方法的名字
        funcCallTransformer.cur_func_name = ""
        funcCallTransformer.current_func = None
        # 开始对参数进行转换 
        try:
            transformer_visit = funcCallTransformer.visit(func_dict[node]['func'])
            # 将参数转换之后村
            func_dict[node]['func'] = transformer_visit
            source = astunparse.unparse(transformer_visit) 
            print("转换成功")
        except Exception as e:
            print(e)
        # print(source)
    print("方法扫描结束")
    set_func_dict(func_dict)
    set_class_def_dict(class_def_dict)
    # 循环打印show_files函数返回的文件名列表
    search_py_file(file_path)
