package com.geoxhonapps.physio_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.geoxhonapps.physio_app.DynamicActivity;
import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.APatientUser;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FLoginResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewAppointmentActivity extends ParentActivity {
    private boolean success;
    private String id,Name,username,email,SSN;
    private int accountType;
    private FLoginResponse userInfo;
    private CalendarView calendarView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r9);
        CalendarView calendarView = findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                FLoginResponse userInfo = new FLoginResponse(success, id, Name, username, accountType, email, SSN);
                APatientUser availableHours = new APatientUser(userInfo);

                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(Calendar.YEAR, year);
                selectedDate.set(Calendar.MONTH, month);
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                ArrayList<Date> listOfHours = availableHours.getAvailableAppointmentsForDate(selectedDate);

                if (listOfHours.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "No available hours. If you want,choose another day.", Toast.LENGTH_SHORT).show();
                } else {
                    openActivity2();
                    StringBuilder hoursText = new StringBuilder("Available Hours:\n");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                    for (Date hour : listOfHours) {
                        String formattedHour = dateFormat.format(hour);
                        hoursText.append(formattedHour).append("\n");
                    }
                    Toast.makeText(getApplicationContext(), hoursText.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

        public void openActivity2() {
            Intent intent = new Intent(this, DynamicActivity.class);
            startActivity(intent);
        }
}


