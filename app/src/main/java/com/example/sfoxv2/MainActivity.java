package com.example.sfoxv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Thread t = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                    Intent i = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(i);
                    finish();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}
