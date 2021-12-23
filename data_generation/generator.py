import datetime
import json
import random
import sys
from random import choice
from time import sleep
from model import *
from queue import MQ
import mysql.connector

# Odds of random event happening (don't need to be normalized)
RANDOM_ENTRY = 0.3
RANDOM_EXIT = 0.3
RANDOM_CLASS_CREATION = 0.05
RANDOM_CLASS_RESERVATION = 0.15
RANDOM_CLASS_RESERVATION_CANCELLATION = 0.5


class Generator:
    def __init__(self):
        self.clients = []
        self.trainers = []
        self.fitness_class_names = []
        self.fitness_class_locations = []
        self.fitness_classes = []
        self.reservations = {}

        self.random_events = {
            self.client_random_entry: RANDOM_ENTRY,
            self.client_random_exit: RANDOM_EXIT,
            self.class_random_creation: RANDOM_CLASS_CREATION,
            self.client_class_reservation: RANDOM_CLASS_RESERVATION,
            self.client_cancel_class_reservation: RANDOM_CLASS_RESERVATION_CANCELLATION
        }

        self.setup_variables()
        self.occupants = random.choices(self.clients, k=random.randint(10, 30))

    def random_event(self):
        event = random.choices(list(self.random_events.keys()), weights=list(self.random_events.values()), k=1)[0]
        return event()

    def class_random_creation(self):
        trainer = choice(self.trainers)
        date, start, end = get_start_end_date()
        max_capacity = random.randint(20, 40)
        name = choice(self.fitness_class_names)
        class_id = max([c.class_id + 1 for c in self.fitness_classes], default=0)
        local = random.choice(self.fitness_class_locations)
        fitness_class = FitnessClass(
            class_id,
            trainer.user_id,
            name,
            max_capacity,
            date,
            start,
            end,
            local
        )
        print('NEW_FITNESS_CLASS: ' + str(fitness_class))
        self.fitness_classes.append(fitness_class)

        return {**fitness_class.__dict__, **{'header': 'NEW_FITNESS_CLASS'}}

    def client_random_entry(self):
        client = choice(self.clients)
        while client in self.occupants:
            client = choice(self.clients)
        self.occupants.append(client)
        client.enter_gym()

        # client enters with a friend
        if random.uniform(0, 1) < 0.1:
            self.client_random_entry()

        return {'header': 'ENTRY', 'user_id': client.user_id, 'time': datetime.datetime.now()}

    def client_random_exit(self):
        if len(self.occupants) <= 0:
            return
        client = self.occupants.pop(random.randint(0, len(self.occupants) - 1))
        client.exit_gym()
        return {'header': 'EXIT', 'user_id': client.user_id, 'time': datetime.datetime.now()}

    def client_class_reservation(self):
        # choose client
        if len(self.fitness_classes) == 0:
            return
        user_id = choice(self.clients).user_id
        if self.reservations.get(user_id) is None:
            self.reservations[user_id] = []
        # choose fitness class
        fitness_class = choice(self.fitness_classes)
        if fitness_class.current_size >= fitness_class.max_capacity:
            return
        self.reservations[user_id].append(fitness_class)
        fitness_class.current_size += 1
        print('RESERVATION: ' + '[' + str(user_id) + '] made a reservation for ' + str(fitness_class))
        return {'header': 'RESERVATION', 'user_id': user_id, 'fitness_class': fitness_class.class_id}

    def client_cancel_class_reservation(self):
        if len(self.reservations) <= 0:
            return
        user_id, reservations = random.choice(list(self.reservations.items()))
        cancellation = reservations.pop(random.randint(0, len(reservations) - 1))
        cancellation.current_size -= 1
        if len(reservations) == 0:
            del self.reservations[user_id]
        print("RESERVATION_CANCELLATION: [" + str(user_id) + "] cancelled their reservation for " + str(cancellation))
        return {'header': 'RESERVATION_CANCELLATION', 'user_id': user_id, 'fitness_class': cancellation.class_id}

    def setup_variables(self):
        try:
            connection = mysql.connector.connect(host='db',
                                                 port='3306',
                                                 database='ies-bestrong',
                                                 user='root',
                                                 password='password')

            sql_select_Query = "select * from clients"
            cursor = connection.cursor()
            cursor.execute(sql_select_Query)

            clients = cursor.fetchall()
            for uid, email, password, phone, username, height, weight, trainer_id in clients:
                client = Client(int(uid), username, float(height), float(weight))
                self.clients.append(client)

            sql_select_Query = "select * from trainers"
            cursor = connection.cursor()
            cursor.execute(sql_select_Query)

            trainers = cursor.fetchall()
            for uid, email, password, phone, username in trainers:
                trainer = PersonalTrainer(int(uid), username)
                self.trainers.append(trainer)

            sql_select_Query = "select * from fitnessclasses"
            cursor = connection.cursor()
            cursor.execute(sql_select_Query)

            fitness_classes = cursor.fetchall()
            for fc_id, capacity, date, end, local, start, name, trainer_id in fitness_classes:
                fitness_class = FitnessClass(fc_id, trainer_id, name, capacity, date, start, end, local)
                self.fitness_classes.append(fitness_class)

        except mysql.connector.Error as e:
            print("Error reading data from MySQL table", e)
        finally:
            if connection.is_connected():
                connection.close()
                cursor.close()
                print("MySQL connection is closed")


        with open('./static_data/fitness_classes_names.txt', 'r') as f:
            line = f.readline()
            while line:
                self.fitness_class_names.append(line[:-1])
                line = f.readline()

        with open('./static_data/fitness_classes_locations.txt', 'r') as f:
            line = f.readline()
            while line:
                self.fitness_class_locations.append(line[:-1])
                line = f.readline()


generator = Generator()


def get_start_end_date():
    day = random.randint(0, 7)
    start_hour = random.randint(8, 19)
    start_minutes = random.randint(0, 11) * 5
    duration = random.randint(6, 12) * 5
    end_minutes = (start_minutes + duration) % 60
    end_hour = start_hour + (start_minutes + duration) // 60
    start = datetime.datetime.today() + datetime.timedelta(days=day, hours=start_hour, minutes=start_minutes)
    end = datetime.datetime.today() + datetime.timedelta(days=day, hours=end_hour, minutes=end_minutes)
    return start.date(), start.time().strftime("%H:%M:%S"), end.time().strftime("%H:%M:%S")


def main():
    queue = MQ('beStrong')
    while True:
        sleep(random.randint(1, 7))
        message = generator.random_event()
        if message is None:
        	continue
        queue.send_msg(json.dumps(message, default=str))


if __name__ == '__main__':
    main()
