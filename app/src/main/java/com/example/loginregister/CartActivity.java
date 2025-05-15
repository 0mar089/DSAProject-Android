package com.example.loginregister;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.Swagger.ShopItem;

import java.util.ArrayList;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);

        RecyclerView recyclerView = findViewById(R.id.cartView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Map<ShopItem, Integer> cartItems = com.example.loginregister.CartManager.getCartItems();
        CartAdapter adapter = new CartAdapter(new ArrayList<>(cartItems.entrySet())); // Convierte HashMap a lista de pares
        recyclerView.setAdapter(adapter);
    }

}