package rx.internal.util.a;

abstract class e<E> extends g<E> {
    protected static final long d = y.a(e.class, "consumerIndex");
    private volatile long consumerIndex;

    public e(int i) {
        super(i);
    }

    protected final long a() {
        return this.consumerIndex;
    }

    protected final boolean b(long j, long j2) {
        return y.a.compareAndSwapLong(this, d, j, j2);
    }
}
