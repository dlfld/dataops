from aop.data_func import func_config


@func_config(
    data=dict({
        "optUrl": "/cnn_twitter_data_init",
        "optDesc": "cnn对twitter数据进行分类时，对数据的初始化工作",  # 模块功能简介
        "optName": "cnn_初始化数据",  # 展示在前端的模块名
        "desc": "start desc",  # 需要的数据的desc  这个desc不能够删除的，并且不能够相同因为这是调度端进行数据更新的东西  但是并不是寻找数据的标志符了，后面可能用来当作模块数据接口规范
        "return_desc": "after cnn init twitter"  # 经过处理之后的desc
    })
)
def setup_word_dict(data):
    data_dict = data[0]
    x = data_dict["x"]
    # 建立词典 key为单词 value为出现次数
    voc_dic = {}
    for sentence in x:
        words = sentence.split()
        for word in words:
            if word in voc_dic:
                voc_dic[word] += 1
            else:
                voc_dic[word] = 1

    # 构建词表
    vocab = ["<pad>", "<unk>"]

    # 词频大于1的才加入词表
    for k, v in voc_dic.items():
        if v > 1:
            vocab.append(k)
    return vocab