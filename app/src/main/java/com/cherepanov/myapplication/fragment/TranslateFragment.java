package com.cherepanov.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cherepanov.myapplication.R;

/**
 * Created by Денис on 02.10.2017.
 */

public class TranslateFragment extends Fragment {

    private View view;
    private EditText translateEdt;
    private EditText resultEdt;
    private Button translateBtn;

    public static TranslateFragment getInstance() {
        Bundle args = new Bundle();
        TranslateFragment fragment = new TranslateFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.translate_layout, container, false);
        translateEdt = (EditText) view.findViewById(R.id.translate_text);
        resultEdt = (EditText) view.findViewById(R.id.result_text);
        translateBtn = (Button) view.findViewById(R.id.translate_btn);

        return view;
    }
}