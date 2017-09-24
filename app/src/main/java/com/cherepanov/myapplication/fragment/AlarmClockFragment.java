package com.cherepanov.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cherepanov.myapplication.R;

/**
 * Created by Денис on 18.09.2017.
 */

public class AlarmClockFragment extends Fragment {

    private View view;

    public static AlarmClockFragment getInstance() {
        Bundle args = new Bundle();
        AlarmClockFragment fragment = new AlarmClockFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.alarm_clock_fragment, container, false);

        return view;
    }

}
