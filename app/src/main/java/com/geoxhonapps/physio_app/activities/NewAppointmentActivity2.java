package com.geoxhonapps.physio_app.activities;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.APatientUser;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class NewAppointmentActivity2 extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean flag = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        APatientUser user = (APatientUser) StaticFunctionUtilities.getUser();
        String thisSelectedhourString = (String) getIntent().getSerializableExtra("thiselectedhour");
        SimpleDateFormat hourFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date selectedHour;
        try {
            selectedHour = hourFormat.parse(thisSelectedhourString);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        flag = user.bookAppointment(selectedHour);
        if (flag) {
            Toast.makeText(getApplicationContext(), "Αποθηκεύτηκε το ραντεβού σας.Σας περιμένουμε!", Toast.LENGTH_LONG).show();
        }
    }
}