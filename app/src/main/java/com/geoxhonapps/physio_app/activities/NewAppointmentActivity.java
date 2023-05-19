package com.geoxhonapps.physio_app.activities;

import static java.util.Calendar.DATE;
import static java.util.Calendar.DAY_OF_MONTH;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.APatientUser;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FLoginResponse;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewAppointmentActivity extends ParentActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r9);
    }
}


