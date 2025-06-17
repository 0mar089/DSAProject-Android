package com.example.loginregister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginregister.Clases.API;
import com.example.loginregister.Clases.AuthService;
import com.example.loginregister.Clases.GenericResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends AppCompatActivity {

    public String user;
    public String correo;
    public String token;
    public String money;
    public String record;
    EditText txtNewPassword, txtActualPassword, txtPasswordDelete;
    Button enviarBtn;
    Button enviarBtn2;

    TextView correoTxtView;

    TextView userTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        this.user = getIntent().getStringExtra("user");
        this.correo = getIntent().getStringExtra("email");
        this.token = getIntent().getStringExtra("token");
        this.money = getIntent().getStringExtra("money");
        this.record = getIntent().getStringExtra("record");
        correoTxtView = findViewById(R.id.correoTxtView);
        userTxtView = findViewById(R.id.userTxtView);

        correoTxtView.setText("Email: " + this.correo);
        userTxtView.setText("User: " + this.user);

        txtNewPassword = findViewById(R.id.txtNewPassword);
        txtActualPassword = findViewById(R.id.txtActualPassword);
        txtPasswordDelete = findViewById(R.id.txtPasswordDelete);

        enviarBtn = findViewById(R.id.enviarBtn);
        enviarBtn2 = findViewById(R.id.enviarBtn2);

        // Están ocultos por XML, pero puedes reforzarlo aquí
        txtNewPassword.setVisibility(View.GONE);
        enviarBtn.setVisibility(View.GONE);

    }

    public void changePassClick(View view){
        txtNewPassword.setVisibility(View.VISIBLE);
        txtActualPassword.setVisibility(View.VISIBLE);
        enviarBtn.setVisibility(View.VISIBLE);
        txtPasswordDelete.setVisibility(View.GONE);
        enviarBtn2.setVisibility(View.GONE);
    }

    public void deleteAccountClick(View view){
        txtNewPassword.setVisibility(View.GONE);
        txtActualPassword.setVisibility(View.GONE);
        enviarBtn.setVisibility(View.GONE);
        txtPasswordDelete.setVisibility(View.VISIBLE);
        enviarBtn2.setVisibility(View.VISIBLE);
    }

    public void enviarChangeClick(View view){
        String nuevaPassword = txtNewPassword.getText().toString().trim();
        String actualPassword = txtActualPassword.getText().toString().trim();

        if (this.token == null || this.token.isEmpty()) {
            Toast.makeText(this, "Login not valid. Please try again.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (actualPassword.isEmpty()) {
            Toast.makeText(this, "Introduce your current password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (nuevaPassword.isEmpty()) {
            Toast.makeText(this, "Introduce a new password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (nuevaPassword.equals(actualPassword)) {
            Toast.makeText(this, "The new password cannot be the same as the old one.", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(PerfilActivity.this, "Password couldn't be changed.. Verify your data.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Toast.makeText(PerfilActivity.this, "Connection error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void enviarDeleteClick(View view){
        String actualPassword = txtPasswordDelete.getText().toString();

        if (this.token == null || this.token.isEmpty() || actualPassword.isEmpty()) {
            Toast.makeText(this, "Introduce your current password", Toast.LENGTH_SHORT).show();
            return;
        }

        AuthService authService = API.getAuthService();
        Call<GenericResponse> call = authService.eliminarUsuario("Bearer " + this.token, actualPassword);

        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                    Toast.makeText(PerfilActivity.this, "Account deleted succesfully", Toast.LENGTH_LONG).show();
                    // Limpiar sesión
                    SharedPreferences.Editor editor = getSharedPreferences("Session", MODE_PRIVATE).edit();
                    editor.clear();
                    editor.apply();
                    // Volver al inicio
                    Intent intent = new Intent(PerfilActivity.this, StartActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    String mensaje = response.body() != null ? response.body().getMessage() : "Error eliminating account";
                    Toast.makeText(PerfilActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Toast.makeText(PerfilActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onClickBack(View view) {
        finish();
        overridePendingTransition(R.anim.slide_in_left);
    }

    public void onClickInventario(View view){
        Intent intent = new Intent(PerfilActivity.this, InventarioActivity.class);
        intent.putExtra("user", this.user);
        intent.putExtra("email", this.correo);
        intent.putExtra("token", this.token);
        intent.putExtra("money",this.money);
        intent.putExtra("record", this.record);
        startActivity(intent);
    }

    private void overridePendingTransition(int slideInLeft) {
    }

    public void onClickCerrarSesion(View view) {
        // Obtener el objeto SharedPreferences y editarlo
        SharedPreferences preferences = getSharedPreferences("Session", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Eliminar todas las preferencias
        editor.clear();
        editor.apply(); // Aplicar cambios

        // Redirigir al inicio (StartHomeActivity)
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Evita que vuelva atrás
        startActivity(intent);

        // Cerrar la actividad actual
        finish();
    }



}
