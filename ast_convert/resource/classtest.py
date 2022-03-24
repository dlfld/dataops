class Test:
    def add_func(self, aaa):
        aaa_ = aaa + 1
        return aaa_


def test(ccc):
    test = Test()
    func = test.add_func(ccc)
    return func
