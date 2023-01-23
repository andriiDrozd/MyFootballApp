package com.example.footballapp.di.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.footballapp.di.ViewModelKey;
import com.example.footballapp.di.factory.TeamListViewModelFactory;
import com.example.footballapp.teamList.TeamListScreenViewModel;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class TeamListScreenFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(TeamListScreenViewModel.class)
    abstract ViewModel bindViewModel(TeamListScreenViewModel teamListScreenViewModel);

    @Binds
    @Named("TeamListScreenFragment")
    abstract ViewModelProvider.Factory bindFactory(TeamListViewModelFactory factory);

}