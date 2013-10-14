package com.github.khannedy.smsgateway.helper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Eko Khannedy
 */
public class ResponseHelper {

    public static String createJsonResponse(String status) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("status", status);
        return object.toString();
    }

    public static String createSuccessJsonResponse() {
        try {
            return createJsonResponse("success");
        } catch (JSONException e) {
            return null;
        }
    }

    public static String createFailedJsonResponse() {
        try {
            return createJsonResponse("failed");
        } catch (JSONException e) {
            return null;
        }
    }

}
