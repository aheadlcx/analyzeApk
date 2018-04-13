package com.tencent.bugly.proguard;

public final class ay extends m implements Cloneable {
    public String a = "";
    public String b = "";
    public String c = "";
    public String d = "";
    public String e = "";

    public void a(l lVar) {
        lVar.a(this.a, 0);
        if (this.b != null) {
            lVar.a(this.b, 1);
        }
        if (this.c != null) {
            lVar.a(this.c, 2);
        }
        if (this.d != null) {
            lVar.a(this.d, 3);
        }
        if (this.e != null) {
            lVar.a(this.e, 4);
        }
    }

    public void a(k kVar) {
        this.a = kVar.a(0, true);
        this.b = kVar.a(1, false);
        this.c = kVar.a(2, false);
        this.d = kVar.a(3, false);
        this.e = kVar.a(4, false);
    }

    public void a(StringBuilder stringBuilder, int i) {
    }
}
