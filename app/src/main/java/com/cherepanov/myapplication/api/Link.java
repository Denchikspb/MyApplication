package com.cherepanov.myapplication.api;

import com.cherepanov.myapplication.model.pojo.translate.TranslateResponse;
import com.cherepanov.myapplication.model.pojo.weather.OpenWeatherMap;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Денис on 03.10.2017.
 */

public interface Link {
    @FormUrlEncoded
    @POST("/api/v1.5/tr.json/translate")
    Call<TranslateResponse> translate(@FieldMap Map<String, String> map);

    @Headers("Content-Type: application/json")
    @GET("/data/2.5/weather")
    Call<OpenWeatherMap> getWeatherDataByCoord(@Query("appid") String appId, @Query("lat") String lat, @Query("lon") String lon, @Query("units") String units);
}
