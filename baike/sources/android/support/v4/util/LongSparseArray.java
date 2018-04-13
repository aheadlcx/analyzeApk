package android.support.v4.util;

public class LongSparseArray<E> implements Cloneable {
    private static final Object a = new Object();
    private boolean b;
    private long[] c;
    private Object[] d;
    private int e;

    public LongSparseArray() {
        this(10);
    }

    public LongSparseArray(int i) {
        this.b = false;
        if (i == 0) {
            this.c = c.b;
            this.d = c.c;
        } else {
            int idealLongArraySize = c.idealLongArraySize(i);
            this.c = new long[idealLongArraySize];
            this.d = new Object[idealLongArraySize];
        }
        this.e = 0;
    }

    public LongSparseArray<E> clone() {
        try {
            LongSparseArray<E> longSparseArray = (LongSparseArray) super.clone();
            try {
                longSparseArray.c = (long[]) this.c.clone();
                longSparseArray.d = (Object[]) this.d.clone();
                return longSparseArray;
            } catch (CloneNotSupportedException e) {
                return longSparseArray;
            }
        } catch (CloneNotSupportedException e2) {
            return null;
        }
    }

    public E get(long j) {
        return get(j, null);
    }

    public E get(long j, E e) {
        int a = c.a(this.c, this.e, j);
        return (a < 0 || this.d[a] == a) ? e : this.d[a];
    }

    public void delete(long j) {
        int a = c.a(this.c, this.e, j);
        if (a >= 0 && this.d[a] != a) {
            this.d[a] = a;
            this.b = true;
        }
    }

    public void remove(long j) {
        delete(j);
    }

    public void removeAt(int i) {
        if (this.d[i] != a) {
            this.d[i] = a;
            this.b = true;
        }
    }

    private void a() {
        int i = this.e;
        long[] jArr = this.c;
        Object[] objArr = this.d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != a) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.b = false;
        this.e = i2;
    }

    public void put(long j, E e) {
        int a = c.a(this.c, this.e, j);
        if (a >= 0) {
            this.d[a] = e;
            return;
        }
        a ^= -1;
        if (a >= this.e || this.d[a] != a) {
            if (this.b && this.e >= this.c.length) {
                a();
                a = c.a(this.c, this.e, j) ^ -1;
            }
            if (this.e >= this.c.length) {
                int idealLongArraySize = c.idealLongArraySize(this.e + 1);
                Object obj = new long[idealLongArraySize];
                Object obj2 = new Object[idealLongArraySize];
                System.arraycopy(this.c, 0, obj, 0, this.c.length);
                System.arraycopy(this.d, 0, obj2, 0, this.d.length);
                this.c = obj;
                this.d = obj2;
            }
            if (this.e - a != 0) {
                System.arraycopy(this.c, a, this.c, a + 1, this.e - a);
                System.arraycopy(this.d, a, this.d, a + 1, this.e - a);
            }
            this.c[a] = j;
            this.d[a] = e;
            this.e++;
            return;
        }
        this.c[a] = j;
        this.d[a] = e;
    }

    public int size() {
        if (this.b) {
            a();
        }
        return this.e;
    }

    public long keyAt(int i) {
        if (this.b) {
            a();
        }
        return this.c[i];
    }

    public E valueAt(int i) {
        if (this.b) {
            a();
        }
        return this.d[i];
    }

    public void setValueAt(int i, E e) {
        if (this.b) {
            a();
        }
        this.d[i] = e;
    }

    public int indexOfKey(long j) {
        if (this.b) {
            a();
        }
        return c.a(this.c, this.e, j);
    }

    public int indexOfValue(E e) {
        if (this.b) {
            a();
        }
        for (int i = 0; i < this.e; i++) {
            if (this.d[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        int i = this.e;
        Object[] objArr = this.d;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.e = 0;
        this.b = false;
    }

    public void append(long j, E e) {
        if (this.e == 0 || j > this.c[this.e - 1]) {
            if (this.b && this.e >= this.c.length) {
                a();
            }
            int i = this.e;
            if (i >= this.c.length) {
                int idealLongArraySize = c.idealLongArraySize(i + 1);
                Object obj = new long[idealLongArraySize];
                Object obj2 = new Object[idealLongArraySize];
                System.arraycopy(this.c, 0, obj, 0, this.c.length);
                System.arraycopy(this.d, 0, obj2, 0, this.d.length);
                this.c = obj;
                this.d = obj2;
            }
            this.c[i] = j;
            this.d[i] = e;
            this.e = i + 1;
            return;
        }
        put(j, e);
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.e * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.e; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(keyAt(i));
            stringBuilder.append('=');
            LongSparseArray valueAt = valueAt(i);
            if (valueAt != this) {
                stringBuilder.append(valueAt);
            } else {
                stringBuilder.append("(this Map)");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
