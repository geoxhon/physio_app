package com.geoxhonapps.physio_app.RestUtilities.Responses;

public class FGetPatientsResponse {
    public boolean isSuccess;
    public String userId;
    public String displayName;
    public String email;
    public String SSN;
    public FGetPatientsResponse(boolean success) {
        isSuccess = success;
    }

    public FGetPatientsResponse(boolean success, String id, String Name, String email, String SSN) {
        isSuccess = success;
        userId = id;
        displayName = Name;
        this.email = email;
        this.SSN = SSN;
    }
}
