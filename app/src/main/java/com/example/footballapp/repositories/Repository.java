package com.example.footballapp.repositories;

import android.util.Log;
import com.example.footballapp.BuildConfig;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.footballapp.BuildConfig;
import com.example.footballapp.model.Datum;
import com.example.footballapp.model.FootballInfo;
import com.example.footballapp.model.FootballTeamInfo;
import com.example.footballapp.service.FootballService;
import com.example.footballapp.util.Region;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private final MutableLiveData<List<Datum>> mutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Datum> data = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private FootballService footballService;

    @Inject
    public Repository(FootballService footballService) {
        this.footballService = footballService;
    }

    public LiveData<Datum> getDatumLiveDataTeamInfo(int teamId) {
        getMutableLiveDataTeamInfo(teamId);
        return data;
    }

    public void getMutableLiveDataTeamInfo(int teamId) {
        Call<FootballTeamInfo> call = footballService.getTeamInfo(teamId, BuildConfig.API_KEY);
        call.enqueue(new Callback<FootballTeamInfo>() {

            @Override
            public void onResponse(Call<FootballTeamInfo> call,
                                   Response<FootballTeamInfo> response) {
                if (response.isSuccessful()) {
                    Log.e("result", "Error from success" + response.code());
                    FootballTeamInfo footballInfo = response.body();
                    if (footballInfo != null && footballInfo.getData() != null) {
                        data.setValue(footballInfo.getData());
                    }
                } else {
                    errorCheckFootballInfo(response);
                    Log.e("result", "Error from else" + response.code());

                }
            }

            @Override
            public void onFailure(@NonNull Call<FootballTeamInfo> call, @NonNull Throwable t) {
                errorMessage.setValue("Unknown Error");
                Log.e("error", t.toString());
            }
        });
    }

    public MutableLiveData<List<Datum>> getMutableLiveDataTeamList() {

        Call<FootballInfo> call = footballService.getFootballInfo(Region.GERMANY.getId(), BuildConfig.API_KEY);

        call.enqueue(new Callback<FootballInfo>() {
            @Override
            public void onResponse(Call<FootballInfo> call, Response<FootballInfo> response) {
                if (response.isSuccessful()) {
                    FootballInfo footballInfo = response.body();
                    if (footballInfo != null && footballInfo.getData() != null) {
                        mutableLiveData.setValue(footballInfo.getData());
                    }
                } else {
                    errorCheckFootballInfo(response);
                    Log.e("error", "Error from else" + response.code());
                }
            }

            @Override
            public void onFailure(Call<FootballInfo> call, Throwable t) {
                errorMessage.postValue("Unknown Error");
                Log.e("error", t.toString());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    private void errorCheckFootballInfo(Response<?> response) {
        switch (response.code()) {
            case 404:
                errorMessage.postValue("Not found");
                Log.e("error", "Error: " + response.code());
                break;
            case 500:
                errorMessage.postValue("Server is broken");
                Log.e("error", "Error: " + response.code());
                break;
            default:
                errorMessage.postValue("Unknown Error");
                Log.e("error", "Error: " + response.code());
        }
    }
}
