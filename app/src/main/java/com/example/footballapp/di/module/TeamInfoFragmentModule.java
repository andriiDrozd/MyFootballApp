package com.example.footballapp.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.footballapp.di.ViewModelKey;
import com.example.footballapp.di.factory.TeamInfoViewModelFactory;
import com.example.footballapp.teamInfo.TeamInfoViewModel;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class TeamInfoFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(TeamInfoViewModel.class)
    abstract ViewModel bindViewModel(TeamInfoViewModel teamInfoViewModel);

    @Binds
    @Named("TeamInfoFragment")
    abstract ViewModelProvider.Factory bindFactory(TeamInfoViewModelFactory factory);

}