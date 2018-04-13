package com.tencent.bugly.proguard;

public final class aa extends m implements Cloneable {
    static bg c;
    static y d;
    static final /* synthetic */ boolean e = (!aa.class.desiredAssertionStatus());
    public bg a = null;
    public y b = null;

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        aa aaVar = (aa) obj;
        if (n.a(this.a, aaVar.a) && n.a(this.b, aaVar.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            if (!e) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public void a(l lVar) {
        if (this.a != null) {
            lVar.a(this.a, 0);
        }
        if (this.b != null) {
            lVar.a(this.b, 1);
        }
    }

    public void a(k kVar) {
        if (c == null) {
            c = new bg();
        }
        this.a = (bg) kVar.a(c, 0, false);
        if (d == null) {
            d = new y();
        }
        this.b = (y) kVar.a(d, 1, false);
    }

    public void a(StringBuilder stringBuilder, int i) {
        i iVar = new i(stringBuilder, i);
        iVar.a(this.a, "baseStrategy");
        iVar.a(this.b, "grayStrategy");
    }
}
