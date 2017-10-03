package com.cherepanov.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cherepanov.myapplication.R;
import com.cherepanov.myapplication.api.Link;
import com.cherepanov.myapplication.api.pojo.TranslateResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Денис on 02.10.2017.
 */

public class TranslateFragment extends Fragment {

    private View view;
    private EditText translateEdt;
    private EditText resultEdt;
    private Button translateBtn;
    private ImageButton changeLangBtn;
    private TextView firstLangTV;
    private TextView secondLangTV;
    private ProgressBar progressBar;

    private static final String URL = "https://translate.yandex.net";
    private static final String KEY = "trnsl.1.1.20171002T214757Z.748035dae7a6438f.e9a3393b653b5db11ec11c991e36672bee53180e";

    private static final String EN_RU = "en-ru";
    private static final String RU_EN = "ru-en";

    private static int lang = 0;

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL)
            .build();

    private Link linkInterface = retrofit.create(Link.class);

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
        changeLangBtn = (ImageButton) view.findViewById(R.id.translate_change_lang_btn);
        firstLangTV = (TextView) view.findViewById(R.id.translate_lang_first);
        secondLangTV = (TextView) view.findViewById(R.id.translate_lang_second);
        progressBar = (ProgressBar) view.findViewById(R.id.translate_progress_bar);

        setupListener();
        return view;
    }

    private void setupListener() {
        progressBar.setVisibility(View.GONE);
        changeLangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lang == 0){
                    firstLangTV.setText(R.string.translate_second_lang);
                    secondLangTV.setText(R.string.translate_first_lang);
                    lang = 1;
                } else {
                    firstLangTV.setText(R.string.translate_first_lang);
                    secondLangTV.setText(R.string.translate_second_lang);
                    lang = 0;
                }
            }
        });
        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Map<String, String> jsonMap = new HashMap<String, String>();
                jsonMap.put("key", KEY);
                jsonMap.put("text", translateEdt.getText().toString());
                jsonMap.put("lang", lang == 0 ? EN_RU : RU_EN);
                Call<TranslateResponse> call = linkInterface.translate(jsonMap);
                progressBar.setVisibility(View.VISIBLE);
                call.enqueue(new Callback<TranslateResponse>() {
                    @Override
                    public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                        if (response.body() != null && response.body().getText() != null){
                            StringBuilder sb = new StringBuilder();
                            for (String iter: response.body().getText()){
                                sb.append(iter);
                            }
                            resultEdt.setText(sb.toString());
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<TranslateResponse> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Не удалось перевести текст", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}