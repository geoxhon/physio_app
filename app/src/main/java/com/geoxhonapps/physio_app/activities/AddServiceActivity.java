package com.geoxhonapps.physio_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.AManagerUser;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FGetAppointmentResponse;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;

import java.util.ArrayList;

public class AddServiceActivity extends ParentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r2);
    }
    @Override
    public void onClick(View view) {
        Button btn = findViewById(R.id.r2_save_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText codetext= findViewById(R.id.r2_code_tv);
                String c1=codetext.getText().toString();
                EditText nametext= findViewById(R.id.r2_sname_tv);
                String c2=nametext.getText().toString();
                EditText desctext= findViewById(R.id.r2_desc_tv);
                String c3=desctext.getText().toString();
                EditText costtext= findViewById(R.id.r2_cost_tv);
                String c4=costtext.getText().toString();
                int costasnum=Integer.valueOf(c4);

                if (c1.length()!=5){
                    Toast.makeText(getApplicationContext(),"Ο κωδικός της υπηρεσίας πρέπει υποχρεωτικά να αποτελείται από 5 χαρακτήρες",Toast.LENGTH_LONG).show();
                } else if (costasnum<0) {
                    Toast.makeText(getApplicationContext(),"H τιμή της υπηρεσίας δεν μπορεί να είναι αρνητική",Toast.LENGTH_LONG).show();
                }
                else{
                    if (((AManagerUser) StaticFunctionUtilities.getUser()).createService(c1,c2,c3,costasnum)){
                        ((AManagerUser) StaticFunctionUtilities.getUser()).createService(c1,c2,c3,costasnum);
                        codetext.setText(" ");
                        nametext.setText(" ");
                        desctext.setText(" ");
                        costtext.setText(" ");
                        Toast.makeText(getApplicationContext(),"Η υπηρεσία δημιουργήθηκε με επιτυχία",Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
    }



}