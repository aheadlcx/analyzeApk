package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.ae.a;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class u {
    private final ByteArrayOutputStream a;
    private final aw b;
    private ak c;

    public u() {
        this(new a());
    }

    public u(am amVar) {
        this.a = new ByteArrayOutputStream();
        this.b = new aw(this.a);
        this.c = amVar.a(this.b);
    }

    public byte[] a(l lVar) throws r {
        this.a.reset();
        lVar.write(this.c);
        return this.a.toByteArray();
    }

    public String a(l lVar, String str) throws r {
        try {
            return new String(a(lVar), str);
        } catch (UnsupportedEncodingException e) {
            throw new r("JVM DOES NOT SUPPORT ENCODING: " + str);
        }
    }

    public String b(l lVar) throws r {
        return new String(a(lVar));
    }
}
