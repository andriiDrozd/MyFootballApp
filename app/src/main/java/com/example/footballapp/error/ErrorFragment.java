package com.example.footballapp.error;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.footballapp.R;
import com.example.footballapp.util.Common;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class ErrorFragment extends Fragment {
private MaterialTextView errorMessageTextView;
private MaterialButton retryInternetCheckButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_error, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController= Navigation.findNavController(view);
        errorMessageTextView = view.findViewById(R.id.errorMessageTextView1);
        errorMessageTextView.setText(ErrorFragmentArgs.fromBundle(getArguments()).getErrorMessage());
        retryInternetCheckButton =view.findViewById(R.id.buttonRetryInternetCheck);
        retryInternetCheckButton.setOnClickListener(view1 -> {
            if(Common.isInternetConnected(requireContext())){
                navController.navigate(ErrorFragmentDirections.actionErrorFragmentToTeamListScreenFragment());
            }
        });
    }
}