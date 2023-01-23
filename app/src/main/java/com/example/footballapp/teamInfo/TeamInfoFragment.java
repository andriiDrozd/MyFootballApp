package com.example.footballapp.teamInfo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.footballapp.databinding.FragmentTeamInfoBinding;
import com.example.footballapp.di.BaseApplication;
import com.example.footballapp.model.Datum;
import com.example.footballapp.util.Common;

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

        ((BaseApplication) requireActivity().getApplication()).getDaggerComponent()
                .inject(this);

        teamInfoViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TeamInfoViewModel.class);
        getDatum(teamId);
        return teamInfoBinding.getRoot();
    }

    private void getDatum(int teamId) {
        teamInfoViewModel.getTeamInfoDatum(teamId).observe(getViewLifecycleOwner(), datum -> fillView(datum));
    }

    private void fillView(Datum data) {
        teamInfoBinding.setTeam(data);
        teamInfoBinding.setCountry(data.getCountry());
        teamInfoBinding.setImageUrl(data.getLogo());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        if (Common.isInternetConnected(requireContext())) {
            teamInfoViewModel.getError().observe(getViewLifecycleOwner(), (String s) -> {
                Log.e("result", "String s=" + s);
                navController.navigate(TeamInfoFragmentDirections.actionTeamInfoFragmentToErrorFragment(s));
            });
        } else {
            navController.navigate(TeamInfoFragmentDirections.actionTeamInfoFragmentToErrorFragment("No Internet connection"));
        }
    }
}