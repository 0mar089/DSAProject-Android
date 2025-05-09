package com.example.loginregister;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);  // Verifica que este nombre coincide con tu XML

        recyclerView = findViewById(R.id.recyclerViewItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Recuperamos los datos que nos pasaron
        ArrayList<ShopItem> shopItems = (ArrayList<ShopItem>) getIntent().getSerializableExtra("shop_items");

        if (shopItems == null) {
            shopItems = new ArrayList<>();
        }

        adapter = new ShopAdapter(shopItems);
        recyclerView.setAdapter(adapter);
    }
}
