package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.Swagger.API;
import com.example.loginregister.Swagger.AuthService;
import com.example.loginregister.Swagger.ShopAdapter;
import com.example.loginregister.Swagger.ShopItem;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class ShopActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ShopAdapter adapter;
    private List<ShopItem> shopItems;
    private ProgressBar progressBarItems;

    private ProgressBar progressBarBanner;


    public void hideProgressBars() {
        progressBarItems.setVisibility(View.GONE);
        progressBarBanner.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        recyclerView = findViewById(R.id.recyclerViewItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBarItems = findViewById(R.id.progressBarItems);
        progressBarItems.setVisibility(View.VISIBLE);

        ImageView bannerImage = findViewById(R.id.banner);
        bannerImage.setImageResource(R.drawable.tienda_tocabolas_banner);

        progressBarBanner = findViewById(R.id.progressBarBanner);
        progressBarBanner.setVisibility(View.VISIBLE);

        // Inicializa la lista antes de llamar a la API
        shopItems = new ArrayList<>();
        adapter = new ShopAdapter(shopItems);
        recyclerView.setAdapter(adapter);

        // Llamar a la API y actualizar la tienda
        getShopItems();
    }

    private void getShopItems() {
        AuthService authService = API.getAuthService();
        authService.getShopItems().enqueue(new Callback<List<ShopItem>>() {
            @Override
            public void onResponse(Call<List<ShopItem>> call, Response<List<ShopItem>> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Log.d("API", "No se pudieron obtener los productos");
                    progressBarItems.setVisibility(View.GONE);
                    return;
                }

                // Actualiza la lista con los ítems de la API
                shopItems.clear();
                shopItems.addAll(response.body());

                // Actualizar el adapter para que los datos aparezcan en la UI
                adapter.notifyDataSetChanged();
                progressBarItems.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<ShopItem>> call, Throwable t) {
                Log.e("API", "Error al obtener productos: " + t.getMessage());
                progressBarItems.setVisibility(View.GONE);
            }


        });

    }
    public void itemDetailClick (View view){

        try {
            // Obtener el ViewHolder (puede necesitar ajustes según tu layout)
            View parent = (View) view.getParent().getParent(); // Ajusta según tu jerarquía

            RecyclerView recyclerView = findViewById(R.id.recyclerViewItems);
            if (recyclerView != null) {
                int position = recyclerView.getChildLayoutPosition(parent);

                if (position != RecyclerView.NO_POSITION && position < shopItems.size()) {
                    ShopItem clickedItem = shopItems.get(position);

                    Intent intent = new Intent(this, DetailActivity.class);
                    intent.putExtra("shopItem", clickedItem);
                    intent.putExtra("itemPosition", position);
                    startActivity(intent);
                }
            }
        } catch (Exception e) {
            Log.e("ShopActivity", "Error al obtener posición del item", e);
        }

    }
}

