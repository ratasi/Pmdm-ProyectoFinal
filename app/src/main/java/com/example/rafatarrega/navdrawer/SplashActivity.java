package com.example.rafatarrega.navdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "Cargando SplashActivity";

    private long SPLASH_DELAY = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //TimerTask
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(mainIntent);
                Log.i(TAG, "Pasando a la siguiente pantalla");
                //Destruimos esta activity para prevenir
                // que el usuario vuelva a este Activity presionando el boton
                // Atras.
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_DELAY);
    }
}
