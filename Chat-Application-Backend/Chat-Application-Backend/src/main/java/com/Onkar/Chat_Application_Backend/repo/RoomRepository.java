package com.Onkar.Chat_Application_Backend.repo;

import com.Onkar.Chat_Application_Backend.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
    Room findByRoomId(String roomId); // Corrected query method
}

