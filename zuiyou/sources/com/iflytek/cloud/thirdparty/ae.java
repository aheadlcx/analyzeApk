package com.iflytek.cloud.thirdparty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ae {
    private static ae a;
    private String b = "";
    private Map<String, a> c = new HashMap();

    public static class a {
        public Long a;
        public Long b;
        public Map<String, List<ad>> c;
        public String d;
        private float e;

        public a(Long l, Long l2, String str, Map<String, List<ad>> map) {
            this.a = l;
            this.b = l2;
            this.d = str;
            this.c = map;
        }
    }

    private ae() {
    }

    public static ae a() {
        if (a == null) {
            a = new ae();
        }
        return a;
    }

    public float a(String str) {
        a aVar = (a) this.c.get(str);
        return aVar == null ? 0.0f : aVar.e;
    }

    public ad a(String str, String str2) {
        List b = b(str, str2);
        return b == null ? null : (ad) b.get(b.size() - 1);
    }

    public void a(String str, float f) {
        a aVar = (a) this.c.get(str);
        if (aVar != null) {
            aVar.e = f;
        }
    }

    public void a(String str, long j) {
        a aVar = (a) this.c.get(str);
        if (aVar != null) {
            aVar.b = Long.valueOf(j);
        }
    }

    public void a(String str, a aVar) {
        this.c.put(str, aVar);
    }

    public void a(String str, String str2, ad adVar) {
        a aVar = (a) this.c.get(str);
        if (aVar != null) {
            Map map = aVar.c;
            if (map != null) {
                List list = (List) map.get(str2);
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(adVar);
                map.put(str2, list);
            }
        }
    }

    public Long b(String str) {
        a aVar = (a) this.c.get(str);
        return aVar == null ? null : aVar.a;
    }

    public List<ad> b(String str, String str2) {
        a aVar = (a) this.c.get(str);
        if (aVar != null) {
            Map map = aVar.c;
            if (map != null) {
                return (List) map.get(str2);
            }
        }
        return null;
    }

    public void b() {
        this.c.clear();
        this.b = "";
    }

    public Long c(String str) {
        a aVar = (a) this.c.get(str);
        return aVar == null ? null : aVar.b;
    }

    public boolean c() {
        a aVar = (a) this.c.get(this.b);
        return (aVar == null || aVar.c == null || aVar.c.size() != 0) ? false : true;
    }

    public boolean c(String str, String str2) {
        a aVar = (a) this.c.get(str);
        if (aVar != null) {
            Map map = aVar.c;
            if (map != null) {
                List<ad> list = (List) map.get(str2);
                if (list != null) {
                    for (ad f : list) {
                        if (f.f()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public String d(String str) {
        a aVar = (a) this.c.get(str);
        return aVar == null ? "" : aVar.d;
    }

    public void d(String str, String str2) {
        synchronized (this) {
            a aVar = (a) this.c.get(str);
            if (aVar != null) {
                aVar.d = str2;
            }
        }
    }

    public boolean e(String str) {
        return this.c.containsKey(str);
    }

    public void f(String str) {
        this.c.remove(str);
    }
}
