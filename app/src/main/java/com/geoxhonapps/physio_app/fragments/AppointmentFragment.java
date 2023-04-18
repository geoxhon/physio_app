package com.geoxhonapps.physio_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geoxhonapps.physio_app.ContextFlowUtilities;
import com.geoxhonapps.physio_app.activities.NewAppointmentActivity;
import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.EUserType;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentFragment extends Fragment {


    public AppointmentFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.r7, container, false);
        FloatingActionButton bottomButton = rootView.findViewById(R.id.floatingActionButton);
        if(StaticFunctionUtilities.getUser().getAccountType()!=EUserType.Patient){
            bottomButton.hide();
        }
        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContextFlowUtilities.moveTo(NewAppointmentActivity.class, true);
            }
        });
        return rootView;
    }
}