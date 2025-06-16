package com.example.loginregister.Clases;

public class QuestionRequest {

    private  String title;
    private  String message;
    private  String sender;

    public QuestionRequest( String title, String message, String sender) {
        // Constructor vacío necesario para deserialización

        this.title = title;
        this.message = message;
        this.sender = sender;
    }







    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


}
