package com.umeng.commonsdk.proguard;

public class af {
    public final String a;
    public final byte b;
    public final short c;

    public af() {
        this("", (byte) 0, (short) 0);
    }

    public af(String str, byte b, short s) {
        this.a = str;
        this.b = b;
        this.c = s;
    }

    public String toString() {
        return "<TField name:'" + this.a + "' type:" + this.b + " field-id:" + this.c + ">";
    }

    public boolean a(af afVar) {
        return this.b == afVar.b && this.c == afVar.c;
    }
}
