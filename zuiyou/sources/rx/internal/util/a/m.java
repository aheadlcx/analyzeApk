package rx.internal.util.a;

abstract class m<E> extends a<E> {
    private static final Integer e = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    protected final int d;

    public m(int i) {
        super(i);
        this.d = Math.min(i / 4, e.intValue());
    }
}
