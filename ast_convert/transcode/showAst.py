import ast

from graphviz import Digraph


def visit(node, nodes, pindex, g):
    name = str(type(node).__name__)
    index = len(nodes)
    nodes.append(index)
    g.node(str(index), name)
    if index != pindex:
        g.edge(str(index), str(pindex))
    for n in ast.iter_child_nodes(node):
        visit(n, nodes, index, g)
if __name__ == '__main__':
    with open("../resource/envtest.py") as f:
        code = f.read()
    # 首先遍历出当前源文件的方法字典
    # r_node = ast.parse(code)
    graph = Digraph(format="png")
    tree = ast.parse(code)
    visit(tree, [], 0, graph)
    graph.render("test")