package com.example.footballapp.di;

import com.example.footballapp.di.module.RetroModule;
import com.example.footballapp.di.module.TeamInfoFragmentModule;
import com.example.footballapp.di.module.TeamListScreenFragmentModule;
import com.example.footballapp.repositories.Repository;
import com.example.footballapp.teamInfo.TeamInfoFragment;
import com.example.footballapp.teamList.TeamListScreenFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetroModule.class, TeamListScreenFragmentModule.class, TeamInfoFragmentModule.class})
public interface DaggerComponent {
    void inject(Repository repository);

    void inject(TeamListScreenFragment teamListScreenFragment);

    void inject(TeamInfoFragment teamInfoFragment);
}