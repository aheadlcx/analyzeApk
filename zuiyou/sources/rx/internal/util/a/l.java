package rx.internal.util.a;

public final class l<E> extends q<E> {
    public l(int i) {
        super(i);
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        Object[] objArr = this.c;
        long j = this.producerIndex;
        long a = a(j);
        if (b(objArr, a) != null) {
            return false;
        }
        b(objArr, a, e);
        c(1 + j);
        return true;
    }

    public E poll() {
        long j = this.consumerIndex;
        long a = a(j);
        Object[] objArr = this.c;
        E b = b(objArr, a);
        if (b == null) {
            return null;
        }
        b(objArr, a, null);
        d(j + 1);
        return b;
    }

    public E peek() {
        return b(a(this.consumerIndex));
    }

    public int size() {
        long b = b();
        while (true) {
            long a = a();
            long b2 = b();
            if (b == b2) {
                return (int) (a - b2);
            }
            b = b2;
        }
    }

    public boolean isEmpty() {
        return a() == b();
    }

    private void c(long j) {
        y.a.putOrderedLong(this, f, j);
    }

    private void d(long j) {
        y.a.putOrderedLong(this, e, j);
    }

    private long a() {
        return y.a.getLongVolatile(this, f);
    }

    private long b() {
        return y.a.getLongVolatile(this, e);
    }
}
