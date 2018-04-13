package rx.internal.util.a;

public final class d<E> extends h<E> {
    public d(int i) {
        super(i);
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        Object[] objArr = this.c;
        long j = this.b;
        long b = b();
        long a = a(b);
        if (b(objArr, a) == null) {
            a(objArr, a, e);
            c(1 + b);
        } else if (b - a() > j) {
            return false;
        } else {
            do {
            } while (b(objArr, a) != null);
        }
        a(objArr, a, e);
        c(1 + b);
        return true;
    }

    public E poll() {
        long a;
        long c = c();
        do {
            a = a();
            if (a >= c) {
                long b = b();
                if (a >= b) {
                    return null;
                }
                d(b);
            }
        } while (!b(a, 1 + a));
        c = a(a);
        Object[] objArr = this.c;
        E a2 = a(objArr, c);
        b(objArr, c, null);
        return a2;
    }

    public E peek() {
        E b;
        long c = c();
        do {
            long a = a();
            if (a >= c) {
                long b2 = b();
                if (a >= b2) {
                    return null;
                }
                d(b2);
            }
            b = b(a(a));
        } while (b == null);
        return b;
    }

    public int size() {
        long a = a();
        while (true) {
            long b = b();
            long a2 = a();
            if (a == a2) {
                return (int) (b - a2);
            }
            a = a2;
        }
    }

    public boolean isEmpty() {
        return a() == b();
    }
}
