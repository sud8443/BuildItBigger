package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.util.Log;

import developersudhanshu.com.javajokeslibrary.RandomProgrammingJokes;
import developersudhanshu.com.jokedisplaylibrary.JokeConstants;
import developersudhanshu.com.jokedisplaylibrary.JokeDisplayActivity;

// Since these two files are the part of the main conifguration
import com.udacity.gradle.builditbigger.EndPointAsyncTask;
import com.udacity.gradle.builditbigger.R;


public class MainActivity extends AppCompatActivity {

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
        EndPointAsyncTask asyncTask = new EndPointAsyncTask();
        asyncTask.execute(new Pair<Context, String>(this, "ABC"));

        final MainActivityFragment fragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_paid);
        fragment.changeLoadingIndicatorVisibilty(true);
        asyncTask.setJokeResultListner(new EndPointAsyncTask.JokeResultListener() {
            @Override
            public void gotJokeFromApi(String joke) {
                fragment.changeLoadingIndicatorVisibilty(false);
                Intent jokeIntent = new Intent(MainActivity.this, JokeDisplayActivity.class);
                jokeIntent.putExtra(JokeConstants.JOKE_INTENT_EXTRA, joke);
                startActivity(jokeIntent);
            }
        });
    }


}
