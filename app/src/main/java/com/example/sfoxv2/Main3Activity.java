package com.example.sfoxv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URL;

public class Main3Activity extends AppCompatActivity {
    WebView sfox;
    EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();
        Intent i = getIntent();
        sfox = (WebView)findViewById(R.id.web);
        edittext = (EditText)findViewById(R.id.edittext);

        //final Intent intent = new Intent(Main3Activity.this, Main2Activity.class);

        WebSettings webSettings = sfox.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        String site = i.getStringExtra("site");
        String url =  i.getStringExtra("url");
        int flag = Integer.parseInt(i.getStringExtra("flag"));
            if(flag==1) {
                switch (site) {
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
            }
            else if(flag==0) {
                if(!url.startsWith("www.")&& !url.startsWith("http://") && !url.startsWith("https://"))
                {
                    url = "www."+url;
                }
                if(!url.startsWith("http://") && !url.startsWith("https://"))
                {
                    if(isValid("https://"+url))
                    {
                        url = "https://"+url;
                    }
                    else if(isValid("http://"+url))
                    {
                        url = "http://"+url;
                    }
                }
                sfox.loadUrl(url);

            }

        edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE))
                {
                    String urlstr = edittext.getText().toString();
                    if(!urlstr.startsWith("www.")&& !urlstr.startsWith("http://") && !urlstr.startsWith("https://"))
                    {
                        urlstr = "www."+urlstr;
                    }
                    if(!urlstr.startsWith("http://") && !urlstr.startsWith("https://"))
                    {
                        if(isValid("https://"+urlstr))
                        {
                            urlstr = "https://"+urlstr;
                        }
                        else if(isValid("http://"+urlstr))
                        {
                            urlstr = "http://"+urlstr;
                        }
                    }
                    sfox.loadUrl(urlstr);
                }
                return false;
            }
        });
        sfox.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

        });

        edittext.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange (View v,boolean hasFocus){
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void onBackPressed() {
        if (sfox.canGoBack()) {
            sfox.goBack();
        } else {
            super.onBackPressed();
        }
    }
    public boolean isValid(String url)
    {
        try
        {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


}
