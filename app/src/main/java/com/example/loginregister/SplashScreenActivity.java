package com.example.loginregister;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class SplashScreenActivity extends Activity{

    public class Constants {
        public static final String ORIGEN_HOME = "home";
        public static final String ORIGEN_LOGIN = "login";
        public static final String ORIGEN_REGISTER = "register";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // Recupera el extra (con valor por defecto si no existe)
        String origen = getIntent().getStringExtra("origen");

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent;
            String user = "";
            String gmail = "";
            String token = "";
            if (origen != null) {
                switch (origen) {
                    case Constants.ORIGEN_HOME:
                        intent = new Intent(this, StartActivity.class);
                        break;
                    case Constants.ORIGEN_LOGIN:
                        intent = new Intent(this, LobbyActivity.class);
                        user = getIntent().getStringExtra("user");
                        gmail = getIntent().getStringExtra("correo");
                        token = getIntent().getStringExtra("token");
                        break;
                    case Constants.ORIGEN_REGISTER:
                        intent = new Intent(this, LobbyActivity.class);
                        user = getIntent().getStringExtra("user");
                        gmail = getIntent().getStringExtra("correo");
                        token = getIntent().getStringExtra("token");
                        break;
                    default: // Por defecto va a StartActivity
                        intent = new Intent(this, StartActivity.class);
                        Toast.makeText(SplashScreenActivity.this, "ERROR ESTAS EN EL DEFAULT, ", Toast.LENGTH_LONG).show();
                }
            } else {
                intent = new Intent(this, StartActivity.class);
            }
            if(user.isEmpty()){
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            else{
                intent.putExtra("user", user);
                intent.putExtra("correo", gmail);
                intent.putExtra("token", token);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            }

            startActivity(intent);
            finish();

        }, 3000);
    }


}