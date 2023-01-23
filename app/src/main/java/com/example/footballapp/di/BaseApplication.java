package com.example.footballapp.di;

import android.app.Application;

import com.example.footballapp.di.module.RetroModule;

public class BaseApplication extends Application {

    private DaggerComponent daggerComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        daggerComponent =
                DaggerDaggerComponent.builder()
                .retroModule(new RetroModule())
                .build();
    }

    public DaggerComponent getDaggerComponent(){
        return daggerComponent;
    }
}