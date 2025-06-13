package com.example.loginregister;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginregister.Swagger.API;
import com.example.loginregister.Swagger.AuthService;
import com.example.loginregister.Swagger.UserStatsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LobbyActivity extends AppCompatActivity {

    private Button shopBtn;
    private String user;
    private String correo;
    private String token;
    private String record;
    private String money;
    private SharedPreferences prefs;
    private View playBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        this.playBtn = findViewById(R.id.playBtn);

        // Obtener datos del usuario desde Intent
        this.user = getIntent().getStringExtra("user");
        this.correo = getIntent().getStringExtra("correo");
        this.token = getIntent().getStringExtra("token");

        // Si los datos no vienen en el Intent, recuperarlos desde SharedPreferences
        prefs = getSharedPreferences("Sesion", MODE_PRIVATE);
        if (user == null || user.isEmpty()) {
            this.user = prefs.getString("user", "Invitado");
        }
        if (correo == null || correo.isEmpty()) {
            this.correo = prefs.getString("correo", "Sin correo");
        }
        if (this.token == null || this.token.isEmpty()) {
            this.token = prefs.getString("token", null);
        }

        // Mostrar el nombre del usuario en pantalla
        TextView UsuarioTxt = findViewById(R.id.UserTxtLobby);
        UsuarioTxt.setText(this.user);

        // Llamada a la API para obtener dinero y récord
        AuthService authService = API.getAuthService();
        authService.getUserStats("Bearer " + token).enqueue(new Callback<UserStatsResponse>() {
            @Override
            public void onResponse(Call<UserStatsResponse> call, Response<UserStatsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    int dinero = response.body().getMoney();
                    int record = response.body().getScore();

                    TextView dineroText = findViewById(R.id.moneyTxt);
                    TextView recordText = findViewById(R.id.recordTxt);
                    dineroText.setText("Dinero: " + dinero);
                    recordText.setText("Récord: " + record);

                    LobbyActivity.this.money = String.valueOf(dinero);
                    LobbyActivity.this.record = String.valueOf(record);
                } else if (response.code() == 404 || response.code() == 401) {
                    SharedPreferences prefs = getSharedPreferences("Sesion", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.clear();
                    editor.apply();

                    Toast.makeText(LobbyActivity.this, "Sesión expirada. Inicia sesión nuevamente.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LobbyActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(LobbyActivity.this, "Error al obtener datos del usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserStatsResponse> call, Throwable t) {
                Toast.makeText(LobbyActivity.this, "Fallo al conectar con servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void shopClick(View view) {
        Intent intent = new Intent(LobbyActivity.this, SplashScreenActivity.class);
        intent.putExtra("origen", "lobby");
        TextView dineroText = findViewById(R.id.moneyTxt);
        intent.putExtra("money", dineroText.getText().toString());
        intent.putExtra("token", this.token);
        startActivity(intent);
    }

    public void perfilClick(View view) {
        Intent intent = new Intent(LobbyActivity.this, PerfilActivity.class);
        intent.putExtra("user", this.user);
        intent.putExtra("correo", this.correo);
        intent.putExtra("token", this.token);
        intent.putExtra("money", this.money);
        intent.putExtra("record", this.record);
        startActivity(intent);
        finish();
    }

    public void jugarClick(View view) {
        try {
            Intent i = new Intent();
            i.setComponent(new ComponentName(
                    "com.DefaultCompany.DSAProjectUnity",
                    "com.unity3d.player.UnityPlayerGameActivity"
            ));
            i.putExtra("user", this.user);
            i.putExtra("money", this.money);
            i.putExtra("record", this.record);
            startActivityForResult(i, 0);
        } catch (Exception e) {
            Toast.makeText(this, "Instala la app de Unity primero", Toast.LENGTH_SHORT).show();
            Log.e("UnityLaunchError", "Error al lanzar la app Unity", e);
        }
    }

    public void chatClick(View view) {
        // Aquí podrías abrir un ChatActivity o implementar más adelante
    }

    public void rankingClick(View view) {
        Intent intent = new Intent(LobbyActivity.this, RankingActivity.class);
        startActivity(intent);
    }

    // Para enlazar boton "?"
    public void helpClick(View view) {
        Intent intent = new Intent(LobbyActivity.this, QuestionActivity.class);
        intent.putExtra("correo", this.correo);  // para autocompletar remitente
        startActivity(intent);
    }
}
