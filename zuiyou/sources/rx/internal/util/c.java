package rx.internal.util;

public final class c<T> {
    final float a;
    int b;
    int c;
    int d;
    T[] e;

    public c() {
        this(16, 0.75f);
    }

    public c(int i, float f) {
        this.a = f;
        int a = rx.internal.util.a.c.a(i);
        this.b = a - 1;
        this.d = (int) (((float) a) * f);
        this.e = new Object[a];
    }

    public boolean a(T t) {
        Object[] objArr = this.e;
        int i = this.b;
        int a = a(t.hashCode()) & i;
        Object obj = objArr[a];
        if (obj != null) {
            if (obj.equals(t)) {
                return false;
            }
            do {
                a = (a + 1) & i;
                obj = objArr[a];
                if (obj == null) {
                }
            } while (!obj.equals(t));
            return false;
        }
        objArr[a] = t;
        a = this.c + 1;
        this.c = a;
        if (a >= this.d) {
            b();
        }
        return true;
    }

    public boolean b(T t) {
        Object[] objArr = this.e;
        int i = this.b;
        int a = a(t.hashCode()) & i;
        Object obj = objArr[a];
        if (obj == null) {
            return false;
        }
        if (obj.equals(t)) {
            return a(a, objArr, i);
        }
        do {
            a = (a + 1) & i;
            obj = objArr[a];
            if (obj == null) {
                return false;
            }
        } while (!obj.equals(t));
        return a(a, objArr, i);
    }

    boolean a(int i, T[] tArr, int i2) {
        this.c--;
        while (true) {
            Object obj;
            int i3 = (i + 1) & i2;
            while (true) {
                obj = tArr[i3];
                if (obj == null) {
                    tArr[i] = null;
                    return true;
                }
                int a = a(obj.hashCode()) & i2;
                if (i <= i3) {
                    if (i >= a || a > i3) {
                        break;
                    }
                    i3 = (i3 + 1) & i2;
                } else {
                    if (i >= a && a > i3) {
                        break;
                    }
                    i3 = (i3 + 1) & i2;
                }
            }
            tArr[i] = obj;
            i = i3;
        }
    }

    public void a() {
        this.c = 0;
        this.e = new Object[0];
    }

    void b() {
        Object[] objArr = this.e;
        int length = objArr.length;
        int i = length << 1;
        int i2 = i - 1;
        Object[] objArr2 = new Object[i];
        int i3 = length;
        length = this.c;
        while (true) {
            int i4 = length - 1;
            if (length != 0) {
                do {
                    i3--;
                } while (objArr[i3] == null);
                length = a(objArr[i3].hashCode()) & i2;
                if (objArr2[length] != null) {
                    do {
                        length = (length + 1) & i2;
                    } while (objArr2[length] != null);
                }
                objArr2[length] = objArr[i3];
                length = i4;
            } else {
                this.b = i2;
                this.d = (int) (((float) i) * this.a);
                this.e = objArr2;
                return;
            }
        }
    }

    static int a(int i) {
        int i2 = -1640531527 * i;
        return i2 ^ (i2 >>> 16);
    }

    public boolean c() {
        return this.c == 0;
    }

    public T[] d() {
        return this.e;
    }
}
