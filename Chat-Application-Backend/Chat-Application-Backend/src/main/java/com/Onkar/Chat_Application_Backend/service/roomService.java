package com.Onkar.Chat_Application_Backend.service;


import com.Onkar.Chat_Application_Backend.entities.Message;
import com.Onkar.Chat_Application_Backend.entities.Room;
import com.Onkar.Chat_Application_Backend.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class roomService {

    @Autowired
    private RoomRepository repo;

//    Get all room ID
    public ResponseEntity<?> getAllRooms() {
        List<Room> rooms = repo.findAll(); // Assuming Room is your entity
        List<String> roomIds = rooms.stream()
                .map(Room::getRoomId) // Extracting roomId
                .collect(Collectors.toList());
        return ResponseEntity.ok(roomIds);
    }

    public boolean checkRoomId(String roomId) {
        if (roomId == null) {
            return false;
        }
        List<Room> rooms = repo.findAll(); // Assuming Room is your entity
        List<String> roomIds = rooms.stream()
                .map(Room::getRoomId) // Extracting roomId
                .collect(Collectors.toList());
        if(roomIds.contains(roomId)){
            return true;
        }else{
            return false;
        }
    }

    public ResponseEntity<?> createRoom(String roomId) {
        Room room = new Room();
        room.setRoomId(roomId);
        Room saveRoom = repo.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }


    public ResponseEntity<?> getRoomById(String roomId) {
        System.out.println("Fetching room with ID: " + roomId);
        Room room = repo.findByRoomId(roomId);
        if (room == null) {
            return ResponseEntity.badRequest().body("Room Not Found..");
        }
        return ResponseEntity.ok(room);
    }



    public ResponseEntity<List<Message>> getMessages(String roomId) {
        Room room = repo.findByRoomId(roomId);
        if(room==null){
            return ResponseEntity.badRequest().build();
        }
        List<Message> messages = room.getMessage();
        return ResponseEntity.ok(messages);
    }



}
