package com.geoxhonapps.physio_app.activities;

import static java.util.Calendar.DAY_OF_MONTH;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.APatientUser;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FLoginResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NewAppointmentActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r9);
    }

    public NewAppointmentActivity(FLoginResponse userInfo){
           /APatientUser hours = new APatientUser(userInfo);
            ArrayList<Date> availablehours;


            EditText time = null;
            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));
            String month = String.valueOf(date.get(Calendar.MONTH));
            String day = String.valueOf(date.get(DAY_OF_MONTH));

#
            availablehours = hours.getAvailableAppointmentsForDate(date);
            time.setText((CharSequence) availablehours);
    }

}