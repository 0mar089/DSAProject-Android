package com.example.loginregister;  // Añade esta línea al inicio

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.Clases.API;
import com.example.loginregister.Clases.AuthService;
import com.example.loginregister.Clases.MediaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaActivity extends AppCompatActivity {
    private ProgressBar progressBar;  // Completado
    private RecyclerView recyclerView;  // Completado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        // Inicializar vistas
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerViewMedia);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadVideos();
    }

    private void loadVideos() {
        AuthService authService = API.getAuthService();
        authService.getAllVideos().enqueue(new Callback<List<MediaResponse>>() {
            @Override
            public void onResponse(Call<List<MediaResponse>> call, Response<List<MediaResponse>> response) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                if (response.isSuccessful() && response.body() != null) {
                    List<MediaResponse> videos = response.body();
                    recyclerView.setAdapter(new MediaAdapter(MediaActivity.this, videos));
                } else {
                    Toast.makeText(MediaActivity.this, "Videos couldn't be obtained", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MediaResponse>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MediaActivity.this, "Connection error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}