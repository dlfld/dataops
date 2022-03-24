import kfp
from kfp.v2 import dsl
from kfp.v2.dsl import component, Input, Output, OutputPath, Dataset, Model,InputPath
import kfp.components as comp


class Test():

    def add_func(self, aaa):
        aaa_ = (aaa + 1)
        return aaa_

@component(output_component_file='test_component.yaml', packages_to_install=['joblib'])
def test(ccc_input:Input[Dataset], test_output:Output[Dataset]):
    import joblib
    ccc = joblib.load(ccc_input.path)['ccc']
    from argon2 import Parameters
    import numpy as np
    import pandas as pd
    import requests

    class Test():

        def add_func(self, aaa):
            aaa_ = (aaa + 1)
            return aaa_
    test = Test()
    func = test.add_func(ccc)
    return joblib.dump({'func': func}, test_output.path)
