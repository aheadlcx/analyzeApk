package com.umeng.analytics.pro;

import com.umeng.analytics.pro.cz.a;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class cp {
    private final ByteArrayOutputStream a;
    private final dr b;
    private df c;

    public cp() {
        this(new a());
    }

    public cp(dh dhVar) {
        this.a = new ByteArrayOutputStream();
        this.b = new dr(this.a);
        this.c = dhVar.a(this.b);
    }

    public byte[] a(cg cgVar) throws cm {
        this.a.reset();
        cgVar.b(this.c);
        return this.a.toByteArray();
    }

    public String a(cg cgVar, String str) throws cm {
        try {
            return new String(a(cgVar), str);
        } catch (UnsupportedEncodingException e) {
            throw new cm("JVM DOES NOT SUPPORT ENCODING: " + str);
        }
    }

    public String b(cg cgVar) throws cm {
        return new String(a(cgVar));
    }
}
