FROM python:3.8

ADD static_data /data/app
COPY . /data/app
WORKDIR /data/app
RUN pip install -r requirements.txt

CMD ["python3","generator.py"]
