package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

public final class bg extends m implements Cloneable {
    static bf m = new bf();
    static Map<String, String> n = new HashMap();
    static final /* synthetic */ boolean o;
    public boolean a = true;
    public boolean b = true;
    public boolean c = true;
    public String d = "";
    public String e = "";
    public bf f = null;
    public Map<String, String> g = null;
    public long h = 0;
    public String i = "";
    public String j = "";
    public int k = 0;
    public int l = 0;

    static {
        boolean z;
        if (bg.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        o = z;
        n.put("", "");
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        bg bgVar = (bg) obj;
        if (n.a(this.a, bgVar.a) && n.a(this.b, bgVar.b) && n.a(this.c, bgVar.c) && n.a(this.d, bgVar.d) && n.a(this.e, bgVar.e) && n.a(this.f, bgVar.f) && n.a(this.g, bgVar.g) && n.a(this.h, bgVar.h) && n.a(this.i, bgVar.i) && n.a(this.j, bgVar.j) && n.a(this.k, bgVar.k) && n.a(this.l, bgVar.l)) {
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
            if (!o) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public void a(l lVar) {
        lVar.a(this.a, 0);
        lVar.a(this.b, 1);
        lVar.a(this.c, 2);
        if (this.d != null) {
            lVar.a(this.d, 3);
        }
        if (this.e != null) {
            lVar.a(this.e, 4);
        }
        if (this.f != null) {
            lVar.a(this.f, 5);
        }
        if (this.g != null) {
            lVar.a(this.g, 6);
        }
        lVar.a(this.h, 7);
        if (this.i != null) {
            lVar.a(this.i, 8);
        }
        if (this.j != null) {
            lVar.a(this.j, 9);
        }
        lVar.a(this.k, 10);
        lVar.a(this.l, 11);
    }

    public void a(k kVar) {
        this.a = kVar.a(this.a, 0, true);
        this.b = kVar.a(this.b, 1, true);
        this.c = kVar.a(this.c, 2, true);
        this.d = kVar.a(3, false);
        this.e = kVar.a(4, false);
        this.f = (bf) kVar.a(m, 5, false);
        this.g = (Map) kVar.a(n, 6, false);
        this.h = kVar.a(this.h, 7, false);
        this.i = kVar.a(8, false);
        this.j = kVar.a(9, false);
        this.k = kVar.a(this.k, 10, false);
        this.l = kVar.a(this.l, 11, false);
    }

    public void a(StringBuilder stringBuilder, int i) {
        i iVar = new i(stringBuilder, i);
        iVar.a(this.a, "enable");
        iVar.a(this.b, "enableUserInfo");
        iVar.a(this.c, "enableQuery");
        iVar.a(this.d, "url");
        iVar.a(this.e, "expUrl");
        iVar.a(this.f, "security");
        iVar.a(this.g, "valueMap");
        iVar.a(this.h, "strategylastUpdateTime");
        iVar.a(this.i, "httpsUrl");
        iVar.a(this.j, "httpsExpUrl");
        iVar.a(this.k, "eventRecordCount");
        iVar.a(this.l, "eventTimeInterval");
    }
}
