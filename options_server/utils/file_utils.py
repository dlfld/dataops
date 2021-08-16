import os

'''
判断文件是否存在，如果不存在就创建
'''


def judge_file_path(path):
    if os.path.exists(path):
        return
    os.makedirs(path)

