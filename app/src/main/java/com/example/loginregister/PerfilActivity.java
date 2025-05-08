package com.example.loginregister;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    public String user;
    public String correo;
    EditText txtNewPassword;
    Button enviarBtn;
    Button mostrarInputsBtn;

    TextView correoTxtView;

    TextView userTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        this.user = getIntent().getStringExtra("user");
        this.correo = getIntent().getStringExtra("correo");

        correoTxtView = findViewById(R.id.correoTxtView);
        userTxtView = findViewById(R.id.userTxtView);

        correoTxtView.setText(this.correo);
        userTxtView.setText(this.user);

        txtNewPassword = findViewById(R.id.txtNewPassword);
        enviarBtn = findViewById(R.id.enviarBtn);

        // Están ocultos por XML, pero puedes reforzarlo aquí
        txtNewPassword.setVisibility(View.GONE);
        enviarBtn.setVisibility(View.GONE);

    }

    public void changePassClick(View view){
        txtNewPassword.setVisibility(View.VISIBLE);
        enviarBtn.setVisibility(View.VISIBLE);

    }

    public void deleteAccountClick(View view){

    }

    public void enviarClick(View view){

    }

}
