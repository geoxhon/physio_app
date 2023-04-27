package com.geoxhonapps.physio_app.RestUtilities;

import com.geoxhonapps.physio_app.RestUtilities.Responses.FGetChildrenResponse;

public class ADoctor extends AInfo{
    private String userId;
    private String displayName;
    private String email;
    private String SSN;
    public ADoctor(String userId, String displayName, String email, String SSN){
        super(userId, displayName, email,SSN);
    }
    public ADoctor(FGetChildrenResponse userInfo){
        super(userInfo);
    }
}
