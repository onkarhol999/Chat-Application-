package com.Onkar.Chat_Application_Backend.service;


import com.Onkar.Chat_Application_Backend.entities.Message;
import com.Onkar.Chat_Application_Backend.entities.Room;
import com.Onkar.Chat_Application_Backend.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class roomService {

    @Autowired
    private RoomRepository repo;

    public boolean checkRoomId(String roomId) {
        if (roomId == null) {
            return false;
        }
        Room room = repo.findRoomById(roomId);
        if (room == null) {
            return false;
        } else {
            String id = room.getRoomId();
            // Use .equals() for string comparison with null safety
            return roomId.equals(id);
        }
    }

    public ResponseEntity<?> createRoom(String roomId) {
        Room room = new Room();
        room.setRoomId(roomId);
        Room saveRoom = repo.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }


    public ResponseEntity<?> getRoomById(String roomId) {
        Room room = repo.findRoomById(roomId);
        if(room==null){
            return ResponseEntity.badRequest().body("Room Not Found..");
        }
        return ResponseEntity.ok(room);
    }

    public ResponseEntity<List<Message>> getMessages(String roomId) {
        Room room = repo.findRoomById(roomId);
        if(room==null){
            return ResponseEntity.badRequest().build();
        }
        List<Message> messages = room.getMessage();
        return ResponseEntity.ok(messages);
    }
}
