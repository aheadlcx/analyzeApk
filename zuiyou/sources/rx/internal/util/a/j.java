package rx.internal.util.a;

abstract class j<E> extends f<E> {
    protected static final long e = y.a(j.class, "producerIndex");
    private volatile long producerIndex;

    protected final long b() {
        return this.producerIndex;
    }

    protected final void c(long j) {
        y.a.putOrderedLong(this, e, j);
    }

    public j(int i) {
        super(i);
    }
}
