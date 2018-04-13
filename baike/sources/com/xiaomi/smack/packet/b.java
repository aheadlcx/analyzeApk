package com.xiaomi.smack.packet;

import android.os.Bundle;
import com.alipay.sdk.util.j;
import com.xiaomi.smack.util.d;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class b extends d {
    private a c = a.a;
    private final Map<String, String> d = new HashMap();

    public static class a {
        public static final a a = new a("get");
        public static final a b = new a("set");
        public static final a c = new a(j.c);
        public static final a d = new a(com.umeng.analytics.pro.b.J);
        public static final a e = new a("command");
        private String f;

        private a(String str) {
            this.f = str;
        }

        public static a a(String str) {
            if (str == null) {
                return null;
            }
            String toLowerCase = str.toLowerCase();
            return a.toString().equals(toLowerCase) ? a : b.toString().equals(toLowerCase) ? b : d.toString().equals(toLowerCase) ? d : c.toString().equals(toLowerCase) ? c : e.toString().equals(toLowerCase) ? e : null;
        }

        public String toString() {
            return this.f;
        }
    }

    public b(Bundle bundle) {
        super(bundle);
        if (bundle.containsKey("ext_iq_type")) {
            this.c = a.a(bundle.getString("ext_iq_type"));
        }
    }

    public a a() {
        return this.c;
    }

    public void a(a aVar) {
        if (aVar == null) {
            this.c = a.a;
        } else {
            this.c = aVar;
        }
    }

    public synchronized void a(Map<String, String> map) {
        this.d.putAll(map);
    }

    public Bundle b() {
        Bundle b = super.b();
        if (this.c != null) {
            b.putString("ext_iq_type", this.c.toString());
        }
        return b;
    }

    public String c() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<iq ");
        if (k() != null) {
            stringBuilder.append("id=\"" + k() + "\" ");
        }
        if (m() != null) {
            stringBuilder.append("to=\"").append(d.a(m())).append("\" ");
        }
        if (n() != null) {
            stringBuilder.append("from=\"").append(d.a(n())).append("\" ");
        }
        if (l() != null) {
            stringBuilder.append("chid=\"").append(d.a(l())).append("\" ");
        }
        for (Entry entry : this.d.entrySet()) {
            stringBuilder.append(d.a((String) entry.getKey())).append("=\"");
            stringBuilder.append(d.a((String) entry.getValue())).append("\" ");
        }
        if (this.c == null) {
            stringBuilder.append("type=\"get\">");
        } else {
            stringBuilder.append("type=\"").append(a()).append("\">");
        }
        String d = d();
        if (d != null) {
            stringBuilder.append(d);
        }
        stringBuilder.append(s());
        h p = p();
        if (p != null) {
            stringBuilder.append(p.b());
        }
        stringBuilder.append("</iq>");
        return stringBuilder.toString();
    }

    public String d() {
        return null;
    }
}
