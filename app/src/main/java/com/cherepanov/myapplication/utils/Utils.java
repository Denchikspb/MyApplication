package com.cherepanov.myapplication.utils;

import com.cherepanov.myapplication.api.Link;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Денис on 04.10.2017.
 */

public class Utils {
    public static Link getLink(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();
        return retrofit.create(Link.class);
    }

    public static String getImage(String icon) {
        return String.format("https://openweathermap.org/img/w/%s.png", icon);
    }
}
