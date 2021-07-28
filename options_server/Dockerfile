FROM python:3.9
ENV PATH /usr/local/bin:$PATH
ADD . /options_server
WORKDIR /options_server
RUN pip install -r requirements.txt
CMD python3 run.py
