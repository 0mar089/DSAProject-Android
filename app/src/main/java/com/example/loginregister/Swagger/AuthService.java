package com.example.loginregister.Swagger;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
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
    @FormUrlEncoded
    @POST("/TocaBolas/users/register")
    Call<RegisterResponse> register(
            @Field("username") String username,
            @Field("correo") String correo,
            @Field("password") String password
    );
}
