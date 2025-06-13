package com.example.loginregister.Swagger;

import com.google.gson.annotations.SerializedName;

public class FAQs {
    @SerializedName("pregunta")
    private String question;

    @SerializedName("respuesta")
    private String answer;

    public FAQs(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}