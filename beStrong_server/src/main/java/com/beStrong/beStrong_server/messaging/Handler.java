package com.beStrong.beStrong_server.messaging;

import com.beStrong.beStrong_server.model.FitnessClass;
import com.beStrong.beStrong_server.model.Trainer;
import com.beStrong.beStrong_server.service.FitnessClassService;
import com.beStrong.beStrong_server.service.TrainerService;
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

    @Async
    public void handle(Map<String, Object> message) {

        if (!message.get("header").equals("NEW_FITNESS_CLASS"))
            return;

        System.out.println(this.trainerService);
        Trainer trainer =
                this.trainerService.getTrainerById(Integer.parseInt(message.get("personal_trainer").toString()));
        if (trainer == null)
            return;

        FitnessClass fitnessClass = new FitnessClass(
                trainer,
                message.getOrDefault("name", "Yoga").toString(),
                Date.valueOf(message.get("date").toString()),
                Time.valueOf(message.get("start").toString()),
                Time.valueOf(message.get("end").toString()),
                message.getOrDefault("local", "Anf. IV").toString(),
                Integer.parseInt(message.getOrDefault("max_capacity", 30).toString())
        );
        fitnessClassService.saveFitnessClass(fitnessClass);
    };
}
