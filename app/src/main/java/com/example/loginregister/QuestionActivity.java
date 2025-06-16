package com.example.loginregister;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginregister.Clases.API;
import com.example.loginregister.Clases.AuthService;
import com.example.loginregister.Clases.QuestionRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity {

    private EditText etTitle, etMessage, etSender;
    private Button btnSubmit;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        etTitle = findViewById(R.id.etTitle);
        etMessage = findViewById(R.id.etMessage);
        etSender = findViewById(R.id.etSender);
        btnSubmit = findViewById(R.id.btnSubmit);

        prefs = getSharedPreferences("Sesion", MODE_PRIVATE);
        String token = prefs.getString("token", "");

        btnSubmit.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String message = etMessage.getText().toString().trim();
            String sender = etSender.getText().toString().trim();

            if (title.isEmpty() || message.isEmpty() || sender.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }


            QuestionRequest question = new QuestionRequest(title, message, sender);
            sendQuestion(token, question);
        });
    }

    private void sendQuestion(String token, QuestionRequest question) {
        AuthService service = API.getAuthService();
        Call<Void> call = service.sendQuestion("Bearer " + token, etSender.getText().toString().trim(),etTitle.getText().toString().trim(),etMessage.getText().toString().trim());  // importante: incluir "Bearer "

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(QuestionActivity.this, "Consulta enviada", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(QuestionActivity.this, "Error en el servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(QuestionActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
