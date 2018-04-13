package org.json.alipay;

public class JSONException extends Exception {
    private Throwable a;

    public JSONException(String str) {
        super(str);
    }

    public JSONException(Throwable th) {
        super(th.getMessage());
        this.a = th;
    }

    public Throwable getCause() {
        return this.a;
    }
}
