package android.support.v7.util;

import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v7.util.ThreadUtil.BackgroundCallback;
import android.support.v7.util.ThreadUtil.MainThreadCallback;
import android.util.SparseIntArray;

public class AsyncListUtil<T> {
    final Class<T> a;
    final int b;
    final DataCallback<T> c;
    final ViewCallback d;
    final TileList<T> e;
    final MainThreadCallback<T> f;
    final BackgroundCallback<T> g;
    final int[] h = new int[2];
    final int[] i = new int[2];
    final int[] j = new int[2];
    boolean k;
    int l = 0;
    int m = 0;
    int n = this.m;
    final SparseIntArray o = new SparseIntArray();
    private int p = 0;
    private final MainThreadCallback<T> q = new a(this);
    private final BackgroundCallback<T> r = new b(this);

    public static abstract class DataCallback<T> {
        @WorkerThread
        public abstract void fillData(T[] tArr, int i, int i2);

        @WorkerThread
        public abstract int refreshData();

        @WorkerThread
        public void recycleData(T[] tArr, int i) {
        }

        @WorkerThread
        public int getMaxCachedTiles() {
            return 10;
        }
    }

    public static abstract class ViewCallback {
        public static final int HINT_SCROLL_ASC = 2;
        public static final int HINT_SCROLL_DESC = 1;
        public static final int HINT_SCROLL_NONE = 0;

        @UiThread
        public abstract void getItemRangeInto(int[] iArr);

        @UiThread
        public abstract void onDataRefresh();

        @UiThread
        public abstract void onItemLoaded(int i);

        @UiThread
        public void extendRangeInto(int[] iArr, int[] iArr2, int i) {
            int i2;
            int i3 = (iArr[1] - iArr[0]) + 1;
            int i4 = i3 / 2;
            int i5 = iArr[0];
            if (i == 1) {
                i2 = i3;
            } else {
                i2 = i4;
            }
            iArr2[0] = i5 - i2;
            i2 = iArr[1];
            if (i != 2) {
                i3 = i4;
            }
            iArr2[1] = i2 + i3;
        }
    }

    public AsyncListUtil(Class<T> cls, int i, DataCallback<T> dataCallback, ViewCallback viewCallback) {
        this.a = cls;
        this.b = i;
        this.c = dataCallback;
        this.d = viewCallback;
        this.e = new TileList(this.b);
        ThreadUtil eVar = new e();
        this.f = eVar.getMainThreadProxy(this.q);
        this.g = eVar.getBackgroundProxy(this.r);
        refresh();
    }

    private boolean b() {
        return this.n != this.m;
    }

    public void onRangeChanged() {
        if (!b()) {
            a();
            this.k = true;
        }
    }

    public void refresh() {
        this.o.clear();
        BackgroundCallback backgroundCallback = this.g;
        int i = this.n + 1;
        this.n = i;
        backgroundCallback.refresh(i);
    }

    public T getItem(int i) {
        if (i < 0 || i >= this.l) {
            throw new IndexOutOfBoundsException(i + " is not within 0 and " + this.l);
        }
        T itemAt = this.e.getItemAt(i);
        if (itemAt == null && !b()) {
            this.o.put(i, 0);
        }
        return itemAt;
    }

    public int getItemCount() {
        return this.l;
    }

    void a() {
        this.d.getItemRangeInto(this.h);
        if (this.h[0] <= this.h[1] && this.h[0] >= 0 && this.h[1] < this.l) {
            if (!this.k) {
                this.p = 0;
            } else if (this.h[0] > this.i[1] || this.i[0] > this.h[1]) {
                this.p = 0;
            } else if (this.h[0] < this.i[0]) {
                this.p = 1;
            } else if (this.h[0] > this.i[0]) {
                this.p = 2;
            }
            this.i[0] = this.h[0];
            this.i[1] = this.h[1];
            this.d.extendRangeInto(this.h, this.j, this.p);
            this.j[0] = Math.min(this.h[0], Math.max(this.j[0], 0));
            this.j[1] = Math.max(this.h[1], Math.min(this.j[1], this.l - 1));
            this.g.updateRange(this.h[0], this.h[1], this.j[0], this.j[1], this.p);
        }
    }
}
