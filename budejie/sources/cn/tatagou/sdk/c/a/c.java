package cn.tatagou.sdk.c.a;

public class c extends Exception {
    private String a;
    private String b;

    public c(String str, String str2, String str3) {
        super(str2);
        this.a = str;
        this.b = str3;
    }

    public c(String str, String str2, Throwable th, String str3) {
        super(str2, th);
        this.a = str;
        this.b = str3;
    }

    public String GetErrorCode() {
        return this.a;
    }

    public String GetErrorMessage() {
        return super.getMessage();
    }

    public String GetRequestId() {
        return this.b;
    }
}
