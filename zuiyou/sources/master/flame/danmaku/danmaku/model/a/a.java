package master.flame.danmaku.danmaku.model.a;

class a<T extends c<T>> implements b<T> {
    private final d<T> a;
    private final int b;
    private final boolean c;
    private T d;
    private int e;

    a(d<T> dVar, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The pool limit must be > 0");
        }
        this.a = dVar;
        this.b = i;
        this.c = false;
    }

    public T a() {
        T t;
        if (this.d != null) {
            T t2 = this.d;
            this.d = (c) t2.l();
            this.e--;
            t = t2;
        } else {
            t = this.a.b();
        }
        if (t != null) {
            t.a(null);
            t.a(false);
            this.a.b(t);
        }
        return t;
    }

    public void a(T t) {
        if (t.j()) {
            System.out.print("[FinitePool] Element is already in pool: " + t);
            return;
        }
        if (this.c || this.e < this.b) {
            this.e++;
            t.a(this.d);
            t.a(true);
            this.d = t;
        }
        this.a.a(t);
    }
}
