package com.geoxhonapps.physio_app.RestUtilities;

import com.geoxhonapps.physio_app.RestUtilities.Responses.FGetAppointmentResponse;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FGetChildrenResponse;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FGetServicesResponse;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FLoginResponse;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class AUser {
    private String userId;
    private String displayName;
    private String username;
    private EUserType accountType;
    private String email;
    private String SSN;
    protected ArrayList<AService> services;
    public AUser(FLoginResponse userInfo){
        userId = userInfo.userId;
        displayName = userInfo.displayName;
        username = userInfo.username;
        accountType = EUserType.values()[userInfo.accountType];
        email = userInfo.email;
        SSN = userInfo.SSN;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<FGetServicesResponse> temp = StaticFunctionUtilities.getRestController().getServices();
                    for(int i =0; i<temp.size();i++){
                        services.add(new AService(temp.get(i)));
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
     * Συνάρτηση για την λήψη του id του συνδεδεμένου χρήστη
     * @return Επιστρέφει το id σε String
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Συνάρτηση για την λήψη του ονοματεπώνυμου του συνδεδεμένου χρήστη
     * @return Επιστρέφει το ονοματεπώνυμο σε String
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Συνάρτηση για την λήψη του username του συνδεδεμένου χρήστη
     * @return Επιστρέφει το username σε String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Συνάρτηση για την λήψη του τύπου του χρήστη (Γιατρός, Ασθενής, Manager)
     * @return Επιστρέφει τον τύπο του χρήστη σε EUserType
     */
    public EUserType getAccountType() {
        return accountType;
    }

    /**
     * Συνάρτηση για την λήψη του email του συνδεδεμένου χρήστη
     * @return Επιστρέφει το email σε String
     */
    public String getEmail() {
        return email;
    }
    /**
     * Συνάρτηση για την λήψη του ΑΜΚΑ/ΑΦΜ του συνδεδεμένου χρήστη
     * @return Επιστρέφει το ΑΜΚΑ/ΑΦΜ σε String
     */
    public String getSSN() {
        return SSN;
    }

    /**
     * Συνάρτηση για την λήψη όλων των διαθέσιμων παροχών που μπορεί να προσφέρει ένας γιατρός στους ασθενείς του.
     * Μπορεί να κληθεί από όλους τους χρήστες.
     * @param bShouldRefreshList Αν θα πρέπει να ανανεώσουμε την λίστα κάνοντας αίτημα στο API, αν ναι τότε η συνάρτηση δεν πρέπει να τρέξει από το κύριο thread.
     * @return Επιστρέφει τις παροχές σε λίστα ASercice.
     */
    public ArrayList<AService> getServices(boolean bShouldRefreshList){
        if(bShouldRefreshList){
            ArrayList<FGetServicesResponse> tempServices = null;
            try {
                tempServices = StaticFunctionUtilities.getRestController().getServices();
                services.clear();
                for(int i =0; i<tempServices.size();i++){
                    services.add(new AService(tempServices.get(i)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return services;
    }
}
