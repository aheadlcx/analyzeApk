package com.loc;

@ag(a = "e")
public class ar {
    @ah(a = "c1", b = 2)
    private int a;
    @ah(a = "c2", b = 2)
    private int b;
    @ah(a = "c3", b = 2)
    private int c;

    public final void a(boolean z) {
        this.a = z ? 1 : 0;
    }

    public final boolean a() {
        return this.a == 1;
    }

    public final void b(boolean z) {
        this.b = z ? 1 : 0;
    }

    public final boolean b() {
        return this.b == 1;
    }

    public final void c(boolean z) {
        this.c = z ? 1 : 0;
    }

    public final boolean c() {
        return this.c == 1;
    }
}
