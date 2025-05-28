package com.example.loginregister.Swagger;

public class RankingResponse {
    String username;
    int record;


    public String getUsername(){
        return this.username;
    }
    public int getRecord(){
        return this.record;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setRecord(int record){
        this.record = record;
    }
}
