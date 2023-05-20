package com.geoxhonapps.physio_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.geoxhonapps.physio_app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DynamicActivity2 extends AppCompatActivity {
    private TextView textView;
    private EditText selectedhour;

    private String thishour,thisSelectedhourString;
    private Button ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic2);

        textView = findViewById(R.id.hours);


        ArrayList<Date> listOfHours  = (ArrayList<Date>) getIntent().getSerializableExtra("listofhours");

        StringBuilder hoursText = new StringBuilder();
        for (Date hour : listOfHours) {
            hoursText.append(hour).append("\n");
        }
        textView.setText(hoursText.toString());
        ok = findViewById(R.id.ok);
        selectedhour = findViewById(R.id.selectedhour2);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thishour = selectedhour.getText().toString();
                boolean containsHour = false;
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");


                for (Date hour : listOfHours) {
                    String hourString = dateFormat.format(hour);
                    if (thishour.equals(hourString)) {
                        containsHour = true;
                        thisSelectedhourString = hourString;
                        break;
                    }
                }
                if (containsHour) {
                    openActivity3(thisSelectedhourString);
                } else {
                    Toast.makeText(getApplicationContext(), "Δεν υπάρχει αυτή η ώρα", Toast.LENGTH_LONG).show();
                }
            };
        });
    }
    public void openActivity3(String thisSelectedhourString ) {
        Intent intent = new Intent(this, NewAppointmentActivity2.class);
        intent.putExtra("thiselectedhour", thisSelectedhourString);
        startActivity(intent);
    }
}