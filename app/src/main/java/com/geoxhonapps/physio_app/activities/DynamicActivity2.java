package com.geoxhonapps.physio_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.geoxhonapps.physio_app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DynamicActivity2 extends ParentActivity {
    private String formattedHour, selectedHour;
    private List<String> formattedHours = new ArrayList<>();

    private Date thiselectedate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic2);

        Intent intent = getIntent();

        ArrayList<Date> listOfHours;
        listOfHours = (ArrayList<Date>) intent.getSerializableExtra("listofhours");


        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

        for (Date hour : listOfHours) {
            formattedHour = hourFormat.format(hour);
            formattedHours.add(formattedHour);
        }

        LinearLayout buttonContainer = findViewById(R.id.buttonContainer);

       /* for (String hour : formattedHours) {
            Button button = new Button(this);
            button.setText(hour);

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    selectedHour = hour;
                    //openActivity3(selectedHour,count,thiselectedDate2);
                    openActivity3(parsedDate);
                }
            });
            buttonContainer.addView(button);
        }

        */

        for (int i = 0; i < formattedHours.size(); i++) {
            final String hour = formattedHours.get(i);
            Button button = new Button(this);
            button.setText(hour);
            button.setTag(i); // Set a unique tag for each button

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag(); // Get the position from the button's tag
                    //selectedHour = formattedHours.get(position); // Use the position to retrieve the corresponding hour
                    thiselectedate = listOfHours.get(position);
                    openActivity3(thiselectedate);
                }
            });
            buttonContainer.addView(button);
        }
      }
        public void openActivity3 (Date thiselectedate){
            Intent intent = new Intent(this, NewAppointmentActivity2.class);
            intent.putExtra("thiselectedate", thiselectedate);
            startActivity(intent);
        }
    }
