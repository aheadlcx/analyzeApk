package org.aspectj.a.b;

import org.aspectj.lang.a.d;

class g implements d {
    Class a;
    String b;
    int c;

    g(Class cls, String str, int i) {
        this.a = cls;
        this.b = str;
        this.c = i;
    }

    public Class a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public String toString() {
        return new StringBuffer().append(b()).append(":").append(c()).toString();
    }
}
