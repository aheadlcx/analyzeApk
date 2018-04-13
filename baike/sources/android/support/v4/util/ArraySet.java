package android.support.v4.util;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public final class ArraySet<E> implements Collection<E>, Set<E> {
    static Object[] a;
    static int b;
    static Object[] c;
    static int d;
    private static final int[] j = new int[0];
    private static final Object[] k = new Object[0];
    final boolean e;
    int[] f;
    Object[] g;
    int h;
    d<E, E> i;

    private int a(Object obj, int i) {
        int i2 = this.h;
        if (i2 == 0) {
            return -1;
        }
        int a = c.a(this.f, i2, i);
        if (a < 0 || obj.equals(this.g[a])) {
            return a;
        }
        int i3 = a + 1;
        while (i3 < i2 && this.f[i3] == i) {
            if (obj.equals(this.g[i3])) {
                return i3;
            }
            i3++;
        }
        a--;
        while (a >= 0 && this.f[a] == i) {
            if (obj.equals(this.g[a])) {
                return a;
            }
            a--;
        }
        return i3 ^ -1;
    }

    private int a() {
        int i = this.h;
        if (i == 0) {
            return -1;
        }
        int a = c.a(this.f, i, 0);
        if (a < 0 || this.g[a] == null) {
            return a;
        }
        int i2 = a + 1;
        while (i2 < i && this.f[i2] == 0) {
            if (this.g[i2] == null) {
                return i2;
            }
            i2++;
        }
        a--;
        while (a >= 0 && this.f[a] == 0) {
            if (this.g[a] == null) {
                return a;
            }
            a--;
        }
        return i2 ^ -1;
    }

    private void a(int i) {
        Object[] objArr;
        if (i == 8) {
            synchronized (ArraySet.class) {
                if (c != null) {
                    objArr = c;
                    this.g = objArr;
                    c = (Object[]) objArr[0];
                    this.f = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    d--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (ArraySet.class) {
                if (a != null) {
                    objArr = a;
                    this.g = objArr;
                    a = (Object[]) objArr[0];
                    this.f = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    b--;
                    return;
                }
            }
        }
        this.f = new int[i];
        this.g = new Object[i];
    }

    private static void a(int[] iArr, Object[] objArr, int i) {
        int i2;
        if (iArr.length == 8) {
            synchronized (ArraySet.class) {
                if (d < 10) {
                    objArr[0] = c;
                    objArr[1] = iArr;
                    for (i2 = i - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    c = objArr;
                    d++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (ArraySet.class) {
                if (b < 10) {
                    objArr[0] = a;
                    objArr[1] = iArr;
                    for (i2 = i - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    a = objArr;
                    b++;
                }
            }
        }
    }

    public ArraySet() {
        this(0, false);
    }

    public ArraySet(int i) {
        this(i, false);
    }

    public ArraySet(int i, boolean z) {
        this.e = z;
        if (i == 0) {
            this.f = j;
            this.g = k;
        } else {
            a(i);
        }
        this.h = 0;
    }

    public ArraySet(ArraySet<E> arraySet) {
        this();
        if (arraySet != null) {
            addAll((ArraySet) arraySet);
        }
    }

    public ArraySet(Collection<E> collection) {
        this();
        if (collection != null) {
            addAll((Collection) collection);
        }
    }

    public void clear() {
        if (this.h != 0) {
            a(this.f, this.g, this.h);
            this.f = j;
            this.g = k;
            this.h = 0;
        }
    }

    public void ensureCapacity(int i) {
        if (this.f.length < i) {
            Object obj = this.f;
            Object obj2 = this.g;
            a(i);
            if (this.h > 0) {
                System.arraycopy(obj, 0, this.f, 0, this.h);
                System.arraycopy(obj2, 0, this.g, 0, this.h);
            }
            a(obj, obj2, this.h);
        }
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    public int indexOf(Object obj) {
        if (obj == null) {
            return a();
        }
        return a(obj, this.e ? System.identityHashCode(obj) : obj.hashCode());
    }

    public E valueAt(int i) {
        return this.g[i];
    }

    public boolean isEmpty() {
        return this.h <= 0;
    }

    public boolean add(E e) {
        int a;
        int i;
        if (e == null) {
            a = a();
            i = 0;
        } else {
            a = this.e ? System.identityHashCode(e) : e.hashCode();
            i = a;
            a = a(e, a);
        }
        if (a >= 0) {
            return false;
        }
        int i2 = a ^ -1;
        if (this.h >= this.f.length) {
            a = this.h >= 8 ? this.h + (this.h >> 1) : this.h >= 4 ? 8 : 4;
            Object obj = this.f;
            Object obj2 = this.g;
            a(a);
            if (this.f.length > 0) {
                System.arraycopy(obj, 0, this.f, 0, obj.length);
                System.arraycopy(obj2, 0, this.g, 0, obj2.length);
            }
            a(obj, obj2, this.h);
        }
        if (i2 < this.h) {
            System.arraycopy(this.f, i2, this.f, i2 + 1, this.h - i2);
            System.arraycopy(this.g, i2, this.g, i2 + 1, this.h - i2);
        }
        this.f[i2] = i;
        this.g[i2] = e;
        this.h++;
        return true;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void append(E e) {
        int i = this.h;
        int identityHashCode = e == null ? 0 : this.e ? System.identityHashCode(e) : e.hashCode();
        if (i >= this.f.length) {
            throw new IllegalStateException("Array is full");
        } else if (i <= 0 || this.f[i - 1] <= identityHashCode) {
            this.h = i + 1;
            this.f[i] = identityHashCode;
            this.g[i] = e;
        } else {
            add(e);
        }
    }

    public void addAll(ArraySet<? extends E> arraySet) {
        int i = 0;
        int i2 = arraySet.h;
        ensureCapacity(this.h + i2);
        if (this.h != 0) {
            while (i < i2) {
                add(arraySet.valueAt(i));
                i++;
            }
        } else if (i2 > 0) {
            System.arraycopy(arraySet.f, 0, this.f, 0, i2);
            System.arraycopy(arraySet.g, 0, this.g, 0, i2);
            this.h = i2;
        }
    }

    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        removeAt(indexOf);
        return true;
    }

    public E removeAt(int i) {
        int i2 = 8;
        E e = this.g[i];
        if (this.h <= 1) {
            a(this.f, this.g, this.h);
            this.f = j;
            this.g = k;
            this.h = 0;
        } else if (this.f.length <= 8 || this.h >= this.f.length / 3) {
            this.h--;
            if (i < this.h) {
                System.arraycopy(this.f, i + 1, this.f, i, this.h - i);
                System.arraycopy(this.g, i + 1, this.g, i, this.h - i);
            }
            this.g[this.h] = null;
        } else {
            if (this.h > 8) {
                i2 = this.h + (this.h >> 1);
            }
            Object obj = this.f;
            Object obj2 = this.g;
            a(i2);
            this.h--;
            if (i > 0) {
                System.arraycopy(obj, 0, this.f, 0, i);
                System.arraycopy(obj2, 0, this.g, 0, i);
            }
            if (i < this.h) {
                System.arraycopy(obj, i + 1, this.f, i, this.h - i);
                System.arraycopy(obj2, i + 1, this.g, i, this.h - i);
            }
        }
        return e;
    }

    public boolean removeAll(ArraySet<? extends E> arraySet) {
        int i = arraySet.h;
        int i2 = this.h;
        for (int i3 = 0; i3 < i; i3++) {
            remove(arraySet.valueAt(i3));
        }
        if (i2 != this.h) {
            return true;
        }
        return false;
    }

    public int size() {
        return this.h;
    }

    public Object[] toArray() {
        Object obj = new Object[this.h];
        System.arraycopy(this.g, 0, obj, 0, this.h);
        return obj;
    }

    public <T> T[] toArray(T[] tArr) {
        Object obj;
        if (tArr.length < this.h) {
            obj = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.h);
        } else {
            obj = tArr;
        }
        System.arraycopy(this.g, 0, obj, 0, this.h);
        if (obj.length > this.h) {
            obj[this.h] = null;
        }
        return obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        if (size() != set.size()) {
            return false;
        }
        int i = 0;
        while (i < this.h) {
            try {
                if (!set.contains(valueAt(i))) {
                    return false;
                }
                i++;
            } catch (NullPointerException e) {
                return false;
            } catch (ClassCastException e2) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        int[] iArr = this.f;
        int i2 = 0;
        while (i < this.h) {
            i2 += iArr[i];
            i++;
        }
        return i2;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.h * 14);
        stringBuilder.append('{');
        for (int i = 0; i < this.h; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            ArraySet valueAt = valueAt(i);
            if (valueAt != this) {
                stringBuilder.append(valueAt);
            } else {
                stringBuilder.append("(this Set)");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    private d<E, E> b() {
        if (this.i == null) {
            this.i = new b(this);
        }
        return this.i;
    }

    public Iterator<E> iterator() {
        return b().getKeySet().iterator();
    }

    public boolean containsAll(Collection<?> collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends E> collection) {
        ensureCapacity(this.h + collection.size());
        boolean z = false;
        for (Object add : collection) {
            z |= add(add);
        }
        return z;
    }

    public boolean removeAll(Collection<?> collection) {
        boolean z = false;
        for (Object remove : collection) {
            z |= remove(remove);
        }
        return z;
    }

    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        for (int i = this.h - 1; i >= 0; i--) {
            if (!collection.contains(this.g[i])) {
                removeAt(i);
                z = true;
            }
        }
        return z;
    }
}
