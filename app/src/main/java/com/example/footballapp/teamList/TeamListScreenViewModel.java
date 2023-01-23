package com.example.footballapp.teamList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.footballapp.model.Datum;
import com.example.footballapp.repositories.Repository;

import java.util.List;

import javax.inject.Inject;

public class TeamListScreenViewModel extends ViewModel {

    Repository repository;

    @Inject
    public TeamListScreenViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Datum>> getAllTeamsDatum() {
        return repository.getMutableLiveDataTeamList();
    }

    public LiveData<String> getError(){
        return repository.getErrorMessage();
    }
}
