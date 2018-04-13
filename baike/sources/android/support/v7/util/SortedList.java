package android.support.v7.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class SortedList<T> {
    public static final int INVALID_POSITION = -1;
    T[] a;
    private T[] b;
    private int c;
    private int d;
    private int e;
    private Callback f;
    private BatchedCallback g;
    private int h;
    private final Class<T> i;

    public static abstract class Callback<T2> implements ListUpdateCallback, Comparator<T2> {
        public abstract boolean areContentsTheSame(T2 t2, T2 t22);

        public abstract boolean areItemsTheSame(T2 t2, T2 t22);

        public abstract int compare(T2 t2, T2 t22);

        public abstract void onChanged(int i, int i2);

        public void onChanged(int i, int i2, Object obj) {
            onChanged(i, i2);
        }
    }

    public static class BatchedCallback<T2> extends Callback<T2> {
        final Callback<T2> a;
        private final BatchingListUpdateCallback b = new BatchingListUpdateCallback(this.a);

        public BatchedCallback(Callback<T2> callback) {
            this.a = callback;
        }

        public int compare(T2 t2, T2 t22) {
            return this.a.compare(t2, t22);
        }

        public void onInserted(int i, int i2) {
            this.b.onInserted(i, i2);
        }

        public void onRemoved(int i, int i2) {
            this.b.onRemoved(i, i2);
        }

        public void onMoved(int i, int i2) {
            this.b.onMoved(i, i2);
        }

        public void onChanged(int i, int i2) {
            this.b.onChanged(i, i2, null);
        }

        public boolean areContentsTheSame(T2 t2, T2 t22) {
            return this.a.areContentsTheSame(t2, t22);
        }

        public boolean areItemsTheSame(T2 t2, T2 t22) {
            return this.a.areItemsTheSame(t2, t22);
        }

        public void dispatchLastEvent() {
            this.b.dispatchLastEvent();
        }
    }

    public SortedList(Class<T> cls, Callback<T> callback) {
        this(cls, callback, 10);
    }

    public SortedList(Class<T> cls, Callback<T> callback, int i) {
        this.i = cls;
        this.a = (Object[]) Array.newInstance(cls, i);
        this.f = callback;
        this.h = 0;
    }

    public int size() {
        return this.h;
    }

    public int add(T t) {
        a();
        return a((Object) t, true);
    }

    public void addAll(T[] tArr, boolean z) {
        a();
        if (tArr.length != 0) {
            if (z) {
                a(tArr);
                return;
            }
            Object[] objArr = (Object[]) Array.newInstance(this.i, tArr.length);
            System.arraycopy(tArr, 0, objArr, 0, tArr.length);
            a(objArr);
        }
    }

    public void addAll(T... tArr) {
        addAll(tArr, false);
    }

    public void addAll(Collection<T> collection) {
        addAll(collection.toArray((Object[]) Array.newInstance(this.i, collection.size())), true);
    }

    private void a(T[] tArr) {
        int i = !(this.f instanceof BatchedCallback) ? 1 : 0;
        if (i != 0) {
            beginBatchedUpdates();
        }
        this.b = this.a;
        this.c = 0;
        this.d = this.h;
        Arrays.sort(tArr, this.f);
        int b = b(tArr);
        if (this.h == 0) {
            this.a = tArr;
            this.h = b;
            this.e = b;
            this.f.onInserted(0, b);
        } else {
            a((Object[]) tArr, b);
        }
        this.b = null;
        if (i != 0) {
            endBatchedUpdates();
        }
    }

    private int b(T[] tArr) {
        int i = 1;
        if (tArr.length == 0) {
            throw new IllegalArgumentException("Input array must be non-empty");
        }
        int i2 = 0;
        int i3 = 1;
        while (i < tArr.length) {
            Object obj = tArr[i];
            int compare = this.f.compare(tArr[i2], obj);
            if (compare > 0) {
                throw new IllegalArgumentException("Input must be sorted in ascending order.");
            }
            if (compare == 0) {
                compare = a(obj, (Object[]) tArr, i2, i3);
                if (compare != -1) {
                    tArr[compare] = obj;
                } else {
                    if (i3 != i) {
                        tArr[i3] = obj;
                    }
                    i3++;
                }
            } else {
                if (i3 != i) {
                    tArr[i3] = obj;
                }
                i2 = i3;
                i3++;
            }
            i++;
        }
        return i3;
    }

    private int a(T t, T[] tArr, int i, int i2) {
        for (int i3 = i; i3 < i2; i3++) {
            if (this.f.areItemsTheSame(tArr[i3], t)) {
                return i3;
            }
        }
        return -1;
    }

    private void a(T[] tArr, int i) {
        this.a = (Object[]) Array.newInstance(this.i, (this.h + i) + 10);
        this.e = 0;
        int i2 = 0;
        while (true) {
            if (this.c >= this.d && i2 >= i) {
                return;
            }
            if (this.c == this.d) {
                int i3 = i - i2;
                System.arraycopy(tArr, i2, this.a, this.e, i3);
                this.e += i3;
                this.h += i3;
                this.f.onInserted(this.e - i3, i3);
                return;
            } else if (i2 == i) {
                i2 = this.d - this.c;
                System.arraycopy(this.b, this.c, this.a, this.e, i2);
                this.e = i2 + this.e;
                return;
            } else {
                Object obj = this.b[this.c];
                Object obj2 = tArr[i2];
                int compare = this.f.compare(obj, obj2);
                if (compare > 0) {
                    Object[] objArr = this.a;
                    compare = this.e;
                    this.e = compare + 1;
                    objArr[compare] = obj2;
                    this.h++;
                    i2++;
                    this.f.onInserted(this.e - 1, 1);
                } else if (compare == 0 && this.f.areItemsTheSame(obj, obj2)) {
                    Object[] objArr2 = this.a;
                    int i4 = this.e;
                    this.e = i4 + 1;
                    objArr2[i4] = obj2;
                    i2++;
                    this.c++;
                    if (!this.f.areContentsTheSame(obj, obj2)) {
                        this.f.onChanged(this.e - 1, 1);
                    }
                } else {
                    Object[] objArr3 = this.a;
                    compare = this.e;
                    this.e = compare + 1;
                    objArr3[compare] = obj;
                    this.c++;
                }
            }
        }
    }

    private void a() {
        if (this.b != null) {
            throw new IllegalStateException("Cannot call this method from within addAll");
        }
    }

    public void beginBatchedUpdates() {
        a();
        if (!(this.f instanceof BatchedCallback)) {
            if (this.g == null) {
                this.g = new BatchedCallback(this.f);
            }
            this.f = this.g;
        }
    }

    public void endBatchedUpdates() {
        a();
        if (this.f instanceof BatchedCallback) {
            ((BatchedCallback) this.f).dispatchLastEvent();
        }
        if (this.f == this.g) {
            this.f = this.g.a;
        }
    }

    private int a(T t, boolean z) {
        int i = 0;
        int a = a(t, this.a, 0, this.h, 1);
        if (a != -1) {
            if (a < this.h) {
                Object obj = this.a[a];
                if (this.f.areItemsTheSame(obj, t)) {
                    if (this.f.areContentsTheSame(obj, t)) {
                        this.a[a] = t;
                        return a;
                    }
                    this.a[a] = t;
                    this.f.onChanged(a, 1);
                    return a;
                }
            }
            i = a;
        }
        a(i, (Object) t);
        if (z) {
            this.f.onInserted(i, 1);
        }
        return i;
    }

    public boolean remove(T t) {
        a();
        return b(t, true);
    }

    public T removeItemAt(int i) {
        a();
        T t = get(i);
        a(i, true);
        return t;
    }

    private boolean b(T t, boolean z) {
        int a = a(t, this.a, 0, this.h, 2);
        if (a == -1) {
            return false;
        }
        a(a, z);
        return true;
    }

    private void a(int i, boolean z) {
        System.arraycopy(this.a, i + 1, this.a, i, (this.h - i) - 1);
        this.h--;
        this.a[this.h] = null;
        if (z) {
            this.f.onRemoved(i, 1);
        }
    }

    public void updateItemAt(int i, T t) {
        a();
        T t2 = get(i);
        boolean z = t2 == t || !this.f.areContentsTheSame(t2, t);
        if (t2 == t || this.f.compare(t2, t) != 0) {
            if (z) {
                this.f.onChanged(i, 1);
            }
            a(i, false);
            int a = a((Object) t, false);
            if (i != a) {
                this.f.onMoved(i, a);
                return;
            }
            return;
        }
        this.a[i] = t;
        if (z) {
            this.f.onChanged(i, 1);
        }
    }

    public void recalculatePositionOfItemAt(int i) {
        a();
        Object obj = get(i);
        a(i, false);
        int a = a(obj, false);
        if (i != a) {
            this.f.onMoved(i, a);
        }
    }

    public T get(int i) throws IndexOutOfBoundsException {
        if (i >= this.h || i < 0) {
            throw new IndexOutOfBoundsException("Asked to get item at " + i + " but size is " + this.h);
        } else if (this.b == null || i < this.e) {
            return this.a[i];
        } else {
            return this.b[(i - this.e) + this.c];
        }
    }

    public int indexOf(T t) {
        if (this.b != null) {
            int a = a(t, this.a, 0, this.e, 4);
            if (a != -1) {
                return a;
            }
            a = a(t, this.b, this.c, this.d, 4);
            return a != -1 ? (a - this.c) + this.e : -1;
        } else {
            return a(t, this.a, 0, this.h, 4);
        }
    }

    private int a(T t, T[] tArr, int i, int i2, int i3) {
        int i4 = i2;
        int i5 = i;
        while (i5 < i4) {
            int i6 = (i5 + i4) / 2;
            Object obj = tArr[i6];
            int compare = this.f.compare(obj, t);
            if (compare < 0) {
                int i7 = i4;
                i4 = i6 + 1;
                i6 = i7;
            } else if (compare != 0) {
                i4 = i5;
            } else if (this.f.areItemsTheSame(obj, t)) {
                return i6;
            } else {
                i4 = a((Object) t, i6, i5, i4);
                if (i3 != 1) {
                    return i4;
                }
                if (i4 != -1) {
                    return i4;
                }
                return i6;
            }
            i5 = i4;
            i4 = i6;
        }
        if (i3 != 1) {
            i5 = -1;
        }
        return i5;
    }

    private int a(T t, int i, int i2, int i3) {
        int i4 = i - 1;
        while (i4 >= i2) {
            Object obj = this.a[i4];
            if (this.f.compare(obj, t) != 0) {
                break;
            } else if (this.f.areItemsTheSame(obj, t)) {
                return i4;
            } else {
                i4--;
            }
        }
        i4 = i + 1;
        while (i4 < i3) {
            obj = this.a[i4];
            if (this.f.compare(obj, t) != 0) {
                break;
            } else if (this.f.areItemsTheSame(obj, t)) {
                return i4;
            } else {
                i4++;
            }
        }
        return -1;
    }

    private void a(int i, T t) {
        if (i > this.h) {
            throw new IndexOutOfBoundsException("cannot add item to " + i + " because size is " + this.h);
        }
        if (this.h == this.a.length) {
            Object[] objArr = (Object[]) Array.newInstance(this.i, this.a.length + 10);
            System.arraycopy(this.a, 0, objArr, 0, i);
            objArr[i] = t;
            System.arraycopy(this.a, i, objArr, i + 1, this.h - i);
            this.a = objArr;
        } else {
            System.arraycopy(this.a, i, this.a, i + 1, this.h - i);
            this.a[i] = t;
        }
        this.h++;
    }

    public void clear() {
        a();
        if (this.h != 0) {
            int i = this.h;
            Arrays.fill(this.a, 0, i, null);
            this.h = 0;
            this.f.onRemoved(0, i);
        }
    }
}
