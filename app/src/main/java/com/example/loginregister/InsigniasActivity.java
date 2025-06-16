package com.example.loginregister;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.Clases.API;
import com.example.loginregister.Clases.AuthService;
import com.example.loginregister.Clases.InsigniaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class InsigniasActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insignias);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewFAQs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Layout vertical por defecto
        String username = getIntent().getStringExtra("user");
        AuthService authService = API.getAuthService();

        authService.getInsignias(username).enqueue(new Callback<List<InsigniaResponse>>() {
            @Override
            public void onResponse(Call<List<InsigniaResponse>> call, Response<List<InsigniaResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<InsigniaResponse> insignias = response.body();
                    for (InsigniaResponse insignia : insignias) {
                        Log.d("Badges", "Badge: " + insignia.getName() + ", Avatar: " + insignia.getAvatar());
                    }
                    // Crear el adaptador con la lista recibida y asignarlo al RecyclerView
                    InsigniasAdapter adapter = new InsigniasAdapter(InsigniasActivity.this, insignias);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(InsigniasActivity.this, "Badges couldn`t be obtained", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<InsigniaResponse>> call, Throwable t) {
                Toast.makeText(InsigniasActivity.this, "Connection error trying to obtain badges", Toast.LENGTH_SHORT).show();
                Log.e("BadgeError", "Error: ", t);
            }
        });

    }

    public void onClickBack(View view) {
        finish();
        overridePendingTransition(R.anim.slide_in_left);
    }

    private void overridePendingTransition(int slideInLeft) {
    }
}
