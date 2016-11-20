package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.Joker;
import com.example.androidjokelibrary.AndroidLibActivity;


public class MainActivity extends ActionBarActivity implements EndpointsAsyncTask.EndpoinrResponseInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        //  new EndpointsAsyncTask(this).execute();
        //  Toast.makeText(this,"called",Toast.LENGTH_SHORT).show();
        // new EndpointsAsyncTask(MainActivity.this).execute();
        // Joker joker= new Joker();
        // Toast.makeText(this, joker.getJoke(), Toast.LENGTH_SHORT).show();
        //    Intent intent=new Intent(this,AndroidLibActivity.class);
        // intent.putExtra("joke",joker.getJoke());
        //  startActivity(intent);
    }


    @Override
    public void onResponse(boolean isSuccess, String result) {

        if (isSuccess) {
            Intent displayIntent = new Intent(this, AndroidLibActivity.class);
            displayIntent.putExtra("jokeToDisplay", result);
            startActivity(displayIntent);

            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Some Error: " + result, Toast.LENGTH_LONG).show();
        }
    }
}

