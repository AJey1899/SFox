package com.example.sfoxv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main3Activity extends AppCompatActivity {
    WebView sfox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();
        Intent i = getIntent();
        sfox = (WebView)findViewById(R.id.web);
        WebSettings webSettings = sfox.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String site = i.getStringExtra("site");
       /*if(site.equals("gitclicked"))
        {

        }*/
        switch(site)
        {
            case "gitclicked":
                sfox.loadUrl("https://github.com/");
                break;
            case "stackclicked":
                sfox.loadUrl("https://stackoverflow.com/");
                break;
            case "ytclicked":
                sfox.loadUrl("https://youtube.com/");
                break;
        }
        sfox.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView viewx, String url) {
                viewx.loadUrl(url);
                return false;
            }
        });

    }
    public void onBackPressed() {
        if (sfox.canGoBack()) {
            sfox.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
