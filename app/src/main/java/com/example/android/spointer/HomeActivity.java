package com.example.android.spointer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.example.android.spointer.MainActivity;
import com.example.android.spointer.R;


public class HomeActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                startActivity(new Intent(HomeActivity.this, MainActivity.class));

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}

