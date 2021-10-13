import jieba
import joblib
from sklearn.feature_extraction.text import CountVectorizer, TfidfTransformer

from aop.data_func import func_config
from utils.file_utils import FileReaders, FileWriters

stopword_path = 'static/stopwords.txt'


def load_stopwords(file_path):
    """
    加载停用词
    :param file_path:
    :return:
    """

    stop_words = []
    with open(file_path, encoding='UTF-8') as words:
        stop_words.extend([i.strip() for i in words.readlines()])
    return stop_words


def review_to_text(review):
    """
    进行分词并去掉停用词  返回去掉停用词的分词
    :param review: 句子
    :return:
    """
    stop_words = load_stopwords(stopword_path)
    review = jieba.cut(review)
    all_stop_words = set(stop_words)
    # 去掉停用词
    review_words = [w for w in review if w not in all_stop_words]
    return review_words


def transform_pre_handle(items):
    data = []
    for item in items:
        file_full_path = item['location']
        item_data = FileReaders.read_params(file_full_path)
        data.append(item_data)
    return data[0]


@func_config(
    data=dict({
        "optUrl": "/transform_news_data",
        "optDesc": "对预处理数据尽行transform",  # 模块功能简介
        "optName": "对预处理数据尽行transform",  # 展示在前端的模块名
        "desc": "start desc",  # 需要的数据的desc  这个desc不能够删除的，并且不能够相同因为这是调度端进行数据更新的东西  但是并不是寻找数据的标志符了，后面可能用来当作模块数据接口规范
        "return_desc": "after transform_news_data"  # 经过处理之后的desc
    }),
    pre_handle_adapter=transform_pre_handle,  # 在进入计算节点之前进行数据处理（读取数据文件+格式转换）
    after_handle_adapter=FileWriters.save_params)  # 在计算节点计算完成之后进行数据处理（写入数据文件+格式转换）
def transform_news_data(data_list):
    """
    预处理输入模型训练的数据
    :param data_list:
    :return:
    """
    contents = [x[3] for x in data_list]
    Y = [x[2] for x in data_list]
    X = [' '.join(review_to_text(review)) for review in contents]

    n = len(data_list) // 5
    X_train, Y_train = X[n:], Y[n:]
    X_test, Y_test = X[:n], Y[:n]

    vectorizer = CountVectorizer(max_df=0.8, min_df=3)
    tfidftransformer = TfidfTransformer()
    # 先转换成词频矩阵，再计算TFIDF值
    X_train_tfidf = tfidftransformer.fit_transform(vectorizer.fit_transform(X_train))
    X_test_tfidf = tfidftransformer.transform(vectorizer.transform(X_test))
    return_dict = {
        "X_train_tfidf": X_train_tfidf,
        "Y_train": Y_train,
        "X_test_tfidf": X_test_tfidf,
        "Y_test": Y_test
    }
    return return_dict
