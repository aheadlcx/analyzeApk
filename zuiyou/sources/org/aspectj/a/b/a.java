package org.aspectj.a.b;

abstract class a extends d implements org.aspectj.lang.a.a {
    Class[] a;
    String[] b;
    Class[] c;

    a(int i, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2) {
        super(i, str, cls);
        this.a = clsArr;
        this.b = strArr;
        this.c = clsArr2;
    }

    public Class[] a() {
        if (this.a == null) {
            this.a = d(3);
        }
        return this.a;
    }

    public Class[] b() {
        if (this.c == null) {
            this.c = d(5);
        }
        return this.c;
    }
}
