package com.meizu.cloud.pushsdk.networking.error;

import com.meizu.cloud.pushsdk.networking.common.ANConstants;
import com.meizu.cloud.pushsdk.networking.http.Response;

public class ANError extends Exception {
    private String errorBody;
    private int errorCode = 0;
    private String errorDetail;
    private Response response;

    public ANError(Response response) {
        this.response = response;
    }

    public ANError(String str) {
        super(str);
    }

    public ANError(String str, Response response) {
        super(str);
        this.response = response;
    }

    public ANError(String str, Throwable th) {
        super(str, th);
    }

    public ANError(String str, Response response, Throwable th) {
        super(str, th);
        this.response = response;
    }

    public ANError(Response response, Throwable th) {
        super(th);
        this.response = response;
    }

    public ANError(Throwable th) {
        super(th);
    }

    public Response getResponse() {
        return this.response;
    }

    public void setErrorDetail(String str) {
        this.errorDetail = str;
    }

    public String getErrorDetail() {
        return this.errorDetail;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setCancellationMessageInError() {
        this.errorDetail = ANConstants.REQUEST_CANCELLED_ERROR;
    }

    public String getErrorBody() {
        return this.errorBody;
    }

    public void setErrorBody(String str) {
        this.errorBody = str;
    }
}
