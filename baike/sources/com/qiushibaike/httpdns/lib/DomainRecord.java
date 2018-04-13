package com.qiushibaike.httpdns.lib;

public class DomainRecord {
    private long a = System.currentTimeMillis();
    public String domain;
    public int errorCount = 0;
    public String ip;
    public int ttl = 600;

    public DomainRecord(String str, String str2) {
        this.domain = str;
        this.ip = str2;
    }

    public boolean isExpire() {
        return System.currentTimeMillis() > this.a + ((long) (this.ttl * 1000));
    }

    public boolean needRefresh() {
        return ((double) System.currentTimeMillis()) > ((double) this.a) + ((((double) this.ttl) * 0.7d) * 1000.0d);
    }

    public boolean needInvalid() {
        return this.errorCount > 2;
    }

    public String toString() {
        return "DomainRecord{domain='" + this.domain + '\'' + ", ip='" + this.ip + '\'' + ", time=" + this.a + ", errorCount=" + this.errorCount + '}';
    }
}
