FROM python:3.9
ENV PATH /usr/local/bin:$PATH
ADD . /options_server
WORKDIR /options_server
RUN cd options_server
RUN pip3 install -r requirements.txt
RUN pip3 install aiofiles
RUN pip3 install python-multipart
CMD python3 run.py
