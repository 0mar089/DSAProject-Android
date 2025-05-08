package com.example.loginregister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
        setContentView(R.layout.activity_lobby); // Inflar el layout

        this.shopBtn = findViewById(R.id.shopBtn);

        //  Obtener datos del usuario desde `Intent`
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
            this.token = prefs.getString("token", null); // intenta desde SharedPreferences como respaldo
        }

        //  Mostrar el nombre del usuario en pantalla
        TextView UsuarioTxt = findViewById(R.id.UserTxtLobby);
        UsuarioTxt.setText(  this.user );

    }

    public void shopClick(View view){
        AuthService authService = API.getAuthService();  // Obtenemos el servicio AuthService
        Call<List<ShopItem>> call = authService.getShopItems();  // Llamamos al endpoint de la tienda

        call.enqueue(new Callback<List<ShopItem>>() {
            @Override
            public void onResponse(Call<List<ShopItem>> call, Response<List<ShopItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ShopItem> items = response.body();

                    for (ShopItem item : items) {
                        Log.d("ShopItem", "Producto: " + item.getName() + ", Descripción: " + item.getDescription());
                    }
                    Intent intent = new Intent(LobbyActivity.this, ShopActivity.class);
                    intent.putExtra("shop_items", new ArrayList<>(items));
                    startActivity(intent);
                } else {
                    Log.d("API", "No se pudieron obtener los productos");
                }
            }

            @Override
            public void onFailure(Call<List<ShopItem>> call, Throwable t) {
                Log.e("API", "Error al obtener productos: " + t.getMessage());
            }
        });
    }

    public void perfilClick(View view){
        Intent intent = new Intent(LobbyActivity.this, PerfilActivity.class);
        intent.putExtra("user", this.user);
        intent.putExtra("correo", this.correo);
        intent.putExtra("token", this.token);
        startActivity(intent);
        finish();
    }
}
