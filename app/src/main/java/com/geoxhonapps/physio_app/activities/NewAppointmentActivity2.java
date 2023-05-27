package com.geoxhonapps.physio_app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.APatientUser;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewAppointmentActivity2 extends ParentActivity {
private Button button;
private boolean flag;

private  Date thiselectedHour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        button = findViewById(R.id.btn2);

        APatientUser user = (APatientUser) StaticFunctionUtilities.getUser();

        Date thiselectedate = (Date) getIntent().getSerializableExtra("thiselectedate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(thiselectedate);
        Toast.makeText(getApplicationContext(), dateString, Toast.LENGTH_LONG).show();

       flag = user.bookAppointment(thiselectedate);

       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    Toast.makeText(getApplicationContext(), "Το ραντεβού σας αποθηκεύτηκε", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Δεν αποθηκεύτηκε", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}