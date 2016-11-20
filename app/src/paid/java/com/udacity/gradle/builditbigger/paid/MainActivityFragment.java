package com.udacity.gradle.builditbigger.paid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.Joker;
import com.example.androidjokelibrary.AndroidLibActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.EndpointResponseInterface {
    private ProgressBar progressBar;
  Button buttonJoke;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_activity, container, false);

        buttonJoke = (Button) root.findViewById(R.id.buttonJoke);
        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        buttonJoke.setOnClickListener(new View.OnClickListener()

                                      {
                                          @Override
                                          public void onClick(View v) {


                                              new EndpointsAsyncTask(MainActivityFragment.this).execute();
                                              progressBar.setVisibility(View.VISIBLE);

                                          }
                                      }

        );


        return root;


    }

    @Override
    public void onResponse(boolean isSuccess, String result) {
        progressBar.setVisibility(View.INVISIBLE);
        if (isSuccess) {
            Intent displayIntent = new Intent(getActivity(), AndroidLibActivity.class);
            displayIntent.putExtra("jokeToDisplay", result);
            startActivity(displayIntent);

            Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.error) + result, Toast.LENGTH_LONG).show();
        }
    }
}
