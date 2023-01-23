package com.example.footballapp.repositories;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    private final MutableLiveData<Datum> _datums = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private FootballService footballService;

    @Inject
    public Repository(FootballService footballService) {
        this.footballService = footballService;
    }

    public LiveData<Datum> getDatumLiveDataTeamInfo(int teamId) {
        getMutableLiveData(teamId);
        return _datums;
    }

    public void getMutableLiveData(int teamId) {
        Call<FootballTeamInfo> call = footballService.getTeamInfo(teamId, "ba31f060-95a7-11ed-a8cc-1be03dcda28c");
        call.enqueue(new Callback<FootballTeamInfo>() {

            @Override
            public void onResponse(Call<FootballTeamInfo> call,
                                   Response<FootballTeamInfo> response) {
                if (response.isSuccessful()) {
                    Log.e("result", "Error from success"+response.code());
                    FootballTeamInfo footballInfo = response.body();
                    if (footballInfo != null && footballInfo.getData() != null) {
                        _datums.setValue(footballInfo.getData());
                    }
                } else {
                    Log.e("result", "Error from else"+response.code());
                    switch (response.code()) {
                        case 404:
                            errorMessage.setValue("Not found");
                            break;
                        case 500:
                            errorMessage.setValue("Server is broken");
                            break;
                        default:
                            errorMessage.setValue("Unknown Error");
                    }
                    errorMessage.setValue(response.message());
                }

            }

            @Override
            public void onFailure(@NonNull Call<FootballTeamInfo> call, @NonNull Throwable t) {
                Log.e("result", t.toString());
                errorMessage.setValue("Unknown Error");
            }
        });
    }

    public MutableLiveData<List<Datum>> getMutableLiveDataTeamList() {

        Call<FootballInfo> call = footballService.getFootballInfo(Region.GERMANY.getId(), "ba31f060-95a7-11ed-a8cc-1be03dcda28c");

        call.enqueue(new Callback<FootballInfo>() {
            @Override
            public void onResponse(Call<FootballInfo> call, Response<FootballInfo> response) {
                if (response.isSuccessful()) {
                    Log.e("result", "Error from success"+response.code());
                    FootballInfo footballInfo = response.body();
                    if (footballInfo != null && footballInfo.getData() != null) {
                        mutableLiveData.setValue(footballInfo.getData());
                    }

                } else {
                    Log.e("result", "Error from else"+response.code());
                    switch (response.code()) {
                        case 404:
                            errorMessage.setValue("Not found");
                            break;
                        case 500:
                            errorMessage.setValue("Server is broken");
                            break;
                        default:
                            errorMessage.setValue("Unknown Error");
                    }
                    errorMessage.setValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<FootballInfo> call, Throwable t) {
                Log.e("Error", t.toString());
                errorMessage.setValue("Unknown Error");
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<String> getErrorMessage(){
        return errorMessage;
    }
}
