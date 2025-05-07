package com.example.loginregister;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class HomeActivity extends Activity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // ⚡️ Verificar la sesión guardada
        prefs = getSharedPreferences("Sesion", MODE_PRIVATE);

        findViewById(R.id.buttonStartHome).setOnClickListener(v -> {
            if (prefs.getBoolean("sesionIniciada", false)) {
                // ✅ Si la sesión está activa, ir directamente al LobbyActivity
                startActivity(new Intent(this, LobbyActivity.class));
            } else {
                // 🔄 Si no hay sesión, ir a StartActivity
                startActivity(new Intent(this, StartActivity.class));
            }
            finish(); // Cierra HomeActivity para evitar que el usuario vuelva atrás
        });
    }
}
