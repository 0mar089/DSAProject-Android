package com.example.loginregister;

import android.os.Bundle;
import android.util.Log;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMedia);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AuthService authService = API.getAuthService();
        authService.getAllVideos().enqueue(new Callback<List<MediaResponse>>() {
            @Override
            public void onResponse(Call<List<MediaResponse>> call, Response<List<MediaResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MediaResponse> videos = response.body();
                    recyclerView.setAdapter(new MediaAdapter(MediaActivity.this, videos));
                } else {
                    Toast.makeText(MediaActivity.this, "No se pudieron obtener los videos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MediaResponse>> call, Throwable t) {
                Toast.makeText(MediaActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                Log.e("MediaError", "Error al cargar videos", t);
            }
        });
    }
}
