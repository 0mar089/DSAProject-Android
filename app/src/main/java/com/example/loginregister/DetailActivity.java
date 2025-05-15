package com.example.loginregister;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.loginregister.R;
import com.example.loginregister.Swagger.ShopItem;

public class DetailActivity extends AppCompatActivity {

    private TextView titleTxt, priceTxt, descriptionTxt;
    private ImageView picMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Referencias a los elementos del layout
        titleTxt = findViewById(R.id.TitleTxt);
        priceTxt = findViewById(R.id.priceDetailTxt);
        descriptionTxt = findViewById(R.id.textView9);
        picMain = findViewById(R.id.picMain);

        // Obtener el objeto ShopItem enviado desde el intent
        ShopItem item = (ShopItem) getIntent().getSerializableExtra("item");

        if (item != null) {
            titleTxt.setText(item.getName());
            priceTxt.setText(item.getPrice() + "$");
            descriptionTxt.setText(item.getDescription());

            // Cargar la imagen con Glide
            Glide.with(this)
                    .load(item.getUrl_icon())
                    .into(picMain);
        }
    }
}
