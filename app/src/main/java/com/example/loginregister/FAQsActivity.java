package com.example.loginregister;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.loginregister.Swagger.API;
import com.example.loginregister.Swagger.AuthService;
import com.example.loginregister.Swagger.FAQs;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAQsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewFAQs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AuthService authService = API.getAuthService();
        authService.getFAQs().enqueue(new Callback<List<FAQs>>() {
            @Override
            public void onResponse(Call<List<FAQs>> call, Response<List<FAQs>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<FAQs> faqs = response.body();

                    for (FAQs faq : faqs) {
                        Log.d("API_DEBUG", "Pregunta: " + faq.getQuestion() + " - Respuesta: " + faq.getAnswer());
                    }

                    FAQsAdapter adapter = new FAQsAdapter(FAQsActivity.this, faqs);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("API_ERROR", "Error al obtener FAQs: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<FAQs>> call, Throwable t) {
                Log.e("API_ERROR", "Error al obtener FAQs");
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