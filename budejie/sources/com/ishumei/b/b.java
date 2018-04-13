package com.ishumei.b;

import com.ishumei.f.d;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private Map<String, a> a;
    private Map<String, b> b;
    private Map<String, c> c;
    private Set<String> d;
    private String e;
    private String f;
    private String g;
    private boolean h = true;
    private boolean i = true;
    private int j = 20;
    private int k = 10;

    public static class a {
        private String a;
        private String b;
        private String c;

        public String a() {
            return this.a;
        }

        public void a(String str) {
            this.a = str;
        }

        public String b() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public void c(String str) {
            this.c = str;
        }
    }

    public static class b {
        private String a;
        private int b;
        private String c;

        public String a() {
            return this.a;
        }

        public void a(int i) {
            this.b = i;
        }

        public void a(String str) {
            this.a = str;
        }

        public String b() {
            return this.c;
        }

        public void b(String str) {
            this.c = str;
        }

        public int c() {
            return this.b;
        }
    }

    public static class c {
        private String a;
        private String b;

        public String a() {
            return this.a;
        }

        public void a(String str) {
            this.a = str;
        }

        public String b() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }
    }

    public static b a(b bVar) {
        if (bVar == null) {
            return null;
        }
        b bVar2 = new b();
        bVar2.a(bVar.e());
        bVar2.b(bVar.f());
        bVar2.c(bVar.g());
        bVar2.a(bVar.h());
        bVar2.b(bVar.i());
        bVar2.c(bVar.j());
        bVar2.a(bVar.k());
        bVar2.b(bVar.d());
        bVar2.a(bVar.c());
        return bVar2;
    }

    public static Map<String, a> a(JSONArray jSONArray) {
        Map<String, a> hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                a aVar = new a();
                String str = (String) jSONObject.keys().next();
                jSONObject = jSONObject.getJSONObject(str);
                aVar.a(str);
                aVar.b(jSONObject.getString("pn"));
                aVar.c(jSONObject.getString("uri"));
                hashMap.put(aVar.a(), aVar);
            } catch (JSONException e) {
            }
        }
        return hashMap;
    }

    public static Set<String> a(JSONObject jSONObject) {
        Set<String> hashSet = new HashSet();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String str = (String) keys.next();
                if (str.startsWith("sensitive.") && jSONObject.getBoolean(str)) {
                    hashSet.add(str.split("\\.")[1]);
                }
            } catch (Throwable e) {
                com.ishumei.f.c.c("CollectConfiguration", "parse sensitives failed");
                com.ishumei.f.c.a(e);
            }
        }
        return hashSet;
    }

    public static Map<String, b> b(JSONArray jSONArray) {
        Map<String, b> hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                b bVar = new b();
                String str = (String) jSONObject.keys().next();
                jSONObject = jSONObject.getJSONObject(str);
                bVar.a(str);
                if (d.a("sdcard", jSONObject.getString("type"))) {
                    bVar.a(0);
                } else if (d.a("absolute", jSONObject.getString("type"))) {
                    bVar.a(1);
                } else {
                }
                bVar.b(jSONObject.getString("dir"));
                hashMap.put(bVar.a(), bVar);
            } catch (JSONException e) {
            }
        }
        return hashMap;
    }

    public static Map<String, c> c(JSONArray jSONArray) {
        Map<String, c> hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                c cVar = new c();
                String str = (String) jSONObject.keys().next();
                jSONObject = jSONObject.getJSONObject(str);
                cVar.a(str);
                cVar.b(jSONObject.getString("pn"));
                hashMap.put(cVar.a(), cVar);
                hashMap.put(cVar.a(), cVar);
            } catch (JSONException e) {
            }
        }
        return hashMap;
    }

    public static Map<String, a> c(JSONObject jSONObject) {
        Map<String, a> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                a aVar = new a();
                String str = (String) keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                aVar.a(str);
                aVar.b(jSONObject2.getString("pn"));
                aVar.c(jSONObject2.getString("uri"));
                hashMap.put(aVar.a(), aVar);
            } catch (Throwable e) {
                com.ishumei.f.c.c("CollectConfiguration", "parse risk app failed");
                com.ishumei.f.c.a(e);
            }
        }
        return hashMap;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ishumei.b.b d(java.lang.String r3) {
        /*
        r1 = new com.ishumei.b.b;
        r1.<init>();
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x005d }
        r2.<init>(r3);	 Catch:{ Exception -> 0x005d }
        r1.b(r2);	 Catch:{ Exception -> 0x0058 }
    L_0x000d:
        r0 = "risk_apps";
        r0 = r2.getJSONArray(r0);	 Catch:{ Exception -> 0x0064 }
        r0 = a(r0);	 Catch:{ Exception -> 0x0064 }
        r1.a(r0);	 Catch:{ Exception -> 0x0064 }
    L_0x001a:
        r0 = "risk_dirs";
        r0 = r2.getJSONArray(r0);	 Catch:{ Exception -> 0x0069 }
        r0 = b(r0);	 Catch:{ Exception -> 0x0069 }
        r1.b(r0);	 Catch:{ Exception -> 0x0069 }
    L_0x0027:
        r0 = "white_apps";
        r0 = r2.getJSONArray(r0);	 Catch:{ Exception -> 0x006e }
        r0 = c(r0);	 Catch:{ Exception -> 0x006e }
        r1.c(r0);	 Catch:{ Exception -> 0x006e }
    L_0x0034:
        r0 = a(r2);	 Catch:{ Exception -> 0x0073 }
        r1.a(r0);	 Catch:{ Exception -> 0x0073 }
    L_0x003b:
        r0 = "core_atamper";
        r0 = r2.getBoolean(r0);	 Catch:{ Exception -> 0x0078 }
        r1.a(r0);	 Catch:{ Exception -> 0x0078 }
    L_0x0044:
        r0 = "all_atamper";
        r0 = r2.getBoolean(r0);	 Catch:{ Exception -> 0x007d }
        r1.b(r0);	 Catch:{ Exception -> 0x007d }
    L_0x004d:
        r1.c(r3);	 Catch:{ Exception -> 0x005d }
        r0 = com.ishumei.f.e.f(r3);	 Catch:{ Exception -> 0x005d }
        r1.b(r0);	 Catch:{ Exception -> 0x005d }
        return r1;
    L_0x0058:
        r0 = move-exception;
        com.ishumei.f.c.a(r0);	 Catch:{ Exception -> 0x005d }
        goto L_0x000d;
    L_0x005d:
        r0 = move-exception;
        r1 = new java.io.IOException;
        r1.<init>(r0);
        throw r1;
    L_0x0064:
        r0 = move-exception;
        com.ishumei.f.c.a(r0);	 Catch:{ Exception -> 0x005d }
        goto L_0x001a;
    L_0x0069:
        r0 = move-exception;
        com.ishumei.f.c.a(r0);	 Catch:{ Exception -> 0x005d }
        goto L_0x0027;
    L_0x006e:
        r0 = move-exception;
        com.ishumei.f.c.a(r0);	 Catch:{ Exception -> 0x005d }
        goto L_0x0034;
    L_0x0073:
        r0 = move-exception;
        com.ishumei.f.c.a(r0);	 Catch:{ Exception -> 0x005d }
        goto L_0x003b;
    L_0x0078:
        r0 = move-exception;
        com.ishumei.f.c.a(r0);	 Catch:{ Exception -> 0x005d }
        goto L_0x0044;
    L_0x007d:
        r0 = move-exception;
        com.ishumei.f.c.a(r0);	 Catch:{ Exception -> 0x005d }
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.b.b.d(java.lang.String):com.ishumei.b.b");
    }

    public static Map<String, b> d(JSONObject jSONObject) {
        Map<String, b> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                b bVar = new b();
                String str = (String) keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                bVar.a(str);
                if (d.a("sdcard", jSONObject2.getString("type"))) {
                    bVar.a(0);
                } else if (d.a("absolute", jSONObject2.getString("type"))) {
                    bVar.a(1);
                }
                bVar.b(jSONObject2.getString("dir"));
                hashMap.put(bVar.a(), bVar);
            } catch (Throwable e) {
                com.ishumei.f.c.c("CollectConfiguration", "parse risk dir failed");
                com.ishumei.f.c.a(e);
            }
        }
        return hashMap;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ishumei.b.b e(java.lang.String r3) {
        /*
        r1 = new com.ishumei.b.b;
        r1.<init>();
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0060 }
        r2.<init>(r3);	 Catch:{ Exception -> 0x0060 }
        r0 = "risk_apps";
        r0 = r2.getJSONObject(r0);	 Catch:{ Exception -> 0x005b }
        r0 = c(r0);	 Catch:{ Exception -> 0x005b }
        r1.a(r0);	 Catch:{ Exception -> 0x005b }
    L_0x0017:
        r0 = "risk_dirs";
        r0 = r2.getJSONObject(r0);	 Catch:{ Exception -> 0x0067 }
        r0 = d(r0);	 Catch:{ Exception -> 0x0067 }
        r1.b(r0);	 Catch:{ Exception -> 0x0067 }
    L_0x0024:
        r0 = "white_apps";
        r0 = r2.getJSONObject(r0);	 Catch:{ Exception -> 0x006c }
        r0 = e(r0);	 Catch:{ Exception -> 0x006c }
        r1.c(r0);	 Catch:{ Exception -> 0x006c }
    L_0x0031:
        r0 = "sensitive";
        r0 = r2.getJSONObject(r0);	 Catch:{ Exception -> 0x0071 }
        r0 = f(r0);	 Catch:{ Exception -> 0x0071 }
        r1.a(r0);	 Catch:{ Exception -> 0x0071 }
    L_0x003e:
        r0 = "core_atamper";
        r0 = r2.getBoolean(r0);	 Catch:{ Exception -> 0x0076 }
        r1.a(r0);	 Catch:{ Exception -> 0x0076 }
    L_0x0047:
        r0 = "all_atamper";
        r0 = r2.getBoolean(r0);	 Catch:{ Exception -> 0x007b }
        r1.b(r0);	 Catch:{ Exception -> 0x007b }
    L_0x0050:
        r1.c(r3);	 Catch:{ Exception -> 0x0060 }
        r0 = com.ishumei.f.e.f(r3);	 Catch:{ Exception -> 0x0060 }
        r1.b(r0);	 Catch:{ Exception -> 0x0060 }
        return r1;
    L_0x005b:
        r0 = move-exception;
        com.ishumei.f.c.a(r0);	 Catch:{ Exception -> 0x0060 }
        goto L_0x0017;
    L_0x0060:
        r0 = move-exception;
        r1 = new java.io.IOException;
        r1.<init>(r0);
        throw r1;
    L_0x0067:
        r0 = move-exception;
        com.ishumei.f.c.a(r0);	 Catch:{ Exception -> 0x0060 }
        goto L_0x0024;
    L_0x006c:
        r0 = move-exception;
        com.ishumei.f.c.a(r0);	 Catch:{ Exception -> 0x0060 }
        goto L_0x0031;
    L_0x0071:
        r0 = move-exception;
        com.ishumei.f.c.a(r0);	 Catch:{ Exception -> 0x0060 }
        goto L_0x003e;
    L_0x0076:
        r0 = move-exception;
        com.ishumei.f.c.a(r0);	 Catch:{ Exception -> 0x0060 }
        goto L_0x0047;
    L_0x007b:
        r0 = move-exception;
        com.ishumei.f.c.a(r0);	 Catch:{ Exception -> 0x0060 }
        goto L_0x0050;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.b.b.e(java.lang.String):com.ishumei.b.b");
    }

    public static Map<String, c> e(JSONObject jSONObject) {
        Map<String, c> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                c cVar = new c();
                String str = (String) keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                cVar.a(str);
                cVar.b(jSONObject2.getString("pn"));
                hashMap.put(cVar.a(), cVar);
            } catch (Throwable e) {
                com.ishumei.f.c.c("CollectConfiguration", "parse white app failed");
                com.ishumei.f.c.a(e);
            }
        }
        return hashMap;
    }

    public static Set<String> f(JSONObject jSONObject) {
        Set<String> hashSet = new HashSet();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String str = (String) keys.next();
                if (jSONObject.getBoolean(str)) {
                    hashSet.add(str);
                }
            } catch (Throwable e) {
                com.ishumei.f.c.c("CollectConfiguration", "parse sensitives failed");
                com.ishumei.f.c.a(e);
            }
        }
        return hashSet;
    }

    public int a() {
        return this.j;
    }

    public void a(int i) {
        this.j = i;
    }

    public void a(String str) {
        this.g = str;
    }

    public void a(Map<String, a> map) {
        this.a = map;
    }

    public void a(Set<String> set) {
        this.d = set;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public int b() {
        return this.k;
    }

    public void b(int i) {
        this.k = i;
    }

    public void b(String str) {
        this.f = str;
    }

    public void b(Map<String, b> map) {
        this.b = map;
    }

    public void b(JSONObject jSONObject) {
        if (jSONObject.has("usrappcnt")) {
            a(jSONObject.getInt("usrappcnt"));
        }
        if (jSONObject.has("sysappcnt")) {
            b(jSONObject.getInt("sysappcnt"));
        }
    }

    public void b(boolean z) {
        this.i = z;
    }

    public void c(String str) {
        this.e = str;
    }

    public void c(Map<String, c> map) {
        this.c = map;
    }

    public boolean c() {
        return this.h;
    }

    public boolean d() {
        return this.i;
    }

    public String e() {
        return this.g;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.e;
    }

    public Map<String, a> h() {
        return this.a;
    }

    public Map<String, b> i() {
        return this.b;
    }

    public Map<String, c> j() {
        return this.c;
    }

    public Set<String> k() {
        return this.d;
    }
}
