import requests
import numpy as np
import pandas as pd
from argon2 import Parameters


class Test:
    def add_func(self, aaa):
        aaa_ = aaa + 1
        return aaa_


# 方法的定义语句，定义方法声明参数，参数可以是变量名
def load_data(url, filename: str, url1: int = 10):
    f = requests.get(url)
    with open(filename, 'wb') as code:
        code.write(f.content)
    a = pd.read_excel(filename)
    xdata = a[['t', 'X_t_1', 'X_t_2', 't_T_1_X_t_1', 't_T_2_X_t_2', 'H_t', 'ck1', 'sk1', 'ck2', 'sk2']]
    ydata = np.log(a["Y"])
    Test()
    #  方法的返回结果，只能够是返回一个或多个变量的形式
    return xdata, ydata


def get_model1(xdata, ydata):
    params = Parameters()
    params.add('beta0', value=1.)
    params.add('beta1', value=1.)
    params.add('beta6', value=1.)
    params.add('ck1', value=1.)
    params.add('sk1', value=1.)
    params.add('ck2', value=1.)
    params.add('sk2', value=1.)
    load_data()
    data = load_data()
    model = minimize(load_data(), params, args=(xdata.values, ydata.values))
    # return joblib.dump({"model":model},output_path.path)
    return model
