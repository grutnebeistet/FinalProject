package com.roberts.adrian.jokedisplayer;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        ((TextView)findViewById(R.id.display_joke_textview)).setText(getIntent().getStringExtra("joke"));
    }
}
