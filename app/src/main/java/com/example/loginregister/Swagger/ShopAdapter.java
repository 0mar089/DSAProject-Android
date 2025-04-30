package com.example.loginregister.Swagger;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginregister.R;


public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder>{
    private List<ShopItem> values;

    // Constructor para recibir la lista de ShopItems
    public ShopAdapter(List<ShopItem> myDataset) {
        values = myDataset;
    }

    public void setData(List<ShopItem> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    // Método para agregar un ítem en una posición específica
    public void add(int position, ShopItem item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    // Método para eliminar un ítem en una posición específica
    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // ViewHolder que mantiene las vistas de cada ítem
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

    // Crear nuevas vistas (invocado por el LayoutManager)
    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.activity_row_shop, parent, false); // Asegúrate de que 'item_shop' es el layout correcto
        return new ShopViewHolder(v);
    }

    // Reemplazar el contenido de una vista (invocado por el LayoutManager)
    @Override
    public void onBindViewHolder(final ShopViewHolder holder, final int position) {
        // Obtener el elemento de la lista en esta posición
        ShopItem item = values.get(position);

        // Establecer los valores en los TextViews y cargar la imagen
        holder.txtName.setText(item.getName());
        holder.txtDescription.setText(item.getDescription());

        // Usar Glide para cargar la imagen desde la URL (puedes usar otras librerías si lo prefieres)
        Glide.with(holder.icon.getContext())
                .load(item.getUrl_icon())
                .into(holder.icon);
    }

    // Retornar el tamaño de la lista (invocado por el LayoutManager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
