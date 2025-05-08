package com.example.loginregister;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginregister.Swagger.API;
import com.example.loginregister.Swagger.AuthService;
import com.example.loginregister.Swagger.ChangePassRequest;
import com.example.loginregister.Swagger.GenericResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends AppCompatActivity {

    public String user;
    public String correo;
    public String token;
    EditText txtNewPassword;
    EditText txtActualPassword;
    Button enviarBtn;
    Button mostrarInputsBtn;

    TextView correoTxtView;

    TextView userTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        this.user = getIntent().getStringExtra("user");
        this.correo = getIntent().getStringExtra("correo");
        this.token = getIntent().getStringExtra("token");
        correoTxtView = findViewById(R.id.correoTxtView);
        userTxtView = findViewById(R.id.userTxtView);

        correoTxtView.setText(this.correo);
        userTxtView.setText(this.user);

        txtNewPassword = findViewById(R.id.txtNewPassword);
        txtActualPassword = findViewById(R.id.txtActualPassword);

        enviarBtn = findViewById(R.id.enviarBtn);

        // Están ocultos por XML, pero puedes reforzarlo aquí
        txtNewPassword.setVisibility(View.GONE);
        enviarBtn.setVisibility(View.GONE);

    }

    public void changePassClick(View view){
        txtNewPassword.setVisibility(View.VISIBLE);
        txtActualPassword.setVisibility(View.VISIBLE);
        enviarBtn.setVisibility(View.VISIBLE);

    }

    public void deleteAccountClick(View view){

    }

    public void enviarClick(View view){
        String nuevaPassword = txtNewPassword.getText().toString().trim();
        String actualPassword = txtActualPassword.getText().toString().trim();

        if (this.token == null || this.token.isEmpty()) {
            Toast.makeText(this, "Sesión no válida. Vuelve a iniciar sesión.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (actualPassword.isEmpty()) {
            Toast.makeText(this, "Introduce tu contraseña actual", Toast.LENGTH_SHORT).show();
            return;
        }

        if (nuevaPassword.isEmpty()) {
            Toast.makeText(this, "Introduce una nueva contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        if (nuevaPassword.equals(actualPassword)) {
            Toast.makeText(this, "La nueva contraseña no puede ser igual a la anterior", Toast.LENGTH_SHORT).show();
            return;
        }

        AuthService authService = API.getAuthService();
        Call<GenericResponse> call = authService.actualizarContrasena("Bearer " + this.token, actualPassword, nuevaPassword);

        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(PerfilActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    txtNewPassword.setText("");
                    txtActualPassword.setText("");
                } else {
                    Toast.makeText(PerfilActivity.this, "No se pudo cambiar la contraseña. Verifica tus datos.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Toast.makeText(PerfilActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
