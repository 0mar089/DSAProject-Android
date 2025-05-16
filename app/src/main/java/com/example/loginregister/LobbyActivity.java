package com.example.loginregister;

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
import com.example.loginregister.Swagger.ShopItem;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LobbyActivity extends AppCompatActivity {

    private Button shopBtn;
    private String user;
    private String correo;
    private String token;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        this.shopBtn = findViewById(R.id.shopBtn3);

        // Obtener datos del usuario desde `Intent`
        this.user = getIntent().getStringExtra("user");
        this.correo = getIntent().getStringExtra("correo");
        this.token = getIntent().getStringExtra("token");

        // Si los datos no vienen en el `Intent`, recuperarlos desde `SharedPreferences`
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
    }

    public void shopClick(View view) {
        // Siempre ir a la SplashScreen antes de la tienda
        Intent intent = new Intent(LobbyActivity.this, SplashScreenActivity.class);
        intent.putExtra("origen", "lobby");
        startActivity(intent);
    }

//    private void startShopActivity() {
//        AuthService authService = API.getAuthService();
//        authService.getShopItems().enqueue(new Callback<List<ShopItem>>() {
//            @Override
//            public void onResponse(Call<List<ShopItem>> call, Response<List<ShopItem>> response) {
//                if (!response.isSuccessful() || response.body() == null) {
//                    Log.d("API", "No se pudieron obtener los productos");
//                    return;
//                }
//
//                List<ShopItem> items = response.body();
//                startActivity(new Intent(LobbyActivity.this, ShopActivity.class).putExtra("shop_items", new ArrayList<>(items)));
//            }
//
//            @Override
//            public void onFailure(Call<List<ShopItem>> call, Throwable t) {
//                Log.e("API", "Error al obtener productos: " + t.getMessage());
//            }
//        });
//    }

    public void perfilClick(View view) {
        Intent intent = new Intent(LobbyActivity.this, PerfilActivity.class);
        intent.putExtra("user", this.user);
        intent.putExtra("correo", this.correo);
        intent.putExtra("token", this.token);
        startActivity(intent);
        finish();
    }

    public void jugarClick(View view){
        String unityPackage = "com.DefaultCompany.DSAProjectUnity";
        String unityActivity = "com.unity3d.player.UnityPlayerGameActivity";

        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(unityPackage, unityActivity));

            // Verificamos si existe el intent para evitar que crashee si no está instalado
            if (getPackageManager().resolveActivity(intent, 0) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "El juego de Unity no está instalado.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al intentar lanzar el juego.", Toast.LENGTH_LONG).show();
        }
    }
}
