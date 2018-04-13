package cn.xiaochuankeji.tieba.background.utils.a;

public class b {
    private final int a;
    private int b;
    private int c;
    private long d;

    public b() {
        this(0);
    }

    public b(int i) {
        this.b = 1;
        this.a = i;
    }

    public void a(int i) {
        this.b = i;
    }

    public int a() {
        return this.b;
    }

    public void b() {
        this.d = System.nanoTime();
    }

    public boolean c() {
        return this.d > 0;
    }

    public void d() {
        if (this.d > 0) {
            this.c = ((int) ((System.nanoTime() - this.d) / 1000000)) + this.c;
        }
        this.d = 0;
    }

    public void b(int i) {
        this.c += i;
    }

    public int e() {
        return this.c;
    }

    public int f() {
        return this.a;
    }
}
