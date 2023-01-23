package com.example.footballapp.teamList;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballapp.adapter.FootballInfoListAdapter;
import com.example.footballapp.databinding.FragmentTemListBinding;
import com.example.footballapp.di.BaseApplication;
import com.example.footballapp.model.Datum;
import com.example.footballapp.util.Common;
import com.example.footballapp.util.RecyclerViewClickInterface;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;


public class TeamListScreenFragment extends Fragment implements RecyclerViewClickInterface
//        , NetworkCallback
{
    @Inject
    @Named("TeamListScreenFragment")
    ViewModelProvider.Factory viewModelFactory;

    private TeamListScreenViewModel teamListScreenViewModel;
    private FragmentTemListBinding fragmentTemListBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTemListBinding = FragmentTemListBinding.inflate(inflater);
        ((BaseApplication) requireActivity().getApplication()).getDaggerComponent()
                .inject(this);
        teamListScreenViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TeamListScreenViewModel.class);

        return fragmentTemListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        if (Common.isInternetConnected(requireContext())) {
            teamListScreenViewModel.getAllTeamsDatum().observe(getViewLifecycleOwner(), data -> fillRecyclerView(data));

            teamListScreenViewModel.getError().observe(getViewLifecycleOwner(), (String s) -> {
                Log.e("result", "String s=" + s);
                navController.navigate(TeamListScreenFragmentDirections.actionTeamListScreenFragmentToErrorFragment(s));
            });
        } else {
            navController.navigate(TeamListScreenFragmentDirections.actionTeamListScreenFragmentToErrorFragment("No Internet connection"));
        }
    }

    private void fillRecyclerView(List<Datum> datumArrayList) {
        FootballInfoListAdapter adapter = new FootballInfoListAdapter(Datum.itemCallback, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        fragmentTemListBinding.recyclerView.setLayoutManager(layoutManager);
        fragmentTemListBinding.recyclerView.setAdapter(adapter);
        adapter.submitList(datumArrayList);
    }

    @Override
    public void ontTeamClicked(int teamId, View view) {
        NavController navController = Navigation.findNavController(view);
        navController.navigate(TeamListScreenFragmentDirections.actionTeamListScreenFragmentToTeamInfoFragment(teamId));
    }
}