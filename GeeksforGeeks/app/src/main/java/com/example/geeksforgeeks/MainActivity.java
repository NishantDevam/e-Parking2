package com.example.geeksforgeeks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        This is used to open a new window in full screen after splash
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {// Handlers are background threads that allow you to communicate with the UI thread (update the UI).
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(i);
                finish();//This is used so that when we click back button from the main screen we don't see the splash again.
            }
        },2000);//the time till which a handler will work.

    }
}