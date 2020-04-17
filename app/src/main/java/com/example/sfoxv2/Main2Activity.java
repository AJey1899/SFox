package com.example.sfoxv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Main2Activity extends AppCompatActivity {

    ImageButton gitbtn,ytbtn,stackbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        gitbtn = (ImageButton)findViewById(R.id.gitbtn);
        stackbtn = (ImageButton)findViewById(R.id.stackbtn);
        ytbtn = (ImageButton)findViewById(R.id.ytbtn);
        final Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
        gitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("site","gitclicked");
                startActivity(intent);
            }
        });
        stackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("site","stackclicked");
                startActivity(intent);
            }
        });
        ytbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("site","ytclicked");
                startActivity(intent);
            }
        });
    }
}
