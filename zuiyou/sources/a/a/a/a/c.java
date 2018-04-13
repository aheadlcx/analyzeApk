package a.a.a.a;

import java.io.Serializable;

public class c implements Serializable {
    private final boolean a;
    public final byte b;
    private final String c;
    private final boolean d;

    public c(byte b, boolean z) {
        this.b = b;
        this.a = false;
        this.c = null;
        this.d = z;
    }

    public c(byte b) {
        this(b, false);
    }

    public c(byte b, String str) {
        this.b = b;
        this.a = true;
        this.c = str;
        this.d = false;
    }

    public boolean a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        return this.b == (byte) 12;
    }

    public boolean d() {
        return this.b == (byte) 15 || this.b == (byte) 13 || this.b == (byte) 14;
    }

    public boolean e() {
        return this.d;
    }
}
