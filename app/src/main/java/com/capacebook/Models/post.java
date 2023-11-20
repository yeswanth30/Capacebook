package com.capacebook.Models;

public class post {
    int id;
    String postid;
    String userid;
    byte[] photovideo;
    String text;
    String created_at;


    public post(int id,byte[] image,String text,String postid) {
        this.id = id;
        this.photovideo = image;
        this.text = text;
        this.postid = postid;
    }
    public post(int id, byte[] image, String text){
        this.id = id;
        this.photovideo = image;
        this.text = text;
    }
    public post(String text) {

        this.text = text;
    }
    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public byte[] getPhotovideo() {
        return photovideo;
    }

    public void setPhotovideo(byte[] photovideo) {
        this.photovideo = photovideo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
