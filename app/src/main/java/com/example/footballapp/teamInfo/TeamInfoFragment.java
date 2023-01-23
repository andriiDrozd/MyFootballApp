package com.example.footballapp.teamInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.footballapp.databinding.FragmentTeamInfoBinding;
import com.example.footballapp.di.BaseApplication;

import com.example.footballapp.model.Datum;

import javax.inject.Inject;
import javax.inject.Named;

public class TeamInfoFragment extends Fragment {

    @Inject
    @Named("TeamInfoFragment")
    ViewModelProvider.Factory viewModelFactory;

    private TeamInfoViewModel teamInfoViewModel;
    private FragmentTeamInfoBinding teamInfoBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int teamId = TeamInfoFragmentArgs.fromBundle(getArguments()).getTeamId();
        teamInfoBinding = FragmentTeamInfoBinding.inflate(inflater);

        ((BaseApplication) getActivity().getApplication()).getDaggerComponent()
                .inject(this);
        teamInfoViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TeamInfoViewModel.class);
        getDatum(teamId);

        return teamInfoBinding.getRoot();
    }

    // TODO: 1/22/2023 remove string
    private void getDatum(int teamId) {
        teamInfoViewModel.getTeamInfoDatum(teamId).observe(getViewLifecycleOwner(), datum -> fillView(datum));
    }

    private void fillView(Datum data) {
        teamInfoBinding.setTeam(data);
        teamInfoBinding.setCountry(data.getCountry());
        teamInfoBinding.setImageUrl(data.getLogo());
    }


}