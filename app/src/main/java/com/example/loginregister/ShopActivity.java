package com.example.loginregister;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.loginregister.Swagger.ShopAdapter;
import com.example.loginregister.Swagger.ShopItem;
import java.util.ArrayList;
import java.util.List;

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
        progressBarItems.setVisibility(View.VISIBLE); // Muestra el ProgressBar al inicio

        ImageView bannerImage = findViewById(R.id.banner);
        bannerImage.setImageResource(R.drawable.tienda_tocabolas_banner);

        progressBarBanner = findViewById(R.id.progressBarBanner);
        progressBarBanner.setVisibility(View.VISIBLE); // Muestra el ProgressBar al inicio


        // Recuperamos los datos que nos pasaron
        ArrayList<ShopItem> shopItems = (ArrayList<ShopItem>) getIntent().getSerializableExtra("shop_items");

        if (shopItems == null) {
            shopItems = new ArrayList<>();
        }

        adapter = new ShopAdapter(shopItems);
        recyclerView.setAdapter(adapter);
    }
}
