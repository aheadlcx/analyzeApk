package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

public final class z extends m implements Cloneable {
    static Map<String, String> e;
    static final /* synthetic */ boolean f = (!z.class.desiredAssertionStatus());
    public int a = 0;
    public String b = "";
    public long c = 0;
    public Map<String, String> d = null;

    public z(int i, String str, long j, Map<String, String> map) {
        this.a = i;
        this.b = str;
        this.c = j;
        this.d = map;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        z zVar = (z) obj;
        if (n.a(this.a, zVar.a) && n.a(this.b, zVar.b) && n.a(this.c, zVar.c) && n.a(this.d, zVar.d)) {
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
            if (!f) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public void a(l lVar) {
        lVar.a(this.a, 0);
        if (this.b != null) {
            lVar.a(this.b, 1);
        }
        lVar.a(this.c, 2);
        if (this.d != null) {
            lVar.a(this.d, 3);
        }
    }

    public void a(k kVar) {
        this.a = kVar.a(this.a, 0, false);
        this.b = kVar.a(1, false);
        this.c = kVar.a(this.c, 2, false);
        if (e == null) {
            e = new HashMap();
            e.put("", "");
        }
        this.d = (Map) kVar.a(e, 3, false);
    }

    public void a(StringBuilder stringBuilder, int i) {
        i iVar = new i(stringBuilder, i);
        iVar.a(this.a, "flag");
        iVar.a(this.b, "localStrategyId");
        iVar.a(this.c, "localStrategyTime");
        iVar.a(this.d, "reserved");
    }
}
