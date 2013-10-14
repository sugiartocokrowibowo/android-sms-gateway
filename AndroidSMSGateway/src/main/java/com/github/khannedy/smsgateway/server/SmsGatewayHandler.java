package com.github.khannedy.smsgateway.server;

import android.telephony.SmsManager;

import com.github.khannedy.smsgateway.helper.ResponseHelper;
import com.github.khannedy.smsgateway.model.SmsRequest;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

import java.io.IOException;

/**
 * @author Eko Khannedy
 */
public class SmsGatewayHandler implements HttpRequestHandler {

    @Override
    public void handle(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        SmsRequest smsRequest = SmsRequest.fromRequest(httpRequest);
        if (smsRequest.isValid()) {
            SmsManager.getDefault().sendTextMessage(smsRequest.getTo(), null, smsRequest.getMessage(), null, null);
            httpResponse.setEntity(new StringEntity(ResponseHelper.createSuccessJsonResponse()));
        } else {
            httpResponse.setEntity(new StringEntity(ResponseHelper.createFailedJsonResponse()));
        }
    }
}
