package qsbk.app.utils;

public class DomainRecord {
    int a = 600;
    long b = System.currentTimeMillis();
    public String domain;
    public int errorCount = 0;
    public String ip;

    public DomainRecord(String str, String str2) {
        this.domain = str;
        this.ip = str2;
    }

    public boolean isExpire() {
        return System.currentTimeMillis() > this.b + ((long) (this.a * 1000));
    }

    public boolean needRefresh() {
        return ((double) System.currentTimeMillis()) > ((double) this.b) + ((((double) this.a) * 0.75d) * 1000.0d);
    }

    public boolean needInvalid() {
        return this.errorCount > 2;
    }
}
