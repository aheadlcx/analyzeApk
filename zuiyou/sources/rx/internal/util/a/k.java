package rx.internal.util.a;

abstract class k<E> extends i<E> {
    private volatile long f;

    public k(int i) {
        super(i);
    }

    protected final long c() {
        return this.f;
    }

    protected final void d(long j) {
        this.f = j;
    }
}
