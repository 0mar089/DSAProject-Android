package com.example.loginregister;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.Clases.API;
import com.example.loginregister.Clases.AuthService;
import com.example.loginregister.Clases.GenericResponse;
import com.example.loginregister.Clases.ShopItem;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewFAQs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        TextView totalTxt = findViewById(R.id.totalTxt);

        Map<ShopItem, Integer> cartItems = com.example.loginregister.CartManager.getCartItems();
        CartAdapter adapter = new CartAdapter(new ArrayList<>(cartItems.entrySet()), totalTxt);

        recyclerView.setAdapter(adapter);


        double total = 0;
        for (Map.Entry<ShopItem, Integer> entry : cartItems.entrySet()) {
            ShopItem item = entry.getKey();
            int cantidad = entry.getValue();
            total += item.getPrice() * cantidad;
        }


        totalTxt.setText(String.format("%.2f $", total));
    }


    public void onClickBack(View view) {
        finish();
        overridePendingTransition(R.anim.slide_in_left);
    }

    private void overridePendingTransition(int slideInLeft) {
    }

    public void clickBuyBtn(View view){

        String token = getIntent().getStringExtra("token");


        Map<ShopItem, Integer> cartItems = com.example.loginregister.CartManager.getCartItems();
        System.out.println(cartItems);
        String itemsString = generateItemsString(cartItems);

        AuthService authService = API.getAuthService();
        Call<GenericResponse> call = authService.comprarItems("Bearer " + token, itemsString);

        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isStatus()) {
                        Toast.makeText(CartActivity.this, "Succesful purchase!", Toast.LENGTH_SHORT).show();
                        com.example.loginregister.CartManager.clearCart();
                        finish(); // salir del carrito o actualizarlo
                    } else {
                        Toast.makeText(CartActivity.this, "Error: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CartActivity.this, "Purchase error, please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public String generateItemsString(Map<ShopItem, Integer> cartItems) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<ShopItem, Integer> entry : cartItems.entrySet()) {
            sb.append(entry.getKey().getId())
                    .append(':')
                    .append(entry.getValue())
                    .append(',');
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


}