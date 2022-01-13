package com.beStrong.beStrong_server.messaging;

import com.beStrong.beStrong_server.model.Client;
import com.beStrong.beStrong_server.model.FitnessClass;
import com.beStrong.beStrong_server.model.Trainer;
import com.beStrong.beStrong_server.service.ClientService;
import com.beStrong.beStrong_server.service.FitnessClassService;
import com.beStrong.beStrong_server.service.TrainerService;

import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.Map;

@Service
public class Handler{

    @Autowired
    private FitnessClassService fitnessClassService;
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private ClientService clientService;

    @Async
    public String handle(Map<String, Object> message) {
        String ret;
        FitnessClass fitnessClass;
        Client client;
        Trainer trainer;

        switch(message.get("header").toString()) {
            case "NEW_FITNESS_CLASS":
                trainer =
                        this.trainerService.getTrainerById(Integer.parseInt(message.get("personal_trainer").toString()));

                if (trainer == null) {
                    ret = "Trainer " + message.get("personal_trainer").toString() + " not found";
                    break;
                }

                fitnessClass = new FitnessClass(
                        trainer,
                        message.getOrDefault("name", "Yoga").toString(),
                        Date.valueOf(message.get("date").toString()),
                        Time.valueOf(message.get("start").toString()),
                        Time.valueOf(message.get("end").toString()),
                        message.getOrDefault("local", "Anf. IV").toString(),
                        Integer.parseInt(message.getOrDefault("max_capacity", 30).toString())
                );
                fitnessClassService.saveFitnessClass(fitnessClass);
                ret = "New fitness class added " + fitnessClass;
                break;

            case "RESERVATION":
                ret = fitnessClassService.makeReservation(
                        Integer.parseInt(message.get("fitness_class").toString()),
                        Integer.parseInt(message.get("user_id").toString())
                );
                break;

            default:
                ret = "Unknown header " + message.get("header").toString();
        }
        return ret;
    };
}
