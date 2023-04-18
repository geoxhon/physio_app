package com.geoxhonapps.physio_app.RestUtilities;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FCreateUserResponse;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FGetPatientsResponse;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FLoginResponse;
import com.geoxhonapps.physio_app.RestUtilities.Responses.FRestResponse;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class RestController {
    private String userId;
    private String username;
    private RequestComponent requestComponent;

    public RestController() {
        requestComponent = new RequestComponent();
    }

    public FLoginResponse doLogin(String username, String password) throws IOException, JSONException {
        JSONObject obj = new JSONObject();

        obj.put("username", username);
        obj.put("password", password);

        FRestResponse r = requestComponent.Post("/api/v1/auth/login", obj);
        System.out.println(r.responseContent);
        if(r.statusCode==200) {

            JSONObject data = new JSONObject(r.responseContent);
            data = (JSONObject)data.get("triggerResults");
            userId = (String) data.get("userId");
            this.username = username;
            return new FLoginResponse(true, userId, (String)data.get("displayName"), username, ((Number)data.get("userType")).intValue(), data.getString("email"), data.getString("ssn"));
        }
        return new FLoginResponse(false);
    }
    public ArrayList<FGetPatientsResponse> getAllPatients() throws IOException, JSONException {
        ArrayList<FGetPatientsResponse> outPatients = new ArrayList<FGetPatientsResponse>();
        FRestResponse r = requestComponent.Get("/api/v1/me/patients");
        JSONObject data = new JSONObject(r.responseContent);
        if(data.getBoolean("success")){
            data = (JSONObject)data.get("triggerResults");
            JSONArray patients = data.getJSONArray("patients");
            for(int i = 0; i<patients.length();i++){
                JSONObject temp = patients.getJSONObject(i);
                outPatients.add(new FGetPatientsResponse(true, temp.getString("id"), temp.getString("displayName"), temp.getString("email"), temp.getString("ssn")));
            }
        }
        System.out.println(outPatients);
        return outPatients;
    }

    public FCreateUserResponse registerUser(String username, String password, String displayName, String email, String SSN) throws JSONException, IOException {
        JSONObject obj = new JSONObject();
        obj.put("username", username);
        obj.put("password", password);
        obj.put("displayName", displayName);
        obj.put("email", email);
        obj.put("ssn", SSN);
        FRestResponse r = requestComponent.Post("/api/v1/auth/register", obj);
        JSONObject data = new JSONObject(r.responseContent);
        if(data.getBoolean("success")) {
            data = data.getJSONObject("triggerResults");
            return new FCreateUserResponse(true, data.getString("userId"), data.getString("createdAt"), data.getInt("accountType"));
        }
        return new FCreateUserResponse(false);
    }
}
