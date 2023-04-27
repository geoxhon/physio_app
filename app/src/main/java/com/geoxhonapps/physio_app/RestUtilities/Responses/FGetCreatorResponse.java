package com.geoxhonapps.physio_app.RestUtilities.Responses;

public class FGetCreatorResponse {
    public boolean isSuccess;
    public String userId;
    public String displayName;
    public String email;
    public String SSN;

    public FGetCreatorResponse(boolean success) {
        isSuccess = success;
    }

    public FGetCreatorResponse(boolean success, String id, String Name, String email, String SSN) {
        isSuccess = success;
        userId = id;
        displayName = Name;
        this.email = email;
        this.SSN = SSN;
    }
}
