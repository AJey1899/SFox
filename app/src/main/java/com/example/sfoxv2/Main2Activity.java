package com.example.sfoxv2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    ImageButton gitbtn, ytbtn, stackbtn;
    EditText edittext;
    String urlstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        gitbtn = (ImageButton) findViewById(R.id.gitbtn);
        stackbtn = (ImageButton) findViewById(R.id.stackbtn);
        ytbtn = (ImageButton) findViewById(R.id.ytbtn);
        edittext = (EditText) findViewById(R.id.edittext);


        final Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
        gitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("site", "gitclicked");
                intent.putExtra("flag","1");
                startActivity(intent);
            }
        });
        stackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("site", "stackclicked");
                intent.putExtra("flag","1");
                startActivity(intent);
            }
        });
        ytbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("site", "ytclicked");
                intent.putExtra("flag","1");
                startActivity(intent);
            }
        });




        edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {

                    urlstr = edittext.getText().toString();
                    intent.putExtra("url",urlstr);
                    intent.putExtra("flag","0");
                    startActivity(intent);
                }
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

}

