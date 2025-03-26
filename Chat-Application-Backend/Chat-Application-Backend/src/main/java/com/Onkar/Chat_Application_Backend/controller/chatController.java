package com.Onkar.Chat_Application_Backend.controller;

import com.Onkar.Chat_Application_Backend.config.AppConstants;
import com.Onkar.Chat_Application_Backend.entities.Message;
import com.Onkar.Chat_Application_Backend.entities.Room;
import com.Onkar.Chat_Application_Backend.payload.MessageRequest;
import com.Onkar.Chat_Application_Backend.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(AppConstants.FRONT_END_URL)
public class chatController {

      @Autowired
      private RoomRepository repo;

//      API for sending and receving messahes
    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
     public Message sendMessage(
             @DestinationVariable String roomId,
             @RequestBody MessageRequest request
    ){
        Room room = repo.findByRoomId(request.getRoomId());

        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());

        if(room != null){
            room.getMessage().add(message);
            repo.save(room);
        }else{
            throw new RuntimeException("Room not found....");
        }
        return message;
     }
}
