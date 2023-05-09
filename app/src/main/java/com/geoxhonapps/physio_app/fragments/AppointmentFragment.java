package com.geoxhonapps.physio_app.fragments;

import static android.view.View.INVISIBLE;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.geoxhonapps.physio_app.ContextFlowUtilities;
import com.geoxhonapps.physio_app.RestUtilities.AAppointment;
import com.geoxhonapps.physio_app.RestUtilities.ADoctorUser;
import com.geoxhonapps.physio_app.RestUtilities.AManagerUser;
import com.geoxhonapps.physio_app.RestUtilities.APatientUser;
import com.geoxhonapps.physio_app.RestUtilities.EAppointmentStatus;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FLoginResponse;
import com.geoxhonapps.physio_app.activities.HomeActivity;
import com.geoxhonapps.physio_app.activities.NewAppointmentActivity;
import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.EUserType;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

class AppointmentViewHandler {
    private AAppointment appointment;
    private View view;
    public AppointmentViewHandler(AAppointment appointment, LinearLayout linearLayout){
        this.appointment = appointment;
        LayoutInflater scrollInflater = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            scrollInflater = (LayoutInflater) ContextFlowUtilities.getCurrentView().getSystemService(ContextFlowUtilities.getCurrentView().LAYOUT_INFLATER_SERVICE);
            this.view = scrollInflater.inflate(R.layout.r7_card_layout, linearLayout);
            ((TextView)this.view.findViewById(R.id.appointmentIdText)).setText("#"+appointment.getAppointmentId());
            ((TextView)this.view.findViewById(R.id.patientName)).setText(appointment.getAssociatedUser().getDisplayName());
            ((TextView)this.view.findViewById(R.id.dateText)).setText(appointment.getGlobalDateString());
            prepareView();
            this.view.findViewById(R.id.confirmButton).setOnClickListener(buttonHandler);
            this.view.findViewById(R.id.cancelButton).setOnClickListener(buttonHandler);
            this.view.findViewById(R.id.recordButton).setOnClickListener(buttonHandler);
        }
    }

    public View getView() {
        return view;
    }
    public void prepareView(){
        if(appointment.getStatus() == EAppointmentStatus.Confirmed){
            this.view.findViewById(R.id.confirmButton).setVisibility(View.GONE);
            this.view.findViewById(R.id.recordButton).setVisibility(View.VISIBLE);
            ((TextView)this.view.findViewById(R.id.appointmentStatus)).setText("Eνεργό");
            this.view.findViewById(R.id.statusCardView).setBackgroundColor(Color.parseColor("#56FFB8"));
        }else if(appointment.getStatus() == EAppointmentStatus.Pending){
            this.view.findViewById(R.id.confirmButton).setVisibility(View.VISIBLE);
            ((TextView)this.view.findViewById(R.id.appointmentStatus)).setText("Eν Αναμονή");
            this.view.findViewById(R.id.statusCardView).setBackgroundColor(Color.parseColor("#FFDA56"));
        }else if(appointment.getStatus() == EAppointmentStatus.Cancelled){
            this.view.findViewById(R.id.confirmButton).setVisibility(View.GONE);
            this.view.findViewById(R.id.cancelButton).setVisibility(View.GONE);
            this.view.findViewById(R.id.recordButton).setVisibility(View.GONE);
            ((TextView)this.view.findViewById(R.id.appointmentStatus)).setText("Ακυρωμένο");
            this.view.findViewById(R.id.statusCardView).setBackgroundColor(Color.parseColor("#FF5656"));
        }
    }
    public void ASYNC_prepareView(){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                prepareView();
                view.invalidate();
            }
        });
    }
    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener buttonHandler = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId() /*to get clicked view id**/) {
                case R.id.confirmButton:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if(appointment.Accept()) {
                                ASYNC_prepareView();
                            }
                        }
                    }).start();
                    break;
                case R.id.cancelButton:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if(appointment.Cancel()) {

                                ASYNC_prepareView();
                            }
                        }
                    }).start();
                    break;
                case R.id.recordButton:
                    break;
                default:
                    break;
            }
        }
    };

}

public class AppointmentFragment extends Fragment {

    private View rootView;
    public AppointmentFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.r7, container, false);
        populateScrollView();
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
    public void populateScrollView(){
        LinearLayout linearLayout = rootView.findViewById(R.id.appointmentLinearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ArrayList<AAppointment> appointments = ((ADoctorUser)StaticFunctionUtilities.getUser()).getAppointments(false);
        for(AAppointment appointment: appointments){
            new AppointmentViewHandler(appointment, linearLayout);
        }
    }
}