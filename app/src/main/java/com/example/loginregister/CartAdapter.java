package com.example.loginregister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginregister.Swagger.ShopItem;
import java.util.List;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Map.Entry<ShopItem, Integer>> cartItems;

    public CartAdapter(List<Map.Entry<ShopItem, Integer>> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item_pic_right, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Map.Entry<ShopItem, Integer> entry = cartItems.get(position);
        ShopItem item = entry.getKey();
        int cantidad = entry.getValue();

        holder.titleTxt.setText(item.getName());
        holder.priceTxt.setText(item.getPrice() * cantidad + "$");
        holder.quantityTxt.setText("Cantidad: " + cantidad);

        Glide.with(holder.picMain.getContext())
                .load(item.getUrl_icon())
                .into(holder.picMain);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, priceTxt, quantityTxt;
        ImageView picMain;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            priceTxt = itemView.findViewById(R.id.pricetxt);
            quantityTxt = itemView.findViewById(R.id.quantityTxt);
            picMain = itemView.findViewById(R.id.imageView5); // Asegúrate que el ID sea el correcto
        }
    }
}
