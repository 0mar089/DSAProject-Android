package com.example.loginregister.Clases;

public class ChangePassRequest {
    private String correo;
    private String passwordActual;
    private String nuevaPassword;

    public ChangePassRequest(String correo, String passwordActual, String nuevaPassword) {
        this.correo = correo;
        this.passwordActual = passwordActual;
        this.nuevaPassword = nuevaPassword;
    }
}
