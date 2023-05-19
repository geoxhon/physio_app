package com.geoxhonapps.physio_app.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;

import com.geoxhonapps.physio_app.R;

public class AddPatientActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();


        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.action_bar_layout);

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFFFF")));


        setContentView(R.layout.r3);
    }
}