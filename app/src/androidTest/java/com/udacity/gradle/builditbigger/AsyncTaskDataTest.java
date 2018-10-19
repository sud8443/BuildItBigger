package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static junit.framework.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class AsyncTaskDataTest {

    private Context context;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void checkNonEmptyReturnValue(){
        EndPointAsyncTask asyncTask = new EndPointAsyncTask();
        asyncTask.execute(new android.support.v4.util.Pair<>(context, "Test String"));
        asyncTask.setJokeResultListner(new EndPointAsyncTask.JokeResultListener() {
            @Override
            public void gotJokeFromApi(String joke) {
                assertTrue(joke, joke != null && joke.length() > 0 &&
                        !joke.equals(EndPointAsyncTask.ASYNC_TASK_ERROR));
            }
        });
    }
}
