package com.alibaba.sdk.android.oss;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 430933593095358673L;
    private String errorCode;
    private String hostId;
    private String rawMessage;
    private String requestId;
    private int statusCode;

    public ServiceException(int i, String str, String str2, String str3, String str4, String str5) {
        super(str);
        this.statusCode = i;
        this.errorCode = str2;
        this.requestId = str3;
        this.hostId = str4;
        this.rawMessage = str5;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getHostId() {
        return this.hostId;
    }

    public String toString() {
        return "[StatusCode]: " + this.statusCode + ", " + "[Code]: " + getErrorCode() + ", " + "[Message]: " + getMessage() + ", " + "[Requestid]: " + getRequestId() + ", " + "[HostId]: " + getHostId();
    }

    public String getRawMessage() {
        return this.rawMessage;
    }
}
