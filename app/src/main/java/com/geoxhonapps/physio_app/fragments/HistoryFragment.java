package com.geoxhonapps.physio_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geoxhonapps.physio_app.R;

/**
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.r10, container, false);
        // Inflate the layout for this fragment
        return rootView;
    }
}