package com.websocket.websocket.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }

    /*
    * So, here we set up @MessageMapping(“/send/message”), and once this URL will be triggered —
    *  we will simply send message to all clients subscribed to /chat subscription.
    * */

    @MessageMapping("/send/message")
    public void onReceiveMessage(String message){
        this.template.convertAndSend("/chat", new SimpleDateFormat("HH:mm:ss").format(new Date())+"- "+message);
    }
}
