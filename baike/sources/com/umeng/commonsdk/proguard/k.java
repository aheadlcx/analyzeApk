package com.umeng.commonsdk.proguard;

public class k extends r {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    private static final ap j = new ap("TApplicationException");
    private static final af k = new af("message", (byte) 11, (short) 1);
    private static final af l = new af("type", (byte) 8, (short) 2);
    protected int i = 0;

    public k(int i) {
        this.i = i;
    }

    public k(int i, String str) {
        super(str);
        this.i = i;
    }

    public k(String str) {
        super(str);
    }

    public int a() {
        return this.i;
    }

    public static k a(ak akVar) throws r {
        akVar.j();
        String str = null;
        int i = 0;
        while (true) {
            af l = akVar.l();
            if (l.b == (byte) 0) {
                akVar.k();
                return new k(i, str);
            }
            switch (l.c) {
                case (short) 1:
                    if (l.b != (byte) 11) {
                        an.a(akVar, l.b);
                        break;
                    }
                    str = akVar.z();
                    break;
                case (short) 2:
                    if (l.b != (byte) 8) {
                        an.a(akVar, l.b);
                        break;
                    }
                    i = akVar.w();
                    break;
                default:
                    an.a(akVar, l.b);
                    break;
            }
            akVar.m();
        }
    }

    public void b(ak akVar) throws r {
        akVar.a(j);
        if (getMessage() != null) {
            akVar.a(k);
            akVar.a(getMessage());
            akVar.c();
        }
        akVar.a(l);
        akVar.a(this.i);
        akVar.c();
        akVar.d();
        akVar.b();
    }
}
