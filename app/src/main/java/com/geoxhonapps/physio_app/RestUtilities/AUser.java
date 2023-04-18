package com.geoxhonapps.physio_app.RestUtilities;

import com.geoxhonapps.physio_app.RestUtilities.Responses.FLoginResponse;

public class AUser {
    private String userId;
    private String displayName;
    private String username;
    private EUserType accountType;
    private String email;
    private String SSN;

    public AUser(FLoginResponse userInfo){
        userId = userInfo.userId;
        displayName = userInfo.displayName;
        username = userInfo.username;
        accountType = EUserType.values()[userInfo.accountType];
        email = userInfo.email;
        SSN = userInfo.SSN;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUsername() {
        return username;
    }

    public EUserType getAccountType() {
        return accountType;
    }

    public String getEmail() {
        return email;
    }

    public String getSSN() {
        return SSN;
    }
}
