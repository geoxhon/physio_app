package com.geoxhonapps.physio_app.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.geoxhonapps.physio_app.ContextFlowUtilities;
import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.ADoctorUser;
import com.geoxhonapps.physio_app.RestUtilities.ARecord;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;
import com.geoxhonapps.physio_app.activities.AddServiceActivity;
import com.geoxhonapps.physio_app.activities.HomeActivity;
import com.geoxhonapps.physio_app.activities.MainActivity;
import com.geoxhonapps.physio_app.activities.ParentActivity;
import com.geoxhonapps.physio_app.activities.RecordAppointmentActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.r10, container, false);
        // Inflate the layout for this fragment
        ArrayList<ARecord> records  = ((ADoctorUser) StaticFunctionUtilities.getUser()).getHistory();




        return rootView;
    }
}