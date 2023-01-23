package com.example.footballapp.error;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.footballapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ErrorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ErrorFragment extends Fragment {
private TextView errorMessageTextView;
    public static ErrorFragment newInstance() {
        ErrorFragment fragment = new ErrorFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        errorMessageTextView =getActivity().findViewById(R.id.errorMessageTextView);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String errorMessage = ErrorFragmentArgs.fromBundle(getArguments()).getErrorMessage();
        errorMessageTextView.setText(errorMessage);
        return inflater.inflate(R.layout.fragment_error, container, false);
    }
}