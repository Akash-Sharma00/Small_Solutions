package com.example.smallsolutions;

public class ChatMessageLoader {

    String message, time,senderId, imageURL, profession, lastMessage, lastTime, userName, uid;

    public ChatMessageLoader() {
    }

    public ChatMessageLoader(String imageURL, String profession, String userName, String uid) {
        this.imageURL = imageURL;
        this.profession = profession;
        this.userName = userName;
        this.uid = uid;
    }


    public ChatMessageLoader(String message, String time, String senderId) {
        this.message = message;
        this.time = time;
        this.senderId = senderId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getProfession() {
        return profession;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getLastTime() {
        return lastTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getUid() {
        return uid;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public String getSenderId() {
        return senderId;
    }
}
