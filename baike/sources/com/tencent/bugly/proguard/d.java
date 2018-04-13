package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;
import java.util.HashMap;

public final class d extends c {
    private static HashMap<String, byte[]> f = null;
    private static HashMap<String, HashMap<String, byte[]>> g = null;
    private f e = new f();

    public final <T> void a(String str, T t) {
        if (str.startsWith(".")) {
            throw new IllegalArgumentException("put name can not startwith . , now is " + str);
        }
        super.a(str, t);
    }

    public final void b() {
        super.b();
        this.e.a = (short) 3;
    }

    public final byte[] a() {
        if (this.e.a != (short) 2) {
            if (this.e.c == null) {
                this.e.c = "";
            }
            if (this.e.d == null) {
                this.e.d = "";
            }
        } else if (this.e.c.equals("")) {
            throw new IllegalArgumentException("servantName can not is null");
        } else if (this.e.d.equals("")) {
            throw new IllegalArgumentException("funcName can not is null");
        }
        j jVar = new j(0);
        jVar.a(this.b);
        if (this.e.a == (short) 2) {
            jVar.a(this.a, 0);
        } else {
            jVar.a(this.d, 0);
        }
        this.e.e = l.a(jVar.a());
        jVar = new j(0);
        jVar.a(this.b);
        this.e.a(jVar);
        byte[] a = l.a(jVar.a());
        int length = a.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + 4);
        allocate.putInt(length + 4).put(a).flip();
        return allocate.array();
    }

    public final void a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            i iVar = new i(bArr, 4);
            iVar.a(this.b);
            this.e.a(iVar);
            HashMap hashMap;
            if (this.e.a == (short) 3) {
                iVar = new i(this.e.e);
                iVar.a(this.b);
                if (f == null) {
                    hashMap = new HashMap();
                    f = hashMap;
                    hashMap.put("", new byte[0]);
                }
                this.d = iVar.a(f, 0, false);
                return;
            }
            iVar = new i(this.e.e);
            iVar.a(this.b);
            if (g == null) {
                g = new HashMap();
                hashMap = new HashMap();
                hashMap.put("", new byte[0]);
                g.put("", hashMap);
            }
            this.a = iVar.a(g, 0, false);
            HashMap hashMap2 = new HashMap();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public final void b(String str) {
        this.e.c = str;
    }

    public final void c(String str) {
        this.e.d = str;
    }

    public final void b(int i) {
        this.e.b = 1;
    }
}
