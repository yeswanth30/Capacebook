package com.capacebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class screenflash extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_TIME = 2500;

    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screenflash);
        logo = findViewById(R.id.logo);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(screenflash.this, welcomepage.class);
                startActivity(intent); // Start the welcomepage activity
                finish(); // Finish the current activity if needed
            }
        }, SPLASH_DISPLAY_TIME);
    }
}


