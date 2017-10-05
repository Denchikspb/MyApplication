package com.cherepanov.myapplication.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import com.cherepanov.myapplication.model.pojo.weather.OpenWeatherMap;
import com.cherepanov.myapplication.utils.Constants;
import com.cherepanov.myapplication.utils.Utils;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Денис on 03.10.2017.
 */

public class WeatherFragment extends Fragment{

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

        rendererWeather();
        return view;
    }

    private void rendererWeather(){
        Location location = getLocation();
        if (location == null) {
            Log.e("TAG", "No location");
        }

        double lat = location.getLatitude();
        double lon = location.getLongitude();
        loadWeather(String.valueOf(lat), String.valueOf(lon));
    }

    private Location getLocation(){
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(new Criteria(), false);

        checkPermission();
        return locationManager.getLastKnownLocation(provider);
    }

    private void checkPermission(){
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 0);
        }
    }

    private void loadWeather(String lat, String lon) {
        Link linkInterface = Utils.getLink(Constants.URL_WEATHER);
        Call<OpenWeatherMap> call = linkInterface.getWeatherDataByCoord(Constants.KEY_WEATHER, lat, lon, Constants.UNITS);
        final ProgressDialog dlg = new ProgressDialog(getContext());
        dlg.setTitle("Загружаются данные о погоде");
        dlg.show();
        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {
                if (response.body() != null) {
                    OpenWeatherMap openWeatherMap = response.body();
                    if (openWeatherMap != null) {
                        currentCityTV.setText(openWeatherMap.getName());
                        DateFormat dateFormat = DateFormat.getDateInstance();
                        String date = dateFormat.format(new Date(openWeatherMap.getDt()));
                        lastUpdateTV.setText(date);
                        detailsWeatherTV.setText(openWeatherMap.getWeather().get(0).getDescription());
                        temperatureTV.setText(String.format("%.2f °C", openWeatherMap.getMain().getTemp()));
                        Picasso.with(getContext())
                                .load(Utils.getImage(openWeatherMap.getWeather().get(0).getIcon()))
                                .into(weatherIconIV);
                    }
                }
                dlg.dismiss();
            }

            @Override
            public void onFailure(Call<OpenWeatherMap> call, Throwable t) {
                Toast.makeText(getContext(), "Не удалось получить информацию", Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });
    }
}
