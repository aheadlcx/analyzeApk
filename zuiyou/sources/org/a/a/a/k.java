package org.a.a.a;

import java.util.HashMap;

public abstract class k {
    private HashMap a = new HashMap();
    private HashMap b = new HashMap();
    private String c = "";
    private String d = "";
    private d e = null;

    public void a(String str, int i, int i2, int i3) {
        d dVar = new d(str, i, i2, i3, this);
        this.b.put(str.toLowerCase(), dVar);
        if (i2 == Integer.MIN_VALUE) {
            this.e = dVar;
        }
    }

    public d a() {
        return this.e;
    }

    public void a(String str, String str2, String str3, String str4) {
        d a = a(str);
        if (a == null) {
            throw new Error(new StringBuffer().append("Attribute ").append(str2).append(" specified for unknown element type ").append(str).toString());
        }
        a.a(str2, str3, str4);
    }

    public void a(String str, String str2) {
        d a = a(str);
        d a2 = a(str2);
        if (a == null) {
            throw new Error(new StringBuffer().append("No child ").append(str).append(" for parent ").append(str2).toString());
        } else if (a2 == null) {
            throw new Error(new StringBuffer().append("No parent ").append(str2).append(" for child ").append(str).toString());
        } else {
            a.b(a2);
        }
    }

    public void a(String str, int i) {
        this.a.put(str, new Integer(i));
    }

    public d a(String str) {
        return (d) this.b.get(str.toLowerCase());
    }

    public int b(String str) {
        Integer num = (Integer) this.a.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public void c(String str) {
        this.c = str;
    }

    public void d(String str) {
        this.d = str;
    }
}
