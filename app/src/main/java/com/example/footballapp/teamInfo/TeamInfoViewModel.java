package com.example.footballapp.teamInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.footballapp.model.Datum;
import com.example.footballapp.repositories.Repository;

import javax.inject.Inject;

public class TeamInfoViewModel extends ViewModel {

    Repository repository;

    @Inject
    public TeamInfoViewModel(Repository repository) {
        this.repository = repository;
    }


    public LiveData<Datum> getTeamInfoDatum(int teamId) {
        return repository.getDatumLiveDataTeamInfo(teamId);
    }

    public LiveData<String> getError() {
        return repository.getErrorMessage();
    }
}
