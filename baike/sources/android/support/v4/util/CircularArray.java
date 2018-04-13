package android.support.v4.util;

public final class CircularArray<E> {
    private E[] a;
    private int b;
    private int c;
    private int d;

    private void a() {
        int length = this.a.length;
        int i = length - this.b;
        int i2 = length << 1;
        if (i2 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        Object obj = new Object[i2];
        System.arraycopy(this.a, this.b, obj, 0, i);
        System.arraycopy(this.a, 0, obj, i, this.b);
        this.a = (Object[]) obj;
        this.b = 0;
        this.c = length;
        this.d = i2 - 1;
    }

    public CircularArray() {
        this(8);
    }

    public CircularArray(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        } else if (i > 1073741824) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        } else {
            if (Integer.bitCount(i) != 1) {
                i = Integer.highestOneBit(i - 1) << 1;
            }
            this.d = i - 1;
            this.a = new Object[i];
        }
    }

    public void addFirst(E e) {
        this.b = (this.b - 1) & this.d;
        this.a[this.b] = e;
        if (this.b == this.c) {
            a();
        }
    }

    public void addLast(E e) {
        this.a[this.c] = e;
        this.c = (this.c + 1) & this.d;
        if (this.c == this.b) {
            a();
        }
    }

    public E popFirst() {
        if (this.b == this.c) {
            throw new ArrayIndexOutOfBoundsException();
        }
        E e = this.a[this.b];
        this.a[this.b] = null;
        this.b = (this.b + 1) & this.d;
        return e;
    }

    public E popLast() {
        if (this.b == this.c) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i = (this.c - 1) & this.d;
        E e = this.a[i];
        this.a[i] = null;
        this.c = i;
        return e;
    }

    public void clear() {
        removeFromStart(size());
    }

    public void removeFromStart(int i) {
        if (i > 0) {
            if (i > size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i2;
            int length = this.a.length;
            if (i < length - this.b) {
                length = this.b + i;
            }
            for (i2 = this.b; i2 < length; i2++) {
                this.a[i2] = null;
            }
            length -= this.b;
            i2 = i - length;
            this.b = (length + this.b) & this.d;
            if (i2 > 0) {
                for (length = 0; length < i2; length++) {
                    this.a[length] = null;
                }
                this.b = i2;
            }
        }
    }

    public void removeFromEnd(int i) {
        if (i > 0) {
            if (i > size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i2;
            int i3 = 0;
            if (i < this.c) {
                i3 = this.c - i;
            }
            for (i2 = i3; i2 < this.c; i2++) {
                this.a[i2] = null;
            }
            i3 = this.c - i3;
            i2 = i - i3;
            this.c -= i3;
            if (i2 > 0) {
                this.c = this.a.length;
                i2 = this.c - i2;
                for (i3 = i2; i3 < this.c; i3++) {
                    this.a[i3] = null;
                }
                this.c = i2;
            }
        }
    }

    public E getFirst() {
        if (this.b != this.c) {
            return this.a[this.b];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E getLast() {
        if (this.b != this.c) {
            return this.a[(this.c - 1) & this.d];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E get(int i) {
        if (i >= 0 && i < size()) {
            return this.a[(this.b + i) & this.d];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int size() {
        return (this.c - this.b) & this.d;
    }

    public boolean isEmpty() {
        return this.b == this.c;
    }
}
