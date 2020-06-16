package com.example.sfoxv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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
    SwipeRefreshLayout pulltorefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent i = getIntent();
        sfox = (WebView) findViewById(R.id.web);
        edittext = (EditText) findViewById(R.id.edittext);
        pulltorefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);


        WebSettings webSettings = sfox.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        String site = i.getStringExtra("site");
        String url = i.getStringExtra("url");


        int flag = Integer.parseInt(i.getStringExtra("flag"));
        if (flag == 1) {
            switch (site) {
                case "gitclicked":
                    sfox.loadUrl("https://github.com/");
                    //edittext.setText("https://github.com/");
                    break;
                case "stackclicked":
                    sfox.loadUrl("https://stackoverflow.com/");
                    //edittext.setText("https://stackoverflow.com/");
                    break;
                case "ytclicked":
                    sfox.loadUrl("https://youtube.com/");
                    //edittext.setText("https://youtube.com/");
                    break;

            }
        } else if (flag == 0) {

            if(url.contains("."))
            {
                if (!url.startsWith("www.") && !url.startsWith("http://") && !url.startsWith("https://"))
                {
                    url = "www." + url;
                }
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    if (isValid("https://" + url)) {
                        url = "https://" + url;
                    } else if (isValid("http://" + url)) {
                        url = "http://" + url;
                    }
                }
            }
            else
            {
                url = "https://www.google.com/search?q="+url;
            }
            sfox.loadUrl(url);

        }

        edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    String urlstr = edittext.getText().toString();
                    if(urlstr.contains("."))
                    {
                        if (!urlstr.startsWith("www.") && !urlstr.startsWith("http://") && !urlstr.startsWith("https://"))
                        {
                            urlstr = "www." + urlstr;
                        }
                        if (!urlstr.startsWith("http://") && !urlstr.startsWith("https://")) {
                            if (isValid("https://" + urlstr)) {
                                urlstr = "https://" + urlstr;
                            } else if (isValid("http://" + urlstr)) {
                                urlstr = "http://" + urlstr;
                            }
                        }
                    }
                    else
                    {
                        urlstr = "https://www.google.com/search?q="+urlstr;
                    }
                    sfox.loadUrl(urlstr);
                }
                return false;
            }
        });
        sfox.setWebViewClient(new WebViewClient() {

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                edittext.setText(url);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                edittext.setText(url);
                return false;
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                edittext.setText(url);
            }
        });


        edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        pulltorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sfox.reload();
                pulltorefresh.setRefreshing(false);
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

    public boolean isValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
