package com.geoxhonapps.physio_app.RestUtilities;

import com.geoxhonapps.physio_app.RestUtilities.Responses.FCreateUserResponse;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FGetChildrenResponse;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FLoginResponse;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class ADoctorUser extends AUser{
    private ArrayList<APatient> myPatients;
    public ADoctorUser(FLoginResponse userInfo) {
        super(userInfo);
        myPatients = new ArrayList<APatient>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<FGetChildrenResponse> temp = StaticFunctionUtilities.getRestController().getAllChildren();
                    for(int i =0; i<temp.size();i++){
                        myPatients.add(new APatient(temp.get(i)));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Συνάρτηση που επιστρέφει μια λίστα από τους ασθενείς που έχει δηλώσει ο γιατρός.
     * @param bShouldRefreshList Αν θα πρέπει να ανανεώσουμε την λίστα κάνοντας αίτημα στο API, αν ναι τότε η συνάρτηση δεν πρέπει να τρέξει από το κύριο thread
     * @return Επιστρέφει λίστα με ασθενείς
     */
    public ArrayList<APatient> getPatients(boolean bShouldRefreshList){
        if(bShouldRefreshList){
            ArrayList<FGetChildrenResponse> temp = null;
            try {
                temp = StaticFunctionUtilities.getRestController().getAllChildren();
                myPatients.clear();
                for(int i =0; i<temp.size();i++){
                    myPatients.add(new APatient(temp.get(i)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return myPatients;
    }

    /**
     * Συνάρτηση που δημιουργεί έναν καινούργιο ασθενή και τον προσθέτει στην βάση δεδομένων.
     * ΝΑ ΜΗΝ ΕΚΤΕΛΕΙΤΑΙ ΑΠΟ ΤΟ ΚΥΡΙΟ THREAD
     * @param username Το όνομα χρήστη του νέου ασθενή
     * @param password Ο κωδικός του νέου ασθενή
     * @param displayName Το Ονοματεπώνυμο του που θα εμφανίζεται και στην λίστα με τους ασθενής
     * @param email Το email του νέου ασθενή
     * @param SSN Το ΑΜΚΑ/ΑΦΜ του.
     * @return Επιστρέφει αν η καταχώρηση ήταν επιτυχής. Αν δεν ηταν επιτυχής πρόκειται για διπλότυπο username ή email.
     */
    public boolean createPatient(String username, String password, String displayName, String email, String SSN){
        try {
            FCreateUserResponse newPatient = StaticFunctionUtilities.getRestController().registerUser(username, password, displayName, email, SSN);
            if(newPatient.isSuccess){
                myPatients.add(new APatient(newPatient.userId, displayName, email, SSN));
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


}