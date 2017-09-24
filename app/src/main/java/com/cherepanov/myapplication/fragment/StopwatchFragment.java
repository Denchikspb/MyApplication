package com.cherepanov.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cherepanov.myapplication.R;

/**
 * Created by Денис on 21.09.2017.
 */

public class StopwatchFragment extends Fragment {

    private View view;

    public static StopwatchFragment getInstance(){
        Bundle args = new Bundle();
        StopwatchFragment fragment = new StopwatchFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stopwatch_fragment, container, false);
        return view;
    }


}
