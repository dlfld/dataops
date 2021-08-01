FROM python:3.9
ENV PATH /usr/local/bin:$PATH
ADD . /options_server
WORKDIR /options_server
RUN pip3 install -r requirements.txt --index=http://10.131.18.162:8889/simple/ --trusted-host 10.131.18.162
CMD python3 run.py
