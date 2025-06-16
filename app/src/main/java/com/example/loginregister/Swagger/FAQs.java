package com.example.loginregister.Swagger;

import com.google.gson.annotations.SerializedName;

public class FAQs {
    @SerializedName("question")
    private String question;

    @SerializedName("answer")
    private String answer;

    @SerializedName("sender")
    private String sender;

    public FAQs(String question, String answer, String sender) {
        this.question = question;
        this.answer = answer;
        this.sender = sender;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getSender() {
        return sender;
    }

}