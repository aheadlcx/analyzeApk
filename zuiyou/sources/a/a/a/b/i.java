package a.a.a.b;

import a.a.a.j;

public class i extends j {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    protected int g = 0;

    public i(int i) {
        this.g = i;
    }

    public i(int i, String str) {
        super(str);
        this.g = i;
    }

    public i(String str) {
        super(str);
    }

    public i(int i, Throwable th) {
        super(th);
        this.g = i;
    }

    public i(Throwable th) {
        super(th);
    }

    public i(String str, Throwable th) {
        super(str, th);
    }

    public i(int i, String str, Throwable th) {
        super(str, th);
        this.g = i;
    }

    public int a() {
        return this.g;
    }
}
