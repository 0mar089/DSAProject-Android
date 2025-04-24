package com.example.loginregister;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginregister.Swagger.API;
import com.example.loginregister.Swagger.LoginResponse;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.loginregister.Swagger.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencia al TextView de Sign up
        TextView signUpText = findViewById(R.id.signUp);

        // Configurar el evento onClick para el TextView
        signUpText.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Referencia al botón de iniciar sesión
        Button loginButton = findViewById(R.id.loginButton);

        // Configurar el evento onClick para el botón de iniciar sesión
//        loginButton.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, ShopActivity.class);
//            startActivity(intent);
//        });

        emailEditText = findViewById(R.id.txtEmail);
        passwordEditText = findViewById(R.id.txtPassword);
    }


    public void clickLogin(View view) {
        String correo = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Hacer la llamada a la API para realizar el login
        loginUser(correo, password);
    }

    private void loginUser(String correo, String password) {
        // Llamada Retrofit para hacer el login
        API.getAuthService().login(correo, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null && loginResponse.isStatus()) {
                        // Login exitoso, muestra un mensaje
                        Toast.makeText(MainActivity.this, "Bienvenido, " + loginResponse.getUser(), Toast.LENGTH_LONG).show();
                    } else {
                        // Login fallido, muestra el mensaje de error
                        Toast.makeText(MainActivity.this, "Error: " + loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Si la respuesta no es exitosa
                    Toast.makeText(MainActivity.this, "Login fallido", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Si hay un error en la llamada, muestra el error
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}