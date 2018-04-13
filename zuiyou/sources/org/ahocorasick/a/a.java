package org.ahocorasick.a;

import org.ahocorasick.interval.c;

public class a extends org.ahocorasick.interval.a implements c {
    private final String a;

    public a(int i, int i2, String str) {
        super(i, i2);
        this.a = str;
    }

    public String d() {
        return this.a;
    }

    public String toString() {
        return super.toString() + "=" + this.a;
    }
}
