package com.example.loginregister;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.buttonStartHome).setOnClickListener(v -> {
            // Inicia SplashScreenActivity y cierra HomeActivity para evitar volver atrás
            Intent intent = new Intent(this, SplashScreenActivity.class);
            startActivity(intent);
            finish(); // Evita que el usuario regrese con el botón "Atrás"
        });

    }

//    public void startClick(View view) {
//        Intent intent = new Intent(this, SplashScreenActivity.class);
//        startActivity(intent);
//        finish(); // Esto evita que el usuario pueda volver atrás
//    }

}
