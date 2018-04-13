package cn.v6.sixrooms.bean;

public class CustomExceptionBean extends MessageBean {
    public static final String TAG_DEBUG = "socket_debug";
    private String data;
    private Exception e;
    private String tag;

    public Exception getE() {
        return this.e;
    }

    public void setE(Exception exception) {
        this.e = exception;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }
}
