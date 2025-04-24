package com.example.loginregister.Swagger;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/TocaBolas/users/login")
    @FormUrlEncoded
    Call<LoginResponse> login(
            @retrofit2.http.Field("correo") String correo,
            @retrofit2.http.Field("password") String password
    );

    // Método corregido para el registro
    @POST("/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);
}
