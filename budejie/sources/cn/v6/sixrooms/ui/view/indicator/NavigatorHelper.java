package cn.v6.sixrooms.ui.view.indicator;

import android.util.SparseArray;
import android.util.SparseBooleanArray;

public class NavigatorHelper {
    private SparseBooleanArray a = new SparseBooleanArray();
    private SparseArray<Float> b = new SparseArray();
    private int c;
    private int d;
    private int e;
    private float f;
    private int g;
    private boolean h;
    private OnNavigatorScrollListener i;

    public interface OnNavigatorScrollListener {
        void onDeselected(int i, int i2);

        void onEnter(int i, int i2, float f, boolean z);

        void onLeave(int i, int i2, float f, boolean z);

        void onSelected(int i, int i2);
    }

    public void onPageScrolled(int i, float f, int i2) {
        boolean z;
        float f2 = ((float) i) + f;
        if (this.f <= f2) {
            z = true;
        } else {
            z = false;
        }
        if (this.g == 0) {
            for (int i3 = 0; i3 < this.c; i3++) {
                if (i3 != this.d) {
                    if (!this.a.get(i3)) {
                        b(i3);
                    }
                    if (((Float) this.b.get(i3, Float.valueOf(0.0f))).floatValue() != 1.0f) {
                        b(i3, 1.0f, false, true);
                    }
                }
            }
            a(this.d, 1.0f, false, true);
            a(this.d);
        } else if (f2 != this.f) {
            boolean z2;
            int i4;
            int i5 = i + 1;
            if (f == 0.0f && z) {
                z2 = false;
                i4 = i - 1;
            } else {
                z2 = true;
                i4 = i5;
            }
            int i6 = 0;
            while (i6 < this.c) {
                if (!(i6 == i || i6 == i4 || ((Float) this.b.get(i6, Float.valueOf(0.0f))).floatValue() == 1.0f)) {
                    b(i6, 1.0f, z, true);
                }
                i6++;
            }
            if (!z2) {
                b(i4, 1.0f - f, true, false);
                a(i, 1.0f - f, true, false);
            } else if (z) {
                b(i, f, true, false);
                a(i4, f, true, false);
            } else {
                b(i4, 1.0f - f, false, false);
                a(i, 1.0f - f, false, false);
            }
        } else {
            return;
        }
        this.f = f2;
    }

    private void a(int i, float f, boolean z, boolean z2) {
        if (this.h || i == this.d || this.g == 1 || z2) {
            if (this.i != null) {
                this.i.onEnter(i, this.c, f, z);
            }
            this.b.put(i, Float.valueOf(1.0f - f));
        }
    }

    private void b(int i, float f, boolean z, boolean z2) {
        if (this.h || i == this.e || this.g == 1 || (((i == this.d - 1 || i == this.d + 1) && ((Float) this.b.get(i, Float.valueOf(0.0f))).floatValue() != 1.0f) || z2)) {
            if (this.i != null) {
                this.i.onLeave(i, this.c, f, z);
            }
            this.b.put(i, Float.valueOf(f));
        }
    }

    private void a(int i) {
        if (this.i != null) {
            this.i.onSelected(i, this.c);
        }
        this.a.put(i, false);
    }

    private void b(int i) {
        if (this.i != null) {
            this.i.onDeselected(i, this.c);
        }
        this.a.put(i, true);
    }

    public void onPageSelected(int i) {
        this.e = this.d;
        this.d = i;
        a(this.d);
        int i2 = 0;
        while (i2 < this.c) {
            if (!(i2 == this.d || this.a.get(i2))) {
                b(i2);
            }
            i2++;
        }
    }

    public void onPageScrollStateChanged(int i) {
        this.g = i;
    }

    public void setNavigatorScrollListener(OnNavigatorScrollListener onNavigatorScrollListener) {
        this.i = onNavigatorScrollListener;
    }

    public void setSkimOver(boolean z) {
        this.h = z;
    }

    public void setTotalCount(int i) {
        this.c = i;
        this.a.clear();
        this.b.clear();
    }

    public int getTotalCount() {
        return this.c;
    }

    public int getCurrentIndex() {
        return this.d;
    }

    public int getScrollState() {
        return this.g;
    }
}
