package com.beStrong.beStrong_server.alerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.beStrong.beStrong_server.messaging.Receiver;


@CrossOrigin
@Controller
public class AlertsController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private Receiver rec;

    @MessageMapping("/entries")
    @SendTo("/entry/num")
    public int send(@Payload int entry){
        return entry;
    }
}