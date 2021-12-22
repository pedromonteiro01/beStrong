import sys

import pika


class MQ:
    def __init__(self, queue_name):
        self.connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
        self.channel = self.connection.channel()
        self.channel.queue_declare(queue=queue_name)
        self.queue_name = queue_name

    def send_msg(self, msg):
        self.channel.basic_publish(exchange='',
                                   routing_key=self.queue_name,
                                   body=msg)
        print(" [x] Sent '" + str(msg) + "'")

    def close_channel(self):
        self.connection.close()


def main():
    while True:
        mq = MQ('beStrong')

        mq.send_msg("{\"header\":\"info\", \"body\":\"Hello Java Program ^^ !\"}")

        mq.close_channel()


if __name__ == '__main__':
    try:
        main()
    except KeyboardInterrupt:
        print("Closing generator...")
        sys.exit()

