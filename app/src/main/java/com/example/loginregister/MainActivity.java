package com.example.loginregister;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.loginregister.Swagger.AuthenticateResponse;
import com.example.loginregister.Swagger.LoginRequest;
import com.example.loginregister.Swagger.Swagger;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

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
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar la actividad RegisterActivity
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void clickLogin(View view){

        EditText usernameInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        LoginRequest loginRequest = new LoginRequest(username, password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Swagger swagger = retrofit.create(Swagger.class);

        Call<AuthenticateResponse> call = swagger.login(loginRequest);
        call.enqueue(new Callback<AuthenticateResponse>() {
            @Override
            public void onResponse(Call<AuthenticateResponse> call, A<AuthenticateResponse> response) {
                if (response.isSuccessful()) {
                    AuthenticateResponse loginResponse = response.body();
                    Toast.makeText(MainActivity.this, "Login correcto: " + loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    // aquí puedes redirigir al usuario a otra actividad
                } else {
                    Toast.makeText(MainActivity.this, "Login fallido", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthenticateResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

}