package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class HttpException extends IOException {
    static Class class$java$lang$Throwable;
    private final Throwable cause;
    private String reason;
    private int reasonCode;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public HttpException() {
        this.reasonCode = 200;
        this.cause = null;
    }

    public HttpException(String str) {
        super(str);
        this.reasonCode = 200;
        this.cause = null;
    }

    public HttpException(String str, Throwable th) {
        super(str);
        this.reasonCode = 200;
        this.cause = th;
        try {
            Class class$;
            Class[] clsArr = new Class[1];
            if (class$java$lang$Throwable == null) {
                class$ = class$("java.lang.Throwable");
                class$java$lang$Throwable = class$;
            } else {
                class$ = class$java$lang$Throwable;
            }
            clsArr[0] = class$;
            if (class$java$lang$Throwable == null) {
                class$ = class$("java.lang.Throwable");
                class$java$lang$Throwable = class$;
            } else {
                class$ = class$java$lang$Throwable;
            }
            class$.getMethod("initCause", clsArr).invoke(this, new Object[]{th});
        } catch (Exception e) {
        }
    }

    public Throwable getCause() {
        return this.cause;
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream printStream) {
        try {
            getClass().getMethod("getStackTrace", new Class[0]);
            super.printStackTrace(printStream);
        } catch (Exception e) {
            super.printStackTrace(printStream);
            if (this.cause != null) {
                printStream.print("Caused by: ");
                this.cause.printStackTrace(printStream);
            }
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        try {
            getClass().getMethod("getStackTrace", new Class[0]);
            super.printStackTrace(printWriter);
        } catch (Exception e) {
            super.printStackTrace(printWriter);
            if (this.cause != null) {
                printWriter.print("Caused by: ");
                this.cause.printStackTrace(printWriter);
            }
        }
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReasonCode(int i) {
        this.reasonCode = i;
    }

    public int getReasonCode() {
        return this.reasonCode;
    }
}
