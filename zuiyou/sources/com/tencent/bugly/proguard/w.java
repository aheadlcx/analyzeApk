package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

public final class w extends m implements Cloneable {
    static v i;
    static Map<String, String> j;
    static final /* synthetic */ boolean k = (!w.class.desiredAssertionStatus());
    public String a = "";
    public long b = 0;
    public byte c = (byte) 0;
    public long d = 0;
    public v e = null;
    public String f = "";
    public int g = 0;
    public Map<String, String> h = null;

    public w(String str, long j, byte b, long j2, v vVar, String str2, int i, Map<String, String> map) {
        this.a = str;
        this.b = j;
        this.c = b;
        this.d = j2;
        this.e = vVar;
        this.f = str2;
        this.g = i;
        this.h = map;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        w wVar = (w) obj;
        if (n.a(this.a, wVar.a) && n.a(this.b, wVar.b) && n.a(this.c, wVar.c) && n.a(this.d, wVar.d) && n.a(this.e, wVar.e) && n.a(this.f, wVar.f) && n.a(this.g, wVar.g) && n.a(this.h, wVar.h)) {
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
            if (!k) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public void a(l lVar) {
        lVar.a(this.a, 0);
        lVar.a(this.b, 1);
        lVar.b(this.c, 2);
        lVar.a(this.d, 3);
        if (this.e != null) {
            lVar.a(this.e, 4);
        }
        if (this.f != null) {
            lVar.a(this.f, 5);
        }
        lVar.a(this.g, 6);
        if (this.h != null) {
            lVar.a(this.h, 7);
        }
    }

    public void a(k kVar) {
        this.a = kVar.a(0, true);
        this.b = kVar.a(this.b, 1, true);
        this.c = kVar.a(this.c, 2, true);
        this.d = kVar.a(this.d, 3, false);
        if (i == null) {
            i = new v();
        }
        this.e = (v) kVar.a(i, 4, false);
        this.f = kVar.a(5, false);
        this.g = kVar.a(this.g, 6, false);
        if (j == null) {
            j = new HashMap();
            j.put("", "");
        }
        this.h = (Map) kVar.a(j, 7, false);
    }

    public void a(StringBuilder stringBuilder, int i) {
        i iVar = new i(stringBuilder, i);
        iVar.a(this.a, "eventType");
        iVar.a(this.b, "eventTime");
        iVar.a(this.c, "eventResult");
        iVar.a(this.d, "eventElapse");
        iVar.a(this.e, "destAppInfo");
        iVar.a(this.f, "strategyId");
        iVar.a(this.g, "updateType");
        iVar.a(this.h, "reserved");
    }
}
