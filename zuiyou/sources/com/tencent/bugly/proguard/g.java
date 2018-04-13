package com.tencent.bugly.proguard;

import android.support.v4.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;

public final class g extends m {
    static byte[] k = null;
    static Map<String, String> l = null;
    static final /* synthetic */ boolean m;
    public short a = (short) 0;
    public byte b = (byte) 0;
    public int c = 0;
    public int d = 0;
    public String e = null;
    public String f = null;
    public byte[] g;
    public int h = 0;
    public Map<String, String> i;
    public Map<String, String> j;

    static {
        boolean z;
        if (g.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        m = z;
    }

    public boolean equals(Object obj) {
        g gVar = (g) obj;
        if (n.a(1, gVar.a) && n.a(1, gVar.b) && n.a(1, gVar.c) && n.a(1, gVar.d) && n.a(Integer.valueOf(1), gVar.e) && n.a(Integer.valueOf(1), gVar.f) && n.a(Integer.valueOf(1), gVar.g) && n.a(1, gVar.h) && n.a(Integer.valueOf(1), gVar.i) && n.a(Integer.valueOf(1), gVar.j)) {
            return true;
        }
        return false;
    }

    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            if (!m) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public void a(l lVar) {
        lVar.a(this.a, 1);
        lVar.b(this.b, 2);
        lVar.a(this.c, 3);
        lVar.a(this.d, 4);
        lVar.a(this.e, 5);
        lVar.a(this.f, 6);
        lVar.a(this.g, 7);
        lVar.a(this.h, 8);
        lVar.a(this.i, 9);
        lVar.a(this.j, 10);
    }

    public void a(k kVar) {
        try {
            this.a = kVar.a(this.a, 1, true);
            this.b = kVar.a(this.b, 2, true);
            this.c = kVar.a(this.c, 3, true);
            this.d = kVar.a(this.d, 4, true);
            this.e = kVar.a(5, true);
            this.f = kVar.a(6, true);
            if (k == null) {
                k = new byte[]{(byte) 0};
            }
            this.g = kVar.a(k, 7, true);
            this.h = kVar.a(this.h, 8, true);
            if (l == null) {
                l = new HashMap();
                l.put("", "");
            }
            this.i = (Map) kVar.a(l, 9, true);
            if (l == null) {
                l = new HashMap();
                l.put("", "");
            }
            this.j = (Map) kVar.a(l, 10, true);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("RequestPacket decode error " + f.a(this.g));
            throw new RuntimeException(e);
        }
    }

    public void a(StringBuilder stringBuilder, int i) {
        i iVar = new i(stringBuilder, i);
        iVar.a(this.a, "iVersion");
        iVar.a(this.b, "cPacketType");
        iVar.a(this.c, "iMessageType");
        iVar.a(this.d, "iRequestId");
        iVar.a(this.e, "sServantName");
        iVar.a(this.f, "sFuncName");
        iVar.a(this.g, "sBuffer");
        iVar.a(this.h, "iTimeout");
        iVar.a(this.i, com.umeng.analytics.b.g.aI);
        iVar.a(this.j, NotificationCompat.CATEGORY_STATUS);
    }
}
