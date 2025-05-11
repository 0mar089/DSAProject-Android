package com.example.loginregister.Swagger;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.loginregister.R;
import com.example.loginregister.ShopActivity;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    private List<ShopItem> values;

    public ShopAdapter(List<ShopItem> values) {
        this.values = values;
    }

    public static class ShopViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName, txtPrice;
        public ShapeableImageView picItem;
        public ImageView plusIcon;
        public View layout;


        public ShopViewHolder(View v) {
            super(v);
            layout = v;
            txtName = v.findViewById(R.id.nameItem);
            txtPrice = v.findViewById(R.id.priceTxt);
            plusIcon = v.findViewById(R.id.imageView4);
            picItem = v.findViewById(R.id.picItem);
        }
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.viewholder_shop, parent, false);
        return new ShopViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ShopViewHolder holder, int position) {
        ShopItem item = values.get(position);

        holder.txtName.setText(item.getName());
        holder.txtPrice.setText(String.valueOf(item.getPrice()));

        Glide.with(holder.picItem.getContext())
                .load(item.getUrl_icon())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                        if (holder.getBindingAdapterPosition() == values.size() - 1) {
                            ((ShopActivity) holder.itemView.getContext()).hideProgressBars();
                        }
                        return false;
                    }
                })
                .into(holder.picItem);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
