package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndPointAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi api = null;
    private Context context;
    private JokeResultListener listener;
    public static final String ASYNC_TASK_ERROR = "ASYNC_TASK_FAILED";

    public void setJokeResultListner(JokeResultListener listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... pairs) {
        if (api == null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            api = builder.build();
        }

        context = pairs[0].first;
        String name = pairs[0].second;

        try {
           return api.sayHi(name).execute().getData();
        } catch (IOException e) {
            return ASYNC_TASK_ERROR;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        listener.gotJokeFromApi(s);
        Log.v("EndPointAsyncTask", "joke correctly fetched: " + s);
    }

    public interface JokeResultListener {
        void gotJokeFromApi(String joke);
    }
}