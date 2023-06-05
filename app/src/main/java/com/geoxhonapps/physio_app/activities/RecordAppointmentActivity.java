package com.geoxhonapps.physio_app.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.geoxhonapps.physio_app.ContextFlowUtilities;
import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.AAppointment;
import com.geoxhonapps.physio_app.RestUtilities.AManagerUser;
import com.geoxhonapps.physio_app.RestUtilities.AService;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;

public class RecordAppointmentActivity extends ParentActivity {
    private AAppointment selectedAppointment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        if(ContextFlowUtilities.getPassedObject()!=null){
            selectedAppointment = (AAppointment)ContextFlowUtilities.getPassedObject();
        }
        setContentView(R.layout.r8);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        for(AService service: StaticFunctionUtilities.getUser().getServices(false)){
            adapter.add(service.getName());
        }
        Button btn = findViewById(R.id.saveButtonR8);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = (TextView)spinner.getSelectedView();
                AService serviceUsed = (AService) textView.getText();

                //TextView t = (TextView)findViewById(R.id.leptomeries);
                String details = "";
                details = "";//t.getText().toString();

                selectedAppointment.recordAppointment(serviceUsed,details);

                if (selectedAppointment.equals(null)){
                    Toast.makeText(getApplicationContext(),"Υπήρξε σφάλμα στην αποθηκευση", Toast.LENGTH_LONG).show();
                }

                Toast.makeText(getApplicationContext(),"Επιτυχία Αποθήκευσης", Toast.LENGTH_LONG).show();

            }
        });
    }


}