package com.umeng.analytics.pro;

import com.baidu.mobads.openad.c.b;

public class cf extends cm {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    private static final dk j = new dk("TApplicationException");
    private static final da k = new da(b.EVENT_MESSAGE, (byte) 11, (short) 1);
    private static final da l = new da("type", (byte) 8, (short) 2);
    private static final long m = 1;
    protected int i = 0;

    public cf(int i) {
        this.i = i;
    }

    public cf(int i, String str) {
        super(str);
        this.i = i;
    }

    public cf(String str) {
        super(str);
    }

    public int a() {
        return this.i;
    }

    public static cf a(df dfVar) throws cm {
        dfVar.j();
        String str = null;
        int i = 0;
        while (true) {
            da l = dfVar.l();
            if (l.b == (byte) 0) {
                dfVar.k();
                return new cf(i, str);
            }
            switch (l.c) {
                case (short) 1:
                    if (l.b != (byte) 11) {
                        di.a(dfVar, l.b);
                        break;
                    }
                    str = dfVar.z();
                    break;
                case (short) 2:
                    if (l.b != (byte) 8) {
                        di.a(dfVar, l.b);
                        break;
                    }
                    i = dfVar.w();
                    break;
                default:
                    di.a(dfVar, l.b);
                    break;
            }
            dfVar.m();
        }
    }

    public void b(df dfVar) throws cm {
        dfVar.a(j);
        if (getMessage() != null) {
            dfVar.a(k);
            dfVar.a(getMessage());
            dfVar.c();
        }
        dfVar.a(l);
        dfVar.a(this.i);
        dfVar.c();
        dfVar.d();
        dfVar.b();
    }
}
