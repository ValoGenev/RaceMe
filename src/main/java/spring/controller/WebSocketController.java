package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import spring.dto.location.UserLocationMessage;


@RestController
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void sendMessage(UserLocationMessage userLocationMessage){

        simpMessagingTemplate.setDefaultDestination("/topic/messages");

        System.out.println(userLocationMessage);

        simpMessagingTemplate.convertAndSend(userLocationMessage);
    }

}
