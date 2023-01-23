package com.example.footballapp.di.module;

import com.example.footballapp.service.FootballService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetroModule {
    private final static String BASE_URL = "https://app.sportdataapi.com/";

    @Singleton
    @Provides
    public FootballService getFootballService(Retrofit retrofit) {
        return retrofit.create(FootballService.class);
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}