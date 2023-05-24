package com.geoxhonapps.physio_app.activities;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.geoxhonapps.physio_app.ContextFlowUtilities;
import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.RequestComponent;
import com.geoxhonapps.physio_app.RestUtilities.RestController;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;

public class MainActivity extends ParentActivity {
    RequestComponent requestComponent;
    RestController restController;
    public MainActivity(){
        requestComponent = new RequestComponent();
        restController = new RestController();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button myButton = findViewById(R.id.loginButton);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        password.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    StaticFunctionUtilities.attemptLogin(username.getText().toString(), password.getText().toString());
                    return true;
                }
                return false;
            }
        });
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StaticFunctionUtilities.attemptLogin(username.getText().toString(), password.getText().toString());
            }
        });
    }
}