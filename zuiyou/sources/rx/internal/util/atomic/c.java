package rx.internal.util.atomic;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class c<T> implements Queue<T> {
    static final int a = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    private static final Object j = new Object();
    final AtomicLong b = new AtomicLong();
    int c;
    long d;
    int e;
    AtomicReferenceArray<Object> f;
    int g;
    AtomicReferenceArray<Object> h;
    final AtomicLong i = new AtomicLong();

    public c(int i) {
        int a = rx.internal.util.a.c.a(Math.max(8, i));
        int i2 = a - 1;
        AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(a + 1);
        this.f = atomicReferenceArray;
        this.e = i2;
        a(a);
        this.h = atomicReferenceArray;
        this.g = i2;
        this.d = (long) (i2 - 1);
        a(0);
    }

    public boolean offer(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        AtomicReferenceArray atomicReferenceArray = this.f;
        long c = c();
        int i = this.e;
        int a = a(c, i);
        if (c < this.d) {
            return a(atomicReferenceArray, t, c, a);
        }
        int i2 = this.c;
        if (a(atomicReferenceArray, a(((long) i2) + c, i)) == null) {
            this.d = (((long) i2) + c) - 1;
            return a(atomicReferenceArray, t, c, a);
        } else if (a(atomicReferenceArray, a(1 + c, i)) != null) {
            return a(atomicReferenceArray, t, c, a);
        } else {
            a(atomicReferenceArray, c, a, t, (long) i);
            return true;
        }
    }

    private boolean a(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j, int i) {
        a(1 + j);
        a((AtomicReferenceArray) atomicReferenceArray, i, (Object) t);
        return true;
    }

    private void a(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i, T t, long j2) {
        AtomicReferenceArray atomicReferenceArray2 = new AtomicReferenceArray(atomicReferenceArray.length());
        this.f = atomicReferenceArray2;
        this.d = (j + j2) - 1;
        a(j + 1);
        a(atomicReferenceArray2, i, (Object) t);
        a((AtomicReferenceArray) atomicReferenceArray, atomicReferenceArray2);
        a((AtomicReferenceArray) atomicReferenceArray, i, j);
    }

    private void a(AtomicReferenceArray<Object> atomicReferenceArray, AtomicReferenceArray<Object> atomicReferenceArray2) {
        a((AtomicReferenceArray) atomicReferenceArray, b(atomicReferenceArray.length() - 1), (Object) atomicReferenceArray2);
    }

    private AtomicReferenceArray<Object> a(AtomicReferenceArray<Object> atomicReferenceArray) {
        return (AtomicReferenceArray) a((AtomicReferenceArray) atomicReferenceArray, b(atomicReferenceArray.length() - 1));
    }

    public T poll() {
        AtomicReferenceArray atomicReferenceArray = this.h;
        long d = d();
        int i = this.g;
        int a = a(d, i);
        T a2 = a(atomicReferenceArray, a);
        Object obj = a2 == j ? 1 : null;
        if (a2 == null || obj != null) {
            return obj != null ? a(a(atomicReferenceArray), d, i) : null;
        } else {
            b(d + 1);
            a(atomicReferenceArray, a, null);
            return a2;
        }
    }

    private T a(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i) {
        this.h = atomicReferenceArray;
        int a = a(j, i);
        T a2 = a((AtomicReferenceArray) atomicReferenceArray, a);
        if (a2 == null) {
            return null;
        }
        b(1 + j);
        a((AtomicReferenceArray) atomicReferenceArray, a, null);
        return a2;
    }

    public T peek() {
        AtomicReferenceArray atomicReferenceArray = this.h;
        long d = d();
        int i = this.g;
        T a = a(atomicReferenceArray, a(d, i));
        if (a == j) {
            return b(a(atomicReferenceArray), d, i);
        }
        return a;
    }

    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    private T b(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i) {
        this.h = atomicReferenceArray;
        return a((AtomicReferenceArray) atomicReferenceArray, a(j, i));
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

    private void a(int i) {
        this.c = Math.min(i / 4, a);
    }

    private long a() {
        return this.b.get();
    }

    private long b() {
        return this.i.get();
    }

    private long c() {
        return this.b.get();
    }

    private long d() {
        return this.i.get();
    }

    private void a(long j) {
        this.b.lazySet(j);
    }

    private void b(long j) {
        this.i.lazySet(j);
    }

    private static int a(long j, int i) {
        return b(((int) j) & i);
    }

    private static int b(int i) {
        return i;
    }

    private static void a(AtomicReferenceArray<Object> atomicReferenceArray, int i, Object obj) {
        atomicReferenceArray.lazySet(i, obj);
    }

    private static <E> Object a(AtomicReferenceArray<Object> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object obj) {
        throw new UnsupportedOperationException();
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    public <E> E[] toArray(E[] eArr) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    public T remove() {
        throw new UnsupportedOperationException();
    }

    public T element() {
        throw new UnsupportedOperationException();
    }
}
