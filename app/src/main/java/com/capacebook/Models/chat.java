package com.capacebook.Models;

public class chat {

    String chatid;
    String senderid;
    String recieverid;
    String message;
    String time;

    public chat(String chatss) {
        this.senderid = chatss;

    }

    public chat(String chatss, String recieverid) {
        this.senderid = chatss;
        this.recieverid = recieverid;
    }

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getRecieverid() {
        return recieverid;
    }

    public void setRecieverid(String recieverid) {
        this.recieverid = recieverid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
