package cn.v6.sixrooms.bean;

public class GuardPropDetailBean {
    private String c;
    private String d;
    private int tid;

    public GuardPropDetailBean(String str, String str2, int i) {
        this.d = str;
        this.c = str2;
        this.tid = i;
    }

    public String getDays() {
        return this.d;
    }

    public void setDays(String str) {
        this.d = str;
    }

    public String getPrice() {
        return this.c;
    }

    public void setPrice(String str) {
        this.c = str;
    }

    public int getTid() {
        return this.tid;
    }

    public void setTid(int i) {
        this.tid = i;
    }

    public String toString() {
        return "PropDetailBean [days=" + this.d + ", price=" + this.c + ", tid=" + this.tid + "]";
    }
}
