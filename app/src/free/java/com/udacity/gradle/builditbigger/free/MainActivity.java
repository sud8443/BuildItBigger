package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.EndPointAsyncTask;
import com.udacity.gradle.builditbigger.R;

import developersudhanshu.com.jokedisplaylibrary.JokeConstants;
import developersudhanshu.com.jokedisplaylibrary.JokeDisplayActivity;


public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAdObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAdObject = new InterstitialAd(this);
        mInterstitialAdObject.setAdUnitId(getResources().getString(R.string.interstitial_ad_test_ad_unit));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mInterstitialAdObject.loadAd(new AdRequest.Builder().build());
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
        EndPointAsyncTask asyncTask = new EndPointAsyncTask();
        asyncTask.execute(new Pair<Context, String>(this, "ABC"));

        final MainActivityFragment fragment = (MainActivityFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_free);
        fragment.changeLoadingLayoutVisibilty(true);
        asyncTask.setJokeResultListner(new EndPointAsyncTask.JokeResultListener() {
            @Override
            public void gotJokeFromApi(String joke) {
                fragment.changeLoadingLayoutVisibilty(false);
                Intent jokeIntent = new Intent(MainActivity.this, JokeDisplayActivity.class);
                jokeIntent.putExtra(JokeConstants.JOKE_INTENT_EXTRA, joke);
                startActivity(jokeIntent);
                if (mInterstitialAdObject.isLoaded()) {
                    mInterstitialAdObject.show();
                }
            }
        });
    }


}
