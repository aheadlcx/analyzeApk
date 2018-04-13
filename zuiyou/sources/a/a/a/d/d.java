package a.a.a.d;

import a.a.a.j;

public class d extends j {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    protected int f = 0;

    public d(int i) {
        this.f = i;
    }

    public d(int i, String str) {
        super(str);
        this.f = i;
    }

    public d(String str) {
        super(str);
    }

    public d(int i, Throwable th) {
        super(th);
        this.f = i;
    }

    public d(Throwable th) {
        super(th);
    }

    public d(String str, Throwable th) {
        super(str, th);
    }

    public d(int i, String str, Throwable th) {
        super(str, th);
        this.f = i;
    }

    public int a() {
        return this.f;
    }
}
