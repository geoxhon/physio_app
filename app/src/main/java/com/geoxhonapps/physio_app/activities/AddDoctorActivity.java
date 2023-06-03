package com.geoxhonapps.physio_app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.geoxhonapps.physio_app.ContextFlowUtilities;
import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.AManagerUser;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;
import com.google.android.material.textfield.TextInputEditText;

public class AddDoctorActivity extends ParentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r1);
        Button button =  findViewById(R.id.SaveButton);

        getSupportActionBar().hide();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean alldata = true;

                TextInputEditText TextInput = findViewById(R.id.NameText);
                // change toast to context flow utility alert

                String name  =  TextInput.getText().toString();
                if(name.isEmpty()){
                    ContextFlowUtilities.presentAlert("Αποτυχία", "Δεν υπάρχει όνομα");
                    alldata = false;
                }

                TextInput = findViewById(R.id.Address); //is not used
                String address  =  TextInput.getText().toString();
                if(address.isEmpty()){
                    ContextFlowUtilities.presentAlert("Αποτυχία", "Δεν υπάρχει διεύθυνση");
                    alldata = false;
                }
                TextInput = findViewById(R.id.AFM);
                String AFM  =  TextInput.getText().toString();
                if(AFM.isEmpty()){
                    ContextFlowUtilities.presentAlert("Αποτυχία", "Δεν υπάρχει ΑΦΜ");
                    alldata = false;
                }
                TextInput = findViewById(R.id.UsernameText);
                String username  =  TextInput.getText().toString();
                if(username.isEmpty()){
                    ContextFlowUtilities.presentAlert("Αποτυχία", "Δεν υπάρχει όνομα χρήστη");
                    alldata = false;
                }
                TextInput = findViewById(R.id.PasswordText);
                String password  =  TextInput.getText().toString();
                if(password.isEmpty()){
                    ContextFlowUtilities.presentAlert("Αποτυχία", "Δεν υπάρχει κωδικός");
                    alldata = false;
                }
                TextInput =findViewById(R.id.EmailText);
                String email  =  TextInput.getText().toString();
                if(email.isEmpty()){
                    ContextFlowUtilities.presentAlert("Αποτυχία", "Δεν υπάρχει email");
                    alldata = false;
                }
                if(alldata){ //if we have all the data, we can create a new thread and connect to the data base
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            if( ((AManagerUser) StaticFunctionUtilities.getUser()).createDoctor(username,password,name,email,AFM)){//send data to base, returns boolean
                                ContextFlowUtilities.presentAlert("Επιτυχία", "Η καταχώρηση ολοκληρώθηκε με επιτυχία.");
                            }

                        }
                    }).start();

                }




            }
        });






    }
}