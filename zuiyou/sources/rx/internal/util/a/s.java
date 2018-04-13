package rx.internal.util.a;

import java.util.Iterator;

public class s<E> extends u<E> {
    static final int a = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    private static final long h;
    private static final long i;
    private static final long j = ((long) y.a.arrayBaseOffset(Object[].class));
    private static final int k;
    private static final Object l = new Object();

    static {
        InternalError internalError;
        int arrayIndexScale = y.a.arrayIndexScale(Object[].class);
        if (4 == arrayIndexScale) {
            k = 2;
        } else if (8 == arrayIndexScale) {
            k = 3;
        } else {
            throw new IllegalStateException("Unknown pointer size");
        }
        try {
            h = y.a.objectFieldOffset(x.class.getDeclaredField("producerIndex"));
            try {
                i = y.a.objectFieldOffset(u.class.getDeclaredField("consumerIndex"));
            } catch (Throwable e) {
                internalError = new InternalError();
                internalError.initCause(e);
                throw internalError;
            }
        } catch (Throwable e2) {
            internalError = new InternalError();
            internalError.initCause(e2);
            throw internalError;
        }
    }

    public s(int i) {
        int a = c.a(i);
        long j = (long) (a - 1);
        Object[] objArr = new Object[(a + 1)];
        this.g = objArr;
        this.f = j;
        a(a);
        this.c = objArr;
        this.b = j;
        this.e = j - 1;
        a(0);
    }

    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public final boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        Object[] objArr = this.g;
        long j = this.producerIndex;
        long j2 = this.f;
        long a = a(j, j2);
        if (j < this.e) {
            return a(objArr, e, j, a);
        }
        int i = this.d;
        if (a(objArr, a(((long) i) + j, j2)) == null) {
            this.e = (((long) i) + j) - 1;
            return a(objArr, e, j, a);
        } else if (a(objArr, a(1 + j, j2)) != null) {
            return a(objArr, e, j, a);
        } else {
            a(objArr, j, a, e, j2);
            return true;
        }
    }

    private boolean a(E[] eArr, E e, long j, long j2) {
        a((Object[]) eArr, j2, (Object) e);
        a(1 + j);
        return true;
    }

    private void a(E[] eArr, long j, long j2, E e, long j3) {
        Object[] objArr = new Object[eArr.length];
        this.g = objArr;
        this.e = (j + j3) - 1;
        a(objArr, j2, (Object) e);
        a((Object[]) eArr, objArr);
        a((Object[]) eArr, j2, l);
        a(j + 1);
    }

    private void a(E[] eArr, E[] eArr2) {
        a((Object[]) eArr, c((long) (eArr.length - 1)), (Object) eArr2);
    }

    private E[] a(E[] eArr) {
        return (Object[]) a((Object[]) eArr, c((long) (eArr.length - 1)));
    }

    public final E poll() {
        Object[] objArr = this.c;
        long j = this.consumerIndex;
        long j2 = this.b;
        long a = a(j, j2);
        E a2 = a(objArr, a);
        Object obj = a2 == l ? 1 : null;
        if (a2 == null || obj != null) {
            return obj != null ? a(a(objArr), j, j2) : null;
        } else {
            a(objArr, a, null);
            b(j + 1);
            return a2;
        }
    }

    private E a(E[] eArr, long j, long j2) {
        this.c = eArr;
        long a = a(j, j2);
        E a2 = a((Object[]) eArr, a);
        if (a2 == null) {
            return null;
        }
        a((Object[]) eArr, a, null);
        b(1 + j);
        return a2;
    }

    public final E peek() {
        Object[] objArr = this.c;
        long j = this.consumerIndex;
        long j2 = this.b;
        E a = a(objArr, a(j, j2));
        if (a == l) {
            return b(a(objArr), j, j2);
        }
        return a;
    }

    private E b(E[] eArr, long j, long j2) {
        this.c = eArr;
        return a((Object[]) eArr, a(j, j2));
    }

    public final int size() {
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

    private void a(int i) {
        this.d = Math.min(i / 4, a);
    }

    private long a() {
        return y.a.getLongVolatile(this, h);
    }

    private long b() {
        return y.a.getLongVolatile(this, i);
    }

    private void a(long j) {
        y.a.putOrderedLong(this, h, j);
    }

    private void b(long j) {
        y.a.putOrderedLong(this, i, j);
    }

    private static long a(long j, long j2) {
        return c(j & j2);
    }

    private static long c(long j) {
        return j + (j << k);
    }

    private static void a(Object[] objArr, long j, Object obj) {
        y.a.putOrderedObject(objArr, j, obj);
    }

    private static <E> Object a(E[] eArr, long j) {
        return y.a.getObjectVolatile(eArr, j);
    }
}
