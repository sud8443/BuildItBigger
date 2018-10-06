package com.udacity.gradle.builditbigger.paid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.gradle.builditbigger.R;
import android.widget.LinearLayout;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private LinearLayout loadingIndicator;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        loadingIndicator = root.findViewById(R.id.ll_loading_indicator);
        return root;
    }

    public void changeLoadingIndicatorVisibilty(boolean visiblity) {
        if(visiblity)
            loadingIndicator.setVisibility(View.VISIBLE);
        else
            loadingIndicator.setVisibility(View.INVISIBLE);
    }
}
