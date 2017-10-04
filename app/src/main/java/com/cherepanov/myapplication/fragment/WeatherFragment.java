package com.cherepanov.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cherepanov.myapplication.R;

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

        return view;
    }
}
