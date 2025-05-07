package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginregister.Swagger.API;
import com.example.loginregister.Swagger.AuthService;
import com.example.loginregister.Swagger.ShopItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LobbyActivity extends AppCompatActivity {

    Button shopBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lobby); // Inflar el layout

        this.shopBtn = findViewById(R.id.shopBtn);

        // Getear Datos del usuario.
        String user = getIntent().getStringExtra("user");
        String correo = getIntent().getStringExtra("gmail");

        TextView UsuarioTxt = findViewById(R.id.UserTxtLobby);
        UsuarioTxt.setText(user);

    }

    public void shopClick(View view){
        // Llamada al servicio AuthService para obtener los productos de la tienda
        AuthService authService = API.getAuthService();  // Obtenemos el servicio AuthService
        Call<List<ShopItem>> call = authService.getShopItems();  // Llamamos al endpoint que devuelve los items de la tienda

        call.enqueue(new Callback<List<ShopItem>>() {
            @Override
            public void onResponse(Call<List<ShopItem>> call, Response<List<ShopItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ShopItem> items = response.body();
                    
                    for (ShopItem item : items) {
                        Log.d("ShopItem", "Producto: " + item.getName() + ", Descripción: " + item.getDescription());
                    }
                    // Aqui debemos de ir a la otra Activity y mostrar los datos.
                    Intent intent = new Intent(LobbyActivity.this, ShopActivity.class);
                    intent.putExtra("shop_items", new ArrayList<>(items));
                    startActivity(intent);
                } else {
                    Log.d("API", "No se pudieron obtener los productos");
                }
            }

            @Override
            public void onFailure(Call<List<ShopItem>> call, Throwable t) {
                // Manejar el error de la solicitud
                Log.e("API", "Error al obtener productos: " + t.getMessage());
            }
        });
    }

    public void PerfilClick(View view){
        Intent intent = new Intent(LobbyActivity.this, PerfilActivity.class);

    }

}
