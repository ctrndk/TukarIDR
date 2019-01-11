// CSIS 3175 Group Project : 300267900 Yunjie Gao ; 300280496 Hsueh-Cheng Liu

package com.dolanan.dev.tukaridr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Set Timer for welcome page
        TimerTask myTimerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, SelectCountriesActivity.class));
                finish();
            }
        };
        Timer myTimer = new Timer();
        myTimer.schedule(myTimerTask, 5000);
    }
}
