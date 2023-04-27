package com.geoxhonapps.physio_app;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.geoxhonapps.physio_app.RestUtilities.ADoctorUser;
import com.geoxhonapps.physio_app.RestUtilities.AUser;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FLoginResponse;
import com.geoxhonapps.physio_app.RestUtilities.RestController;
import com.geoxhonapps.physio_app.activities.HomeActivity;

import org.json.JSONException;

import java.io.IOException;

public class StaticFunctionUtilities {
    private static RestController restController = new RestController();
    private static AUser User;
    /**
     * Πραγματοποιεί την σύνδεση του χρήστη, έφοσον είναι επιτυχής μεταφερόμαστε στο HomeActivity
     * @param username Το όνομα χρήστη
     * @param password Ο κωδικός του χρήστη
     */
    public static void attemptLogin(String username, String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FLoginResponse r = restController.doLogin(username, password);
                    if(r.isSuccess){
                        switch(r.accountType){
                            case 0:
                            case 2:
                                User = new AUser(r);
                                break;
                            case 1:
                                User = new ADoctorUser(r);
                                break;
                        }
                        ContextFlowUtilities.moveTo(HomeActivity.class, false);
                    }else{
                        ContextFlowUtilities.presentAlert("Σφάλμα", "Η σύνδεση δεν ήταν επιτυχής, παρακαλώ προσπαθήστε ξανά");
                    }
                } catch (IOException e) {
                    ContextFlowUtilities.presentAlert("Σφάλμα", "Δεν υπάρχει σύνδεση στο διαδίκτυο");
                } catch (JSONException e) {
                    ContextFlowUtilities.presentAlert("Σφάλμα", "Η σύνδεση δεν ήταν επιτυχής, παρακαλώ προσπαθήστε ξανά");
                }
            }
        }).start();
    }


    /**
     * Επιστρέφει τον χρήστη για τον οποίο έχει γίνει σύνδεση
     * @return χρήστη για τον οποίο έχει γίνει σύνδεση
     */
    public static AUser getUser() {
        return User;
    }

    public static RestController getRestController(){return restController;}
}