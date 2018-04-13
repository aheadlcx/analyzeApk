package com.alibaba.sdk.android.oss;

public class ClientException extends Exception {
    private Boolean canceled;

    public ClientException() {
        this.canceled = Boolean.valueOf(false);
    }

    public ClientException(String str) {
        super("[ErrorMessage]: " + str);
        this.canceled = Boolean.valueOf(false);
    }

    public ClientException(Throwable th) {
        super(th);
        this.canceled = Boolean.valueOf(false);
    }

    public ClientException(String str, Throwable th) {
        this(str, th, Boolean.valueOf(false));
    }

    public ClientException(String str, Throwable th, Boolean bool) {
        super("[ErrorMessage]: " + str, th);
        this.canceled = Boolean.valueOf(false);
        this.canceled = bool;
    }

    public Boolean isCanceledException() {
        return this.canceled;
    }

    public String getMessage() {
        String message = super.getMessage();
        return getCause() == null ? message : getCause().getMessage() + "\n" + message;
    }
}
