package com.beStrong.beStrong_server.messaging;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.beStrong.beStrong_server.model.FitnessClass;
import com.beStrong.beStrong_server.model.Trainer;
import com.beStrong.beStrong_server.repository.ClientRepository;
import com.beStrong.beStrong_server.repository.FitnessClassRepository;
import com.beStrong.beStrong_server.repository.TrainerRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private final CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private FitnessClassRepository fitnessClassRepository;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private ClientRepository clientRepository;

    public void receiveMessage(byte[] message) throws IOException {
        ObjectMapper om = new ObjectMapper();
        TypeReference<Map<String,Object>> tr = new TypeReference<Map<String,Object>> (){};
        Map<String,Object> m = om.readValue(message, tr);

        System.out.println("Received <" + m + ">");

        if (m.get("header").equals("NEW_FITNESS_CLASS")) {
            System.out.println(m.get("start").toString());
            FitnessClass fitnessClass = new FitnessClass(
                    trainerRepository.findById(Long.valueOf(m.get("personal_trainer").toString())).orElse(new Trainer()),
                    m.getOrDefault("name", "Yoga").toString(),
                    // m.getOrDefault("max_capacity", 30),
                    Date.valueOf(m.get("date").toString()),
                    Time.valueOf(m.get("start").toString()),
                    Time.valueOf(m.get("end").toString()),
                    m.getOrDefault("local", "Anf. IV").toString()
            );
            fitnessClassRepository.save(fitnessClass);
            // trainer.setClass(fitnessClass);
        }
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}