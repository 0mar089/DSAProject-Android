package com.example.loginregister.Swagger;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.loginregister.R;
import com.example.loginregister.ShopActivity;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    private List<ShopItem> values;

    public ShopAdapter(List<ShopItem> myDataset) {
        values = myDataset;
    }

    public void setData(List<ShopItem> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, ShopItem item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName, txtDescription;
        public ImageView icon;
        public View layout;

        public ShopViewHolder(View v) {
            super(v);
            layout = v;
            txtName = v.findViewById(R.id.firstLine);
            txtDescription = v.findViewById(R.id.secondLine);
            icon = v.findViewById(R.id.icon);
        }
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.activity_row_shop, parent, false);
        return new ShopViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ShopViewHolder holder, int position) {
        ShopItem item = values.get(position);

        holder.txtName.setText(item.getName());
        holder.txtDescription.setText(item.getDescription());

        Glide.with(holder.icon.getContext())
                .load(item.getUrl_icon())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (holder.getBindingAdapterPosition() == values.size() - 1) {
                            ((ShopActivity) holder.itemView.getContext()).hideProgressBars();  // Oculta ambos
                        }
                        return false;
                    }
                })
                .into(holder.icon);
    }






    @Override
    public int getItemCount() {
        return values.size();
    }
}
