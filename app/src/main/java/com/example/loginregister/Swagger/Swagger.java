package com.example.loginregister.Swagger;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Swagger {
    @POST("/dsaApp/login")
    Call<AuthenticateResponse> login(@Body LoginRequest loginRequest);
}
