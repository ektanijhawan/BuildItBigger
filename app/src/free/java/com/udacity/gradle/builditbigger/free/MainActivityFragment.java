package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;


public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.EndpoinrResponseInterface {

    public MainActivityFragment() {
    }

    private Button buttonJoke;
    private ProgressBar progressBar;
    private AdView mAdView;
    public PublisherInterstitialAd mPublisherInterstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_activity, container, false);
        Joker joker = new Joker();
        buttonJoke = (Button) root.findViewById(R.id.butttonJoke);
        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        mAdView = (AdView) root.findViewById(R.id.adView);
        progressBar.setVisibility(View.INVISIBLE);

        mPublisherInterstitialAd = new PublisherInterstitialAd(getActivity());
        mPublisherInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed(){
                requestInterstitialAd();

                new EndpointsAsyncTask(MainActivityFragment.this).execute();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        requestBannerAd();
        requestInterstitialAd();

        buttonJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPublisherInterstitialAd.isLoaded()){
                    mPublisherInterstitialAd.show();
                }else{

                    new EndpointsAsyncTask(MainActivityFragment.this).execute();
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });


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
            Toast.makeText(getActivity(), "Some Error: " + result, Toast.LENGTH_LONG).show();
        }
    }

    private void requestBannerAd(){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    private void requestInterstitialAd(){
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mPublisherInterstitialAd.loadAd(adRequest);
    }



}
