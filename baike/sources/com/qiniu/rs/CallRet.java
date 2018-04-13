package com.qiniu.rs;

public class CallRet {
    private final int a;
    private final String b;
    private final String c;
    private Exception d;

    public CallRet(CallRet callRet) {
        this.a = callRet.a;
        this.b = callRet.b;
        this.c = callRet.c;
        this.d = callRet.d;
        b();
    }

    public CallRet(int i, String str, String str2) {
        this.a = i;
        this.b = str;
        this.c = str2;
        b();
    }

    public CallRet(int i, String str, Exception exception) {
        this.a = i;
        this.b = str;
        this.c = "";
        this.d = exception;
        b();
    }

    private void b() {
        if (this.d == null && this.c != null && this.c.trim().startsWith("{")) {
            try {
                a();
            } catch (Exception e) {
                if (this.d == null) {
                    this.d = e;
                }
            }
        }
    }

    protected void a() throws Exception {
    }

    public boolean isOk() {
        return this.a / 100 == 2;
    }

    public int getStatusCode() {
        return this.a;
    }

    public String getReqId() {
        return this.b;
    }

    public String getResponse() {
        return this.c;
    }

    public Exception getException() {
        return this.d;
    }

    public String toString() {
        String str = "statusCode: " + this.a + ", reqId: " + this.b + ", response: " + this.c;
        if (this.d != null) {
            return str + ", ex: " + this.d.toString();
        }
        return str;
    }
}
