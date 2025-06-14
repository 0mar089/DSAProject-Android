package com.example.loginregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginregister.Swagger.InsigniaResponse;

import java.util.List;

public class InsigniasAdapter extends RecyclerView.Adapter<InsigniasAdapter.ViewHolder>{
    private List<InsigniaResponse> insigniasList;
    private Context context;

    public InsigniasAdapter(Context context, List<InsigniaResponse> insigniasList) {
        this.context = context;
        this.insigniasList = insigniasList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_row_insignias, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InsigniaResponse insignia = insigniasList.get(position);
        holder.firstLine.setText(insignia.getName());

        Glide.with(context)
                .load(insignia.getAvatar())
                .placeholder(R.mipmap.ic_launcher) // imagen por defecto mientras carga
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return insigniasList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView firstLine;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            firstLine = itemView.findViewById(R.id.firstLine);
        }
    }
}
