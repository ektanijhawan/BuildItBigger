package com.example.androidjokelibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.Joker;


public class AndroidLibActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_lib);
        Joker joke= new Joker();
        TextView jokeView= (TextView) findViewById(R.id.tvJoke);
        jokeView.setText(joke.getJoke());
    }
}
