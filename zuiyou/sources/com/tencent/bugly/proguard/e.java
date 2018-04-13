package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;
import java.util.HashMap;

public class e extends d {
    static HashMap<String, byte[]> h = null;
    static HashMap<String, HashMap<String, byte[]>> i = null;
    protected g g = new g();
    private int j = 0;

    public <T> void a(String str, T t) {
        if (str.startsWith(".")) {
            throw new IllegalArgumentException("put name can not startwith . , now is " + str);
        }
        super.a(str, (Object) t);
    }

    public void b() {
        super.b();
        this.g.a = (short) 3;
    }

    public byte[] a() {
        if (this.g.a != (short) 2) {
            if (this.g.e == null) {
                this.g.e = "";
            }
            if (this.g.f == null) {
                this.g.f = "";
            }
        } else if (this.g.e.equals("")) {
            throw new IllegalArgumentException("servantName can not is null");
        } else if (this.g.f.equals("")) {
            throw new IllegalArgumentException("funcName can not is null");
        }
        l lVar = new l(0);
        lVar.a(this.c);
        if (this.g.a == (short) 2) {
            lVar.a(this.a, 0);
        } else {
            lVar.a(this.e, 0);
        }
        this.g.g = n.a(lVar.a());
        lVar = new l(0);
        lVar.a(this.c);
        a(lVar);
        byte[] a = n.a(lVar.a());
        int length = a.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + 4);
        allocate.putInt(length + 4).put(a).flip();
        return allocate.array();
    }

    public void a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            k kVar = new k(bArr, 4);
            kVar.a(this.c);
            a(kVar);
            if (this.g.a == (short) 3) {
                kVar = new k(this.g.g);
                kVar.a(this.c);
                if (h == null) {
                    h = new HashMap();
                    h.put("", new byte[0]);
                }
                this.e = kVar.a(h, 0, false);
                return;
            }
            kVar = new k(this.g.g);
            kVar.a(this.c);
            if (i == null) {
                i = new HashMap();
                HashMap hashMap = new HashMap();
                hashMap.put("", new byte[0]);
                i.put("", hashMap);
            }
            this.a = kVar.a(i, 0, false);
            this.b = new HashMap();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void b(String str) {
        this.g.e = str;
    }

    public void c(String str) {
        this.g.f = str;
    }

    public void a(int i) {
        this.g.d = i;
    }

    public void a(l lVar) {
        this.g.a(lVar);
    }

    public void a(k kVar) {
        this.g.a(kVar);
    }
}
