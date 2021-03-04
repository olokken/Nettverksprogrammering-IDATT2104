FROM python:3.9.0-alpine
WORKDIR /OVING5
<<<<<<< HEAD

ENV FLASK_APP=Oving5.py

ENV FLASK_RUN_HOST=0.0.0.0

RUN apk add --no-cache gcc musl-dev linux-headers

COPY requirements.txt requirements.txt

RUN pip3 install -r requirements.txt

EXPOSE 5000

COPY . .

CMD ["flask", "run"]

=======
ENV FLASK_APP=Oving5.py
ENV FLASK_RUN_HOST=0.0.0.0
RUN apk add --no-cache gcc musl-dev linux-headers
COPY requirements.txt requirements.txt
RUN pip3 install -r requirements.txt
EXPOSE 5000
COPY . .
CMD ["flask", "run"]
>>>>>>> a231efc813de10c744e213dc36568df6b3a35120
