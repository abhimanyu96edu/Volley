package com.abhimanyusharma.volley;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HomeScreenActivity extends AppCompatActivity {

    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        welcome = (TextView) findViewById(R.id.welcome);

        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Welcome !!", Toast.LENGTH_SHORT).show();

        welcome.setText("WELCOME MR. " + getIntent().getExtras().getString("Mobile") + " !!");

    }

    boolean backPress = false;

    @Override
    public void onBackPressed() {

        if (backPress) {
            super.onBackPressed();
        }

        this.backPress = true;
        Toast.makeText(getApplicationContext(), "Please Press Back one more time to Exit", Toast.LENGTH_SHORT).show();

        //.postDelayed(new Runnable(), TIME MILLISECOND)
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                backPress = false;
            }
        }, 2000);
    }
}

