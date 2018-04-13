package com.umeng.analytics.pro;

public final class dd {
    public final String a;
    public final byte b;
    public final int c;

    public dd() {
        this("", (byte) 0, 0);
    }

    public dd(String str, byte b, int i) {
        this.a = str;
        this.b = b;
        this.c = i;
    }

    public String toString() {
        return "<TMessage name:'" + this.a + "' type: " + this.b + " seqid:" + this.c + ">";
    }

    public boolean equals(Object obj) {
        if (obj instanceof dd) {
            return a((dd) obj);
        }
        return false;
    }

    public boolean a(dd ddVar) {
        return this.a.equals(ddVar.a) && this.b == ddVar.b && this.c == ddVar.c;
    }
}
