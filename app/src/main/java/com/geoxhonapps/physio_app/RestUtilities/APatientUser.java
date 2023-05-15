package com.geoxhonapps.physio_app.RestUtilities;

import com.geoxhonapps.physio_app.RestUtilities.Responses.FGetAppointmentResponse;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FGetAvailabilityResponse;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FGetChildrenResponse;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FGetCreatorResponse;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FLoginResponse;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
public class APatientUser extends AUser{
    private ArrayList<Long> bookedTimestamps;
    private ADoctor myDoctor;
    private ArrayList<AAppointment> myAppointments;
    public APatientUser(FLoginResponse userInfo) {
        super(userInfo);
        bookedTimestamps = new ArrayList<Long>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FGetAvailabilityResponse response = StaticFunctionUtilities.getRestController().getAvailability();
                    if(response.isSuccess){
                        bookedTimestamps =  response.bookedTimestamps;
                    }
                    FGetCreatorResponse creator = StaticFunctionUtilities.getRestController().getCreator();
                    myDoctor = new ADoctor("", creator.displayName, creator.email, creator.SSN);
                    ArrayList<FGetAppointmentResponse> tempAppointments = StaticFunctionUtilities.getRestController().getAppointments();
                    for(int i = 0; i<tempAppointments.size(); i++){
                        myAppointments.add(new AAppointment(tempAppointments.get(i)));
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
     * Συνάρτηση που επιστρέφει μια λίστα με τα ραντεβού του γιατρού
     * @param bShouldRefreshList Αν θα πρέπει να ανανεώσουμε την λίστα κάνοντας αίτημα στο API, αν ναι τότε η συνάρτηση δεν πρέπει να τρέξει από το κύριο thread
     * @return Επιστρέφει λίστα με ραντεβού
     */
    public ArrayList<AAppointment> getAppointments(boolean bShouldRefreshList){
        if(bShouldRefreshList){
            ArrayList<FGetAppointmentResponse> tempAppointments = null;
            try {
                tempAppointments = StaticFunctionUtilities.getRestController().getAppointments();
                myAppointments.clear();
                for(int i = 0; i<tempAppointments.size(); i++){
                    myAppointments.add(new AAppointment(tempAppointments.get(i)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return myAppointments;
    }

    /**
     * Συνάρτηση για την λήψη διαθέσιμων ραντεβού για μια συγκεκριμένη ημερομηνία
     * @param date Η Ημερομηνία σε String σε μορφη εεεε-ΜΜ-μμ ΩΩ:λλ:δδ
     * @return Μια λίστα με διαθέσιμες ώρες σε μορφή date.
     */
    public ArrayList<Date> getAvailableAppointmentsForDate(Calendar date){
        ArrayList<Date> outResults = new ArrayList<Date>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            for(int i = 10; i<22; i++){
                Date parsedDate = dateFormat.parse(date+" "+ Integer.toString(i)+":00:00");
                if(!bookedTimestamps.contains(parsedDate.getTime())){
                    outResults.add(parsedDate);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outResults;
    }

    /**
     * Συνάρτηση για την δέσμευση ραντεβού με τον γιατρό
     * ΝΑ ΜΗΝ ΕΚΤΕΛΕΙΤΕ ΑΠΟ ΤΟ ΚΥΡΙΟ THREAD.
     * @param date Η Ημερομηνία του ραντεβού
     * @return Επιστρέφει αν η καταχώρηση ήταν επιτυχής
     */
    public boolean bookAppointment(Date date){
        try {
            return StaticFunctionUtilities.getRestController().bookAppointment(date.getTime()/1000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
    public ADoctor getMyDoctor(){
        return myDoctor;
    }
}
