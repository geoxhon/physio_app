package com.geoxhonapps.physio_app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geoxhonapps.physio_app.ContextFlowUtilities;
import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.ADoctorUser;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;

public class AddPatientActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r3);
        Button B=findViewById(R.id.buttonr3);

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = findViewById(R.id.usernamer3);
                String a1 = username.getText().toString();
                EditText password = findViewById(R.id.passwordr3);
                String a2 = password.getText().toString();
                EditText name = findViewById(R.id.namer3);
                String a3 = name.getText().toString();
                EditText email = findViewById(R.id.emailr3);
                String a4 = email.getText().toString();
                EditText amka = findViewById(R.id.amkar3);
                String a5 = amka.getText().toString();

                if ((a1.equals("")||a2.equals("")||a3.equals("")||a4.equals("")||a5.equals(""))){
                    Toast.makeText(getApplicationContext(),"Παρακαλώ συμπληρώστε όλες τις πληροφορίες",Toast.LENGTH_LONG).show();
                } else if (a5.length()!=11) {
                    Toast.makeText(getApplicationContext(),"Ο ΑΜΚΑ πρέπει να αποτελείται απο 11 ψηφία",Toast.LENGTH_LONG).show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            boolean flag = ((ADoctorUser) StaticFunctionUtilities.getUser()).createPatient(a1, a2, a3, a4, a5);
                            if (flag) {
                                username.setHint(" Username");
                                username.setText("");
                                password.setText(" Password");
                                password.setText("");
                                name.setText(" Ονοματεπώνυμο");
                                name.setText("");
                                email.setText(" E-mail");
                                email.setText("");
                                amka.setHint(" ΑΜΚΑ");
                                amka.setText("");
                                ContextFlowUtilities.presentAlert("Επιτυχία", "Ο ασθενής δημηιουργήθηκε με επιτυχία");
                            } else {
                                ContextFlowUtilities.presentAlert("Αποτυχία", "");
                            }

                        }
                    }).start();
                }

            }
        });

    }

}