package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import java.util.List;

class ab {
    final b a;
    final a b = new a();
    final List<View> c = new ArrayList();

    static class a {
        long a = 0;
        a b;

        a() {
        }

        void a(int i) {
            if (i >= 64) {
                b();
                this.b.a(i - 64);
                return;
            }
            this.a |= 1 << i;
        }

        private void b() {
            if (this.b == null) {
                this.b = new a();
            }
        }

        void b(int i) {
            if (i < 64) {
                this.a &= (1 << i) ^ -1;
            } else if (this.b != null) {
                this.b.b(i - 64);
            }
        }

        boolean c(int i) {
            if (i < 64) {
                return (this.a & (1 << i)) != 0;
            } else {
                b();
                return this.b.c(i - 64);
            }
        }

        void a() {
            this.a = 0;
            if (this.b != null) {
                this.b.a();
            }
        }

        void a(int i, boolean z) {
            if (i >= 64) {
                b();
                this.b.a(i - 64, z);
                return;
            }
            boolean z2 = (this.a & Long.MIN_VALUE) != 0;
            long j = (1 << i) - 1;
            this.a = (((j ^ -1) & this.a) << 1) | (this.a & j);
            if (z) {
                a(i);
            } else {
                b(i);
            }
            if (z2 || this.b != null) {
                b();
                this.b.a(0, z2);
            }
        }

        boolean d(int i) {
            if (i >= 64) {
                b();
                return this.b.d(i - 64);
            }
            long j = 1 << i;
            boolean z = (this.a & j) != 0;
            this.a &= j ^ -1;
            j--;
            this.a = Long.rotateRight((j ^ -1) & this.a, 1) | (this.a & j);
            if (this.b == null) {
                return z;
            }
            if (this.b.c(0)) {
                a(63);
            }
            this.b.d(0);
            return z;
        }

        int e(int i) {
            if (this.b == null) {
                if (i >= 64) {
                    return Long.bitCount(this.a);
                }
                return Long.bitCount(this.a & ((1 << i) - 1));
            } else if (i < 64) {
                return Long.bitCount(this.a & ((1 << i) - 1));
            } else {
                return this.b.e(i - 64) + Long.bitCount(this.a);
            }
        }

        public String toString() {
            if (this.b == null) {
                return Long.toBinaryString(this.a);
            }
            return this.b.toString() + "xx" + Long.toBinaryString(this.a);
        }
    }

    interface b {
        void addView(View view, int i);

        void attachViewToParent(View view, int i, LayoutParams layoutParams);

        void detachViewFromParent(int i);

        View getChildAt(int i);

        int getChildCount();

        ViewHolder getChildViewHolder(View view);

        int indexOfChild(View view);

        void onEnteredHiddenState(View view);

        void onLeftHiddenState(View view);

        void removeAllViews();

        void removeViewAt(int i);
    }

    ab(b bVar) {
        this.a = bVar;
    }

    private void g(View view) {
        this.c.add(view);
        this.a.onEnteredHiddenState(view);
    }

    private boolean h(View view) {
        if (!this.c.remove(view)) {
            return false;
        }
        this.a.onLeftHiddenState(view);
        return true;
    }

    void a(View view, boolean z) {
        a(view, -1, z);
    }

    void a(View view, int i, boolean z) {
        int childCount;
        if (i < 0) {
            childCount = this.a.getChildCount();
        } else {
            childCount = f(i);
        }
        this.b.a(childCount, z);
        if (z) {
            g(view);
        }
        this.a.addView(view, childCount);
    }

    private int f(int i) {
        if (i < 0) {
            return -1;
        }
        int childCount = this.a.getChildCount();
        int i2 = i;
        while (i2 < childCount) {
            int e = i - (i2 - this.b.e(i2));
            if (e == 0) {
                while (this.b.c(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += e;
        }
        return -1;
    }

    void a(View view) {
        int indexOfChild = this.a.indexOfChild(view);
        if (indexOfChild >= 0) {
            if (this.b.d(indexOfChild)) {
                h(view);
            }
            this.a.removeViewAt(indexOfChild);
        }
    }

    void a(int i) {
        int f = f(i);
        View childAt = this.a.getChildAt(f);
        if (childAt != null) {
            if (this.b.d(f)) {
                h(childAt);
            }
            this.a.removeViewAt(f);
        }
    }

    View b(int i) {
        return this.a.getChildAt(f(i));
    }

    void a() {
        this.b.a();
        for (int size = this.c.size() - 1; size >= 0; size--) {
            this.a.onLeftHiddenState((View) this.c.get(size));
            this.c.remove(size);
        }
        this.a.removeAllViews();
    }

    View c(int i) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = (View) this.c.get(i2);
            ViewHolder childViewHolder = this.a.getChildViewHolder(view);
            if (childViewHolder.getLayoutPosition() == i && !childViewHolder.j() && !childViewHolder.m()) {
                return view;
            }
        }
        return null;
    }

    void a(View view, int i, LayoutParams layoutParams, boolean z) {
        int childCount;
        if (i < 0) {
            childCount = this.a.getChildCount();
        } else {
            childCount = f(i);
        }
        this.b.a(childCount, z);
        if (z) {
            g(view);
        }
        this.a.attachViewToParent(view, childCount, layoutParams);
    }

    int b() {
        return this.a.getChildCount() - this.c.size();
    }

    int c() {
        return this.a.getChildCount();
    }

    View d(int i) {
        return this.a.getChildAt(i);
    }

    void e(int i) {
        int f = f(i);
        this.b.d(f);
        this.a.detachViewFromParent(f);
    }

    int b(View view) {
        int indexOfChild = this.a.indexOfChild(view);
        if (indexOfChild == -1 || this.b.c(indexOfChild)) {
            return -1;
        }
        return indexOfChild - this.b.e(indexOfChild);
    }

    boolean c(View view) {
        return this.c.contains(view);
    }

    void d(View view) {
        int indexOfChild = this.a.indexOfChild(view);
        if (indexOfChild < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        this.b.a(indexOfChild);
        g(view);
    }

    void e(View view) {
        int indexOfChild = this.a.indexOfChild(view);
        if (indexOfChild < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        } else if (this.b.c(indexOfChild)) {
            this.b.b(indexOfChild);
            h(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public String toString() {
        return this.b.toString() + ", hidden list:" + this.c.size();
    }

    boolean f(View view) {
        int indexOfChild = this.a.indexOfChild(view);
        if (indexOfChild == -1) {
            return h(view) ? true : true;
        } else {
            if (!this.b.c(indexOfChild)) {
                return false;
            }
            this.b.d(indexOfChild);
            if (h(view)) {
                this.a.removeViewAt(indexOfChild);
            } else {
                this.a.removeViewAt(indexOfChild);
            }
            return true;
        }
    }
}
