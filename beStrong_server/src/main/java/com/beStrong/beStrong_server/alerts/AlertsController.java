package com.beStrong.beStrong_server.alerts;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.beStrong.beStrong_server.model.ChatMessage;


@CrossOrigin
@Controller
public class AlertsController {

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        System.out.println("server: "+ chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println("message: "+chatMessage);
        return chatMessage;
    }

    @MessageMapping("/entries")
    @SendTo("/entry/num")
    public int send(@Payload int entry){
        System.out.println("entries: "+ entry);
        //this.simpMessagingTemplate.convertAndSend("/entry/num", entry);
        return entry;
    }
}