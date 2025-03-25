package com.Onkar.Chat_Application_Backend.controller;

import com.Onkar.Chat_Application_Backend.entities.Message;
import com.Onkar.Chat_Application_Backend.service.roomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:5173")
public class roomController {
    @Autowired
    private roomService service;

    //Create Room


    @PostMapping("/createRoomById")
    public ResponseEntity<?> createRoomById(@RequestBody Map<String, String> requestBody) {
        String roomId = requestBody.get("roomId"); // Extract actual value

        if (roomId == null || roomId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid Room ID.");
        }

        boolean isPresent = service.checkRoomId(roomId);
        if (isPresent) {
            return ResponseEntity.badRequest().body("Room Already Exists.");
        } else {
            return service.createRoom(roomId);
        }
    }


    //join room for that get room API
    @GetMapping("/getRoomById/{roomId}")
    public ResponseEntity<?> getRoomById(@PathVariable("roomId") String roomId){
        return service.getRoomById(roomId);
    }


    //    Get Messages
    @GetMapping("/message/{roomId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String roomId){
        return service.getMessages(roomId);
    }

// Get all rooms id
     @GetMapping("/getAllRooms")
    public ResponseEntity<?> getAllRooms(){
        return service.getAllRooms();
     }
}
