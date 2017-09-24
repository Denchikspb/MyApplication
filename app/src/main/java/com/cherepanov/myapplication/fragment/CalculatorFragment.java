package com.cherepanov.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cherepanov.myapplication.R;

/**
 * Created by Денис on 22.09.2017.
 */

public class CalculatorFragment extends Fragment {

    private View view;

    public static CalculatorFragment getInstance(){
        Bundle args = new Bundle();
        CalculatorFragment fragment = new CalculatorFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.calculator_layout, container, false);

        return view;
    }
}
