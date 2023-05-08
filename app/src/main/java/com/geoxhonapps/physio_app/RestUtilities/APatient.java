package com.geoxhonapps.physio_app.RestUtilities;

import com.geoxhonapps.physio_app.RestUtilities.Responses.FGetChildrenResponse;

import java.util.ArrayList;

public class APatient extends AInfo{
    private String userId;
    private String displayName;
    private String email;
    private String SSN;
    private ArrayList<ARecord> patientHistory;
    public APatient(String userId, String displayName, String email, String SSN){
        super(userId, displayName, email, SSN);
    }
    public APatient(FGetChildrenResponse userInfo){
        super(userInfo);
    }

    public boolean addRecord(ARecord newRecord){
        for(ARecord record: patientHistory){
            if(record.getId() == newRecord.getId()){
                return false;
            }
        }
        patientHistory.add(newRecord);
        return true;
    }

    public ArrayList<ARecord> getPatientHistory(){
        return this.patientHistory;
    }
}
