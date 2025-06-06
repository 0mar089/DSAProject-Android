package com.example.loginregister.Swagger;

public class QuestionResponse {

    private boolean success;
    private String message;

    public QuestionResponse(boolean success, boolean message) {
        // Constructor vacío necesario para deserialización
        this.success = success;
        this.message = String.valueOf(message);
    }

    public QuestionResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
