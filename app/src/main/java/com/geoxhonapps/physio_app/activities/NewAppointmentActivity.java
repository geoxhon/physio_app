package com.geoxhonapps.physio_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.APatientUser;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;

import java.util.ArrayList;
import java.util.Date;

public class NewAppointmentActivity extends ParentActivity {
    private CalendarView calendarView;
    private Button button1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r9);
        CalendarView calendarView = findViewById(R.id.calendar);
        button1 = findViewById(R.id.btn);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                APatientUser user = (APatientUser) StaticFunctionUtilities.getUser();
                String selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                ArrayList<Date> listOfHours = user.getAvailableAppointmentsForDate(selectedDate);

                if (listOfHours.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "No available hours. If you want,choose another day.", Toast.LENGTH_SHORT).show();
                } else {
                    openActivity2(listOfHours);
                }

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Δεν γίνεται αποθήκευση γιατί δεν επέλεξες ημερομηνία!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

        public void openActivity2(ArrayList<Date> listOfHours) {
            Intent intent = new Intent(this, DynamicActivity2.class);
            intent.putExtra("listofhours", listOfHours);
            startActivity(intent);
        }
}


