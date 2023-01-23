package com.example.footballapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private final static String BASE_URL = "https://app.sportdataapi.com/";
    private static Retrofit retrofit = null;

    public static FootballService getService() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(FootballService.class);
    }
}
