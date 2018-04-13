package qsbk.app.utils.image.issue;

public class IssueBean {
    private String a;
    private String b;
    private Exception c;
    private int d;
    private int e = 1;

    public IssueBean(String str, int i, Exception exception, String str2, int i2) {
        this.a = str;
        this.b = str2;
        this.c = exception;
        this.d = i;
        this.e = i2;
    }

    public int getType() {
        return this.e;
    }

    public void setType(int i) {
        this.e = i;
    }

    public String getUrl() {
        return this.a;
    }

    public void setUrl(String str) {
        this.a = str;
    }

    public String getIp() {
        return this.b;
    }

    public void setIp(String str) {
        this.b = str;
    }

    public Exception getException() {
        return this.c;
    }

    public void setException(Exception exception) {
        this.c = exception;
    }

    public int getResponseCode() {
        return this.d;
    }

    public void setResponseCode(int i) {
        this.d = i;
    }

    public String toString() {
        return "IssueBean{url='" + this.a + '\'' + ", ip='" + this.b + '\'' + ", exception=" + this.c + ", responseCode=" + this.d + ", type=" + this.e + '}';
    }
}
