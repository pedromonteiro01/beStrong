from abc import ABC
import json


class GymPlan:
    def __init__(self, description, price):
        self.description = description
        self.price = price


class User(ABC):
    def __init__(self, user_id, name):
        self.user_id = user_id
        self.name = name


class Client(User):
    def __init__(self, user_id, name, height, weight):
        super().__init__(user_id, name)
        self.height = height
        self.weight = weight

    def enter_gym(self):
        print('ENTRY: ' + self.name + ' [' + str(self.user_id) + '] entered the gym')

    def exit_gym(self):
        print('EXIT: ' + self.name + ' [' + str(self.user_id) + '] left the gym')

    def __str__(self):
        return str(json.dumps(self.__dict__))


class PersonalTrainer(User):
    def __init__(self, user_id, name):
        super().__init__(user_id, name)

    def __str__(self):
        return str(json.dumps(self.__dict__))


class FitnessClass:
    def __init__(self, class_id, personal_trainer, name, max_capacity, date, start, end, current_size=0):
        self.class_id = class_id
        self.personal_trainer = personal_trainer
        self.name = name
        self.max_capacity = max_capacity
        self.current_size = current_size
        self.date = date
        self.start = start
        self.end = end

    def __str__(self):
        return str(json.dumps(self.__dict__, default=str))

