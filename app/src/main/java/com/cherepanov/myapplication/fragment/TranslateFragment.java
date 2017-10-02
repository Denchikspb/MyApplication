package com.cherepanov.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cherepanov.myapplication.R;
import com.cherepanov.myapplication.api.Link;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
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

    private static final String URL = "https://translate.yandex.net";
    private static final String KEY = "trnsl.1.1.20171002T214757Z.748035dae7a6438f.e9a3393b653b5db11ec11c991e36672bee53180e";

    private static final String EN_RU = "en-ru";
    private static final String RU_EN = "ru-en";

    private Gson gson = new GsonBuilder().create();
    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(URL)
            .build();

    private Link linkInterface = retrofit.create(Link.class);
    Handler handler = new Handler(Looper.getMainLooper());

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

        setupListener();
        return view;
    }

    private void setupListener() {
        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Map<String, String> jsonMap = new HashMap<String, String>();
                jsonMap.put("key", KEY);
                jsonMap.put("text", translateEdt.getText().toString());
                jsonMap.put("lang", EN_RU);

                final Call<Object> call = linkInterface.translate(jsonMap);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Response response = call.execute();
                            if (response != null && response.body() != null) {
                                Map<String, String> map = gson.fromJson(response.body().toString(), Map.class);

                                for (Map.Entry e : map.entrySet()) {
                                    if (e.getKey().equals("text")) {
                                        resultEdt.setText(e.getValue().toString());
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
//
//    Runnable run = new Runnable() {
//        @Override
//        public void run() {
//            handler
//        }
//    };
//
//    class Task extends AsyncTask<String, String, Void>{
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            return null;
//        }
//    }
}