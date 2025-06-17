package com.example.loginregister;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.Clases.API;
import com.example.loginregister.Clases.AuthService;
import com.example.loginregister.Clases.UsersScoreResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AuthService authService;
    private Handler handler = new Handler();
    private Runnable updateRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        recyclerView = findViewById(R.id.recyclerViewFAQs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        authService = API.getAuthService();

        fetchRanking(); // Obtiene el ranking inicial

        // Configura la actualización automática cada 30 segundos
        updateRanking = new Runnable() {
            @Override
            public void run() {
                fetchRanking();
                handler.postDelayed(this, 30000);
            }
        };

        handler.postDelayed(updateRanking, 30000);
    }

    private void fetchRanking() {
        authService.getRanking().enqueue(new Callback<List<UsersScoreResponse>>() {
            @Override
            public void onResponse(Call<List<UsersScoreResponse>> call, Response<List<UsersScoreResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UsersScoreResponse> ranking = response.body();

                    // Ordenar por puntuación en orden descendente
                    ranking.sort((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()));

                    RankingAdapter adapter = new RankingAdapter(RankingActivity.this, ranking);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(RankingActivity.this, "Ranking couldn't be obtained", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<UsersScoreResponse>> call, Throwable t) {
                Toast.makeText(RankingActivity.this, "Connection error obtaining ranking", Toast.LENGTH_SHORT).show();
                Log.e("RankingError", "Error: ", t);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateRanking); // Detiene la actualización automática al cerrar la actividad
    }

    public void onClickBack(View view) {
        finish();
        overridePendingTransition(R.anim.slide_in_left);
    }

    private void overridePendingTransition(int slideInLeft) {
    }
}
