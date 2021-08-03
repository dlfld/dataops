import pandas as pd
a = [['1', '2', '3'], [1, 2, 3], [4, 5, 6]]
n = a.pop(0)
print(n)
print(a)
df = pd.DataFrame(a, columns=n)
print(df.columns.values)
print(df.values)
df = pd.DataFrame(df.values,columns=df.columns.values,dtype=float)
print(df)