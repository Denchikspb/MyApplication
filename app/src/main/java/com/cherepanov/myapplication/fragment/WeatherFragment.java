package com.cherepanov.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cherepanov.myapplication.R;
import com.cherepanov.myapplication.api.Link;
import com.cherepanov.myapplication.api.pojo.weather.OpenWeatherMap;
import com.cherepanov.myapplication.utils.Constants;
import com.cherepanov.myapplication.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Денис on 03.10.2017.
 */

public class WeatherFragment extends Fragment {

    private View view;
    private TextView currentCityTV;
    private TextView lastUpdateTV;
    private ImageView weatherIconIV;
    private TextView detailsWeatherTV;
    private TextView temperatureTV;

    public static WeatherFragment getInstance() {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.weather_layout, container, false);
        currentCityTV = (TextView) view.findViewById(R.id.city_field);
        lastUpdateTV = (TextView) view.findViewById(R.id.updated_field);
        detailsWeatherTV = (TextView) view.findViewById(R.id.details_field);
        temperatureTV = (TextView) view.findViewById(R.id.current_temperature_field);
        weatherIconIV = (ImageView) view.findViewById(R.id.weather_icon);

        loadWeather();
        return view;
    }

    private void loadWeather() {
        Link linkInterface = Utils.getLink(Constants.URL_WEATHER);
        Call<OpenWeatherMap> call = linkInterface.getWeatherData(Constants.KEY_WEATHER);
        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {
                Toast.makeText(getContext(), "Yeah beach!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<OpenWeatherMap> call, Throwable t) {
                Toast.makeText(getContext(), "Fail, beach..", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
