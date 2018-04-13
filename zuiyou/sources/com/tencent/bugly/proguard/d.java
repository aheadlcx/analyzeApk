package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class d extends c {
    protected HashMap<String, byte[]> e = null;
    k f = new k();
    private HashMap<String, Object> g = new HashMap();

    public void b() {
        this.e = new HashMap();
    }

    public <T> void a(String str, T t) {
        if (this.e == null) {
            super.a(str, (Object) t);
        } else if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            l lVar = new l();
            lVar.a(this.c);
            lVar.a((Object) t, 0);
            this.e.put(str, n.a(lVar.a()));
        }
    }

    public <T> T b(String str, T t) throws b {
        T a;
        if (this.e != null) {
            if (!this.e.containsKey(str)) {
                return null;
            }
            if (this.g.containsKey(str)) {
                return this.g.get(str);
            }
            try {
                a = a((byte[]) this.e.get(str), (Object) t);
                if (a == null) {
                    return a;
                }
                c(str, a);
                return a;
            } catch (Exception e) {
                throw new b(e);
            }
        } else if (!this.a.containsKey(str)) {
            return null;
        } else {
            if (this.g.containsKey(str)) {
                return this.g.get(str);
            }
            byte[] bArr;
            byte[] bArr2 = new byte[0];
            Iterator it = ((HashMap) this.a.get(str)).entrySet().iterator();
            if (it.hasNext()) {
                Entry entry = (Entry) it.next();
                String str2 = (String) entry.getKey();
                bArr = (byte[]) entry.getValue();
            } else {
                bArr = bArr2;
            }
            try {
                this.f.a(bArr);
                this.f.a(this.c);
                a = this.f.a((Object) t, 0, true);
                c(str, a);
                return a;
            } catch (Exception e2) {
                throw new b(e2);
            }
        }
    }

    private Object a(byte[] bArr, Object obj) {
        this.f.a(bArr);
        this.f.a(this.c);
        return this.f.a(obj, 0, true);
    }

    private void c(String str, Object obj) {
        this.g.put(str, obj);
    }

    public byte[] a() {
        if (this.e == null) {
            return super.a();
        }
        l lVar = new l(0);
        lVar.a(this.c);
        lVar.a(this.e, 0);
        return n.a(lVar.a());
    }

    public void a(byte[] bArr) {
        try {
            super.a(bArr);
        } catch (Exception e) {
            this.f.a(bArr);
            this.f.a(this.c);
            Map hashMap = new HashMap(1);
            hashMap.put("", new byte[0]);
            this.e = this.f.a(hashMap, 0, false);
        }
    }
}
