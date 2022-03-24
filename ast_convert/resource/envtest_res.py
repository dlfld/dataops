import kfp
from kfp.v2 import dsl
from kfp.v2.dsl import component, Input, Output, OutputPath, Dataset, Model,InputPath
import kfp.components as comp

import requests
import numpy as np
import pandas as pd
from argon2 import Parameters

class Test():

    def add_func(self, aaa):
        aaa_ = (aaa + 1)
        return aaa_

@component(output_component_file='load_data_component.yaml', packages_to_install=['argon2', 'joblib', 'pandas', 'requests', 'numpy'])
def load_data(url_input:Input[Dataset], filename_input:Input[Dataset], url1_input:Input[Dataset], load_data_output:Output[Dataset]):
    import numpy as np
    from argon2 import Parameters
    import pandas as pd
    import requests
    import joblib
    url = joblib.load(url_input.path)['url']
    filename = joblib.load(filename_input.path)['filename']
    url1 = joblib.load(url1_input.path)['url1']
    from argon2 import Parameters
    import numpy as np
    import pandas as pd
    import requests

    class Test():

        def add_func(self, aaa):
            aaa_ = (aaa + 1)
            return aaa_
    f = requests.get(url)
    with open(filename, 'wb') as code:
        code.write(f.content)
    a = pd.read_excel(filename)
    xdata = a[['t', 'X_t_1', 'X_t_2', 't_T_1_X_t_1', 't_T_2_X_t_2', 'H_t', 'ck1', 'sk1', 'ck2', 'sk2']]
    ydata = np.log(a['Y'])
    Test()
    return joblib.dump({'xdata': xdata, ' ydata': ydata}, load_data_output.path)

@component(output_component_file='get_model1_component.yaml', packages_to_install=['argon2', 'joblib', 'pandas', 'requests', 'numpy'])
def get_model1(xdata_input:Input[Dataset], ydata_input:Input[Dataset], get_model1_output:Output[Dataset]):
    import numpy as np
    from argon2 import Parameters
    import pandas as pd
    import requests
    import joblib
    xdata = joblib.load(xdata_input.path)['xdata']
    ydata = joblib.load(ydata_input.path)['ydata']
    from argon2 import Parameters
    import numpy as np
    import pandas as pd
    import requests

    def load_data(url, filename: str, url1: int=10):
        from argon2 import Parameters
        import numpy as np
        import pandas as pd
        import requests

        class Test():

            def add_func(self, aaa):
                aaa_ = (aaa + 1)
                return aaa_
        f = requests.get(url)
        with open(filename, 'wb') as code:
            code.write(f.content)
        a = pd.read_excel(filename)
        xdata = a[['t', 'X_t_1', 'X_t_2', 't_T_1_X_t_1', 't_T_2_X_t_2', 'H_t', 'ck1', 'sk1', 'ck2', 'sk2']]
        ydata = np.log(a['Y'])
        Test()
        return (xdata, ydata)
    params = Parameters()
    params.add('beta0', value=1.0)
    params.add('beta1', value=1.0)
    params.add('beta6', value=1.0)
    params.add('ck1', value=1.0)
    params.add('sk1', value=1.0)
    params.add('ck2', value=1.0)
    params.add('sk2', value=1.0)
    load_data()
    data = load_data()
    model = minimize(load_data(), params, args=(xdata.values, ydata.values))
    return joblib.dump({'model': model}, get_model1_output.path)
