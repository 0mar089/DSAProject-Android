package com.example.loginregister;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginregister.Swagger.API;
import com.example.loginregister.Swagger.RegisterRequest;
import com.example.loginregister.Swagger.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends Activity {

    private EditText usernameInput, txtEmail, txtPassword, confirmPasswordInput;
    private CheckBox termsCheckbox;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Referencias a los elementos de la interfaz gráfica
        usernameInput = findViewById(R.id.usernameInput);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        termsCheckbox = findViewById(R.id.termsCheckbox);
        createAccountButton = findViewById(R.id.createAccountButton);

        // Configuración del botón de registro
        createAccountButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            String email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();
            String confirmPassword = confirmPasswordInput.getText().toString();

            // Validación de los datos ingresados
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!termsCheckbox.isChecked()) {
                Toast.makeText(RegisterActivity.this, "Debes aceptar los términos y condiciones.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Llamar al método para registrar al usuario
            registerUser(username, email, password);
        });
    }

    private void registerUser(String username, String email, String password) {
        API.getAuthService().register(new RegisterRequest(username, email, password)).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse.isStatus()) {
                        Toast.makeText(RegisterActivity.this, "Registro exitoso: " + registerResponse.getMessage(), Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error: " + registerResponse.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Error en el registro.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
