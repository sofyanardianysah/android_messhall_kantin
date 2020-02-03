package dev.sofie.messhalkantin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import dev.sofie.messhalkantin.R;

import static dev.sofie.messhalkantin.helper.SharedPreferecesHelper.userIDPref;
import static dev.sofie.messhalkantin.helper.SharedPreferecesHelper.userPreferences;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            public void run() {

                if (getSharedPreferences(userPreferences,0).getInt(userIDPref, 0) != 0) {
                    SplashActivity splashActivity = SplashActivity.this;
                    splashActivity.startActivity(new Intent(splashActivity.getApplicationContext(), MainActivity.class));
                } else {
                    SplashActivity splashActivity2 = SplashActivity.this;
                    splashActivity2.startActivity(new Intent(splashActivity2.getApplicationContext(), LoginActivity.class));
                }
                finish();
            }
        }, 2500);
    }
}
