package com.umeng.commonsdk.proguard;

public final class ai {
    public final String a;
    public final byte b;
    public final int c;

    public ai() {
        this("", (byte) 0, 0);
    }

    public ai(String str, byte b, int i) {
        this.a = str;
        this.b = b;
        this.c = i;
    }

    public String toString() {
        return "<TMessage name:'" + this.a + "' type: " + this.b + " seqid:" + this.c + ">";
    }

    public boolean equals(Object obj) {
        if (obj instanceof ai) {
            return a((ai) obj);
        }
        return false;
    }

    public boolean a(ai aiVar) {
        return this.a.equals(aiVar.a) && this.b == aiVar.b && this.c == aiVar.c;
    }
}
