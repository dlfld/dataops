import ast
import gc
import sys
from _ast import Return
from typing import Any

import astunparse
from astroid import Import, ImportFrom
import copy

from class_func_handle import ClassTransformer
from params_save_util import get_func_dict, get_class_def_dict, set_func_dict, set_class_def_dict


def reduction_params(old_params):
    """
        还原一个方法原来的参数
    :param old_params:  传入的是原来参数的参数名
    :return: 返回的是形成的参数还原代码的
    """
    code_str = ""
    for param in old_params:
        single_statement = f"{param} = joblib.load({param}_input.path)[\"{param}\"]\n"
        code_str += single_statement
    r_node = ast.parse(code_str)
    origin_params_node = []
    for item in r_node.body:
        class_name = item.__class__.__name__
        if class_name == "Assign":
            origin_params_node.append(item)
    return origin_params_node
    # print(astunparse.dump(r_node))


def handle_decorators(file_name, packages):
    """
        形成方法上面组件注解的代码
    :param file_name:
    :param packages:
    :return:
    """
    # print(packages)
    # @component(output_component_file='fun1min_component.yaml',packages_to_install=['lmfit', 'numpy', 'matplotlib', 'joblib', 'pandas', ])
    install_package = ""
    if packages is None:
        return None
    for index, package in enumerate(packages):
        if package is None:
            continue
        if index != len(packages) - 1:
            install_package += "'" + package + "',"
        else:
            install_package += "'" + package + "'"
    #         \ndef func():\n    pass 这一段要加才是一个不会编译出错的语法，才能够parse
    code = f"@component(output_component_file='{file_name}',packages_to_install=[{install_package}])\ndef func():\n    pass"
    # print(code)
    r_node = ast.parse(code)
    for item in r_node.body:
        class_name = item.__class__.__name__
        # print(class_name)
        # 找到装饰器的list，装饰器的节点是在FunctionDef里
        if class_name == "FunctionDef":
            return item.decorator_list
    # print(astunparse.dump(r_node))
    return None


def pre_ergodic(r_node):
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


"""
    处理方法代码
        ✅将需要引入的kubeflow SDK的import代码添加到文件上面
        ✅将import代码添加到方法定义语句的下一行,包括joblib包的添加
        ✅将方法参数修改为元数据形式并添加输出元数据
        ❓删除方法返回type  暂时没有找到方法返回类型声明对应的node节点  找到了 对象是returns  返回值是Return
        ✅还原参数变量
        ✅在方法定义的上面一行添加装饰器 
        ✅修改方法返回值 使用ast的方法暂时做不出来，因此打算使用字符串匹配的方式完成  因此还是存在限制
            🚫 所有的返回值都必须是变量而不能是其他的表达式或者是方法的调用
        ✅扫描当前方法调用了那些方法，并将这些方法添加到当前方法中
"""
def visit_Return( node: Return,cur_func_name):

    """
    扫描方法的返回值
    一个方法会先到这儿
    :param node:
    :return:
    """
    # 反向获取返回值的代码
    return_code = astunparse.unparse(node).replace("return", "")
    # 当返回值有多个时，ast会自动给返回的值转变为一个元组，因此需要删除元组的括号
    return_code = return_code.replace("(", "").replace(")", "").strip()
    # print(return_code)
    return_params = return_code.split(",")
    # return joblib.dump({"model":model},funcname_output.path)
    dict_str = ""
    for index, param in enumerate(return_params):
        # 主要是为了不报错
        param = param.replace("\"", "").replace("'", "").replace("\\", "").replace("\n", "").replace("=", "")
        if index < len(return_params) - 1:
            dict_str += f"\"{param}\":{param},"
        else:
            dict_str += f"\"{param}\":{param}"
    return_wrap_code = "return joblib.dump({" + dict_str + "}," + f"{cur_func_name}_output.path)"
    # print(return_wrap_code)
    try:
        r_node = ast.parse(return_wrap_code)
        return r_node.body[0]  # 返回新生成的node来替换原来的Return的node
    except Exception as e:
        print(e)
    return None
        #     如果出错就直接不返回
        # print(astunparse.dump(r_node))
        # return node  # 不要返回就表示遍历这个node之后不返回这个node  ----> 删除这个node

class CodeTransformer(ast.NodeTransformer):
    """
            对一个源文件中的方法进行检测，获取顶层方法所调用的方法。
            将被调用方法的方法体添加进去   
            
    """

    # 记录当前代码有几个方法定义，当扫描到方法的时候只对这几个方法定义代码的方法进行转换
    code_func_names = []
    # 存储导包节点，用于转移到下
    imports = set([])
    # 存储第三方包的名字，用于装饰器的添加
    imports_names = set(["joblib"])
    # 保存当前方法
    current_func = None
    # 存储当前方法的名字
    cur_func_name = ""
    # 保存当前方法调用的方法名
    call_func = set([])


    def visit_Import(self, node: Import):
        """
         扫描import xxx语句
        :param node:
        :return:
        """

        self.imports_names.add(node.names[0].name)
        self.imports.add(node)
        return node

    def visit_ImportFrom(self, node: ImportFrom):
        """
          扫描from xxx import 语句
        :param node:
        :return:
        """
        self.imports.add(node)
        self.imports_names.add(node.module)
        return node



    def visit_FunctionDef(self, node):
        # 当前方法的名字
        self.cur_func_name = node.name

        """
        扫描方法节点
        :param node:
        :return:
        """

        """
            替换形参名字
        """
        self.generic_visit(node)  # 这里表示先去访问里面的children node
        # 如果当前扫描到的方法不在当前顶层方法中 
        # 则表示当前扫到的方法是被方法调用的方法，因此不对方法节点进行处理
        if node.name not in self.code_func_names:
            return node

        func_dict = get_func_dict()
        # 之前在对方法扫描的过程中就已经node代码进行一定的修改了，因此将已经修改好的代码先加载过来。
        node = copy.deepcopy(func_dict[node.name]['func'])
 
        # 存储原来的参数,用作后面的参数还原
        old_args = []
        new_args = []
        # 遍历当前方法的所有形参，将每个形参改为元数据方式输入
        for arg in node.args.args:
            old_args.append(arg.arg)
            # 修改形参的定义语句，并在后面加上参数的type
            arg.arg = arg.arg + "_input:Input[Dataset]"

            # 设置参数后面的type为None
            arg.annotation = None
            new_args.append(arg)
        """
            给方法添加返回的元数据参数
        """
        output = ast.arg()  # 这是一个arg对象
        output.arg = node.name + "_output:Output[Dataset]"
        output.annotation = None
        new_args.append(output)

        # 设置参数的默认值
        node.args.defaults = []
        node.args.args = new_args  # 将新的参数赋值给参数对象

        """
            添加当前节点所调用的方法
            遍历
        """
        func_node_list = []  # 当前方法所调用方法的节点列表
        imports_list = []  # 保存当前方法调用方法所依赖的包
        """
            到此，当前方法使用了那些方法就明确了。保存在func_node_list之中，
        """
        # 方法原参数的加载
        """ 
                方法内代码的重新组合
                self.imports 导包代码
                func_node_list 当前方法所调用的方法的代码
                node.body  当前方法本身的代码
        """
        # 添加joblib代码的import
        joblib_module = ast.Import()
        joblib_alias = ast.alias()
        joblib_alias.name = "joblib"
        joblib_alias.asname = None
        joblib_module.names = [joblib_alias]
        # 添加joblib代码的import

        params = reduction_params(old_params=old_args)
        # 获取方法定义代码上组件装饰器代码
        decorator = handle_decorators(node.name + "_component.yaml", self.imports_names)
        node.decorator_list = decorator  # 设置装饰器
        # print(list(set(imports_list)))
        total_imports = list(set(list(self.imports) +imports_list))
        new_body = []
        # print(ast.dump(node))
        
        # 只对最外层的方法节点进行扫描，扫描到return节点之后直接进行转换
        for body_item in node.body:
            node_name = type(body_item).__name__
            inner_node = body_item
            # 单独处理return节点 
            if node_name  == "Return":
                return_node = visit_Return(body_item,self.cur_func_name)
                inner_node = copy.deepcopy(return_node)

            if inner_node is not None:
                new_body.append(inner_node)

        node.body =  total_imports + [joblib_module]  + params + new_body
        # 将当前方法调用的方法列表置为空
        self.call_func = set([])
        return node

    def visit_Call(self, node) -> Any:
        """
        获取方法调用的代码
        这个方法会在每一个方法定义代码扫描之前扫描
        :param node:
        :return:
        """
        self.generic_visit(node)

        if hasattr(node.func, "value"):
            # print("call_func_name value:" + node.func.value.id)
            pass
        elif hasattr(node.func, "id"):
            cal_name = node.func.id
            # print("call_func_name id:" + cal_name)
            self.call_func.add(cal_name)
        return node


def get_components(code, save_path):
    # 首先遍历出当前源文件的方法字典
    r_node = ast.parse(code)
    transformer = CodeTransformer()
    #  初始化参数，如果不初始化的话for中的参数会重复使用
    transformer.imports = set([])
    transformer.imports_names = set(["joblib"])
    transformer.current_func = None
    transformer.cur_func_name = ""
    transformer.call_func = set([])
    transformer.code_func_names = []
    # print(ast.dump(r_node))
    # 遍历当前文件的ast，将最外面的方法名获取到，后面对方法进行转换的时候就只转换这一些，其他的就不转换
    for node in r_node.body:
        node_name = type(node).__name__
        if node_name  == "FunctionDef":
            transformer.code_func_names.append(node.name)
    res = transformer.visit(r_node)

    source = astunparse.unparse(res)  # astunparse 一般python不自带，需要conda 或者 pip安装
    # 在文件顶部添加导包语句
    component_import = "import kfp\n" \
                       "from kfp.v2 import dsl\n" \
                       "from kfp.v2.dsl import component, Input, Output, OutputPath, Dataset, Model,InputPath\n" \
                       "import kfp.components as comp\n"
    source = component_import + source
    # print(source)
    with open(save_path, "w") as w:
        w.write(source)
        w.close()


if __name__ == '__main__':
    # file_path = sys.argv[1]
    # save_path = sys.argv[2]
    file_path = "/Users/dailinfeng/Desktop/实验室项目/kubeflow/ast_convert/resource/classtest.py"
    # file_path = "/Users/dailinfeng/Desktop/实验室项目/kubeflow/ast_convert/resource/envtest.py"

    save_path = "/Users/dailinfeng/Desktop/实验室项目/kubeflow/ast_convert/resource/res.py"
    with open(file_path) as f:
        code = f.read()
    get_components(code, save_path)

    # cm = compile(source, '<string>', 'exec')
    # exec(cm)


def transfer(file_path, save_path):
    with open(file_path) as f:
        code = f.read()
    get_components(code, save_path)
