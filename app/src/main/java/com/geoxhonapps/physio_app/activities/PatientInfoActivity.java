package com.geoxhonapps.physio_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.geoxhonapps.physio_app.ContextFlowUtilities;
import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.APatient;

public class PatientInfoActivity extends ParentActivity {
    private APatient selectedPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r4);
        this.selectedPatient = (APatient) ContextFlowUtilities.getPassedObject();
    }
}