package com.example.loginregister;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.Clases.MediaResponse;

import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {

    private Context context;
    private List<MediaResponse> mediaList;

    public MediaAdapter(Context context, List<MediaResponse> mediaList) {
        this.context = context;
        this.mediaList = mediaList;
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_media, parent, false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        MediaResponse media = mediaList.get(position);

        // Extraer el ID del video de la URL
        String videoId = extractYoutubeId(media.getVideo());

        // Cargar el video en formato embed
        String html = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + videoId + "\" frameborder=\"0\" allowfullscreen></iframe>";

        holder.webView.getSettings().setJavaScriptEnabled(true);
        holder.webView.loadData(html, "text/html", "utf-8");
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    public static class MediaViewHolder extends RecyclerView.ViewHolder {
        WebView webView;

        public MediaViewHolder(@NonNull View itemView) {
            super(itemView);
            webView = itemView.findViewById(R.id.webView);
        }
    }

    // Método auxiliar para extraer ID del video
    private String extractYoutubeId(String url) {
        try {
            Uri uri = Uri.parse(url);
            return uri.getQueryParameter("v");  // Solo si es del tipo watch?v=
        } catch (Exception e) {
            return "";
        }
    }
}
