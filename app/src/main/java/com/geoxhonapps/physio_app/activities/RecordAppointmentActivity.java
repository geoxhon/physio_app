package com.geoxhonapps.physio_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.geoxhonapps.physio_app.ContextFlowUtilities;
import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.AAppointment;

public class RecordAppointmentActivity extends ParentActivity {
    private AAppointment selectedAppointment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ContextFlowUtilities.getPassedObject()!=null){
            selectedAppointment = (AAppointment)ContextFlowUtilities.getPassedObject();
        }
        setContentView(R.layout.r8);
    }
}