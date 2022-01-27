package com.beStrong.beStrong_server.messaging;

import com.beStrong.beStrong_server.repository.ClientRepository;
import com.beStrong.beStrong_server.repository.FitnessClassRepository;
import com.beStrong.beStrong_server.repository.TrainerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    private final CountDownLatch latch = new CountDownLatch(1);

    public static int num_entries = 35;

    @Autowired
    private FitnessClassRepository fitnessClassRepository;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private Handler handler;
    @Autowired
    private SimpMessagingTemplate template;

    public void receiveMessage(byte[] message) throws IOException {
        ObjectMapper om = new ObjectMapper();
        TypeReference<Map<String,Object>> tr = new TypeReference<Map<String,Object>> (){};
        Map<String,Object> m = om.readValue(message, tr);

        System.out.println("Received <" + m + ">");

        if(m.get("header").toString().equals("ENTRY")){
            num_entries += 1;
        }
        else if(m.get("header").toString().equals("EXIT")){
            num_entries -=1;
        }

        if (num_entries<0)
            num_entries = 0;

        this.template.convertAndSend("/entry/num", num_entries);

        this.handler.handle(m);

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}