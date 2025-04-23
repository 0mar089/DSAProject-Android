package com.example.loginregister.Swagger;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/TocaBolas/users/login")
    @FormUrlEncoded
    Call<LoginResponse> login(
            @Field("correo") String correo,
            @Field("password") String password
    );

}
