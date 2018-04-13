package com.umeng.analytics.pro;

public class da {
    public final String a;
    public final byte b;
    public final short c;

    public da() {
        this("", (byte) 0, (short) 0);
    }

    public da(String str, byte b, short s) {
        this.a = str;
        this.b = b;
        this.c = s;
    }

    public String toString() {
        return "<TField name:'" + this.a + "' type:" + this.b + " field-id:" + this.c + ">";
    }

    public boolean a(da daVar) {
        return this.b == daVar.b && this.c == daVar.c;
    }
}
