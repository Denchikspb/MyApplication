package com.cherepanov.myapplication.api;

import com.cherepanov.myapplication.api.pojo.TranslateResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Денис on 03.10.2017.
 */

public interface Link {
    @FormUrlEncoded
    @POST("/api/v1.5/tr.json/translate")
    Call<TranslateResponse> translate(@FieldMap Map<String, String> map);
}
