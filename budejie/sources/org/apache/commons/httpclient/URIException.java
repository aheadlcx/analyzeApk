package org.apache.commons.httpclient;

public class URIException extends HttpException {
    public static final int ESCAPING = 3;
    public static final int PARSING = 1;
    public static final int PUNYCODE = 4;
    public static final int UNKNOWN = 0;
    public static final int UNSUPPORTED_ENCODING = 2;
    protected String reason;
    protected int reasonCode;

    public URIException(int i) {
        this.reasonCode = i;
    }

    public URIException(int i, String str) {
        super(str);
        this.reason = str;
        this.reasonCode = i;
    }

    public URIException(String str) {
        super(str);
        this.reason = str;
        this.reasonCode = 0;
    }

    public int getReasonCode() {
        return this.reasonCode;
    }

    public void setReasonCode(int i) {
        this.reasonCode = i;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }
}
