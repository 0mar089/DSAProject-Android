package com.example.loginregister;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.Swagger.API;
import com.example.loginregister.Swagger.AuthService;
import com.example.loginregister.Swagger.UsersScoreResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewRanking);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AuthService authService = API.getAuthService();

        authService.getRanking().enqueue(new Callback<List<UsersScoreResponse>>() {
            @Override
            public void onResponse(Call<List<UsersScoreResponse>> call, Response<List<UsersScoreResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UsersScoreResponse> ranking = response.body();
                    for (UsersScoreResponse userScore : ranking) {
                        Log.d("Ranking", "Usuario: " + userScore.getUsuario() + ", Score: " + userScore.getScore());
                    }
                    RankingAdapter adapter = new RankingAdapter(RankingActivity.this, ranking);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(RankingActivity.this, "No se pudo obtener el ranking", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<UsersScoreResponse>> call, Throwable t) {
                Toast.makeText(RankingActivity.this, "Error de conexión al obtener ranking", Toast.LENGTH_SHORT).show();
                Log.e("RankingError", "Error: ", t);
            }
        });
    }

}