package com.example.loginregister.Clases;

public class InsigniaResponse {
    String name;
    String avatar;

    public InsigniaResponse(){}

    public InsigniaResponse(String name, String avatar){
        this.name = name;
        this.avatar = avatar;
    }
    public String getName(){return this.name;}
    public String getAvatar(){return this.avatar;}
    public void setName(String name){this.name = name;}
    public void setAvatar(String avatar) {this.avatar = avatar;}
}
