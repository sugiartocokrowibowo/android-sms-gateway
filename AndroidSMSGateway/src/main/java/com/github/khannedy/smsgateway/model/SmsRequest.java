package com.github.khannedy.smsgateway.model;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * @author Eko Khannedy
 */
public class SmsRequest {

    private String to;
    private String message;

    public SmsRequest() {
        this(null, null);
    }

    public SmsRequest(String to, String message) {
        setTo(to);
        setMessage(message);
    }

    public static SmsRequest fromRequest(HttpRequest httpRequest) {
        SmsRequest smsRequest = new SmsRequest();
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            try {
                HttpEntity entity = ((HttpEntityEnclosingRequest) httpRequest).getEntity();
                String body = EntityUtils.toString(entity);
                JSONObject object = new JSONObject(body);

                smsRequest.setTo(object.getString("to"));
                smsRequest.setMessage(object.getString("message"));
            } catch (Exception ex) {
                Log.e(SmsRequest.class.getName(), ex.getMessage(), ex);
            }
        }
        return smsRequest;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValid() {
        return getMessage() != null && getTo() != null;
    }
}
