package com.Onkar.Chat_Application_Backend.payload;


import java.time.LocalDateTime;

public class MessageRequest {

    private String content;
    private String sennder;
    private String roomId;


    public MessageRequest(String content, String sennder, String roomId) {
        this.content = content;
        this.sennder = sennder;
        this.roomId = roomId;
    }
    public MessageRequest() {
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSennder() {
        return sennder;
    }

    public void setSennder(String sennder) {
        this.sennder = sennder;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
