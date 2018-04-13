package cn.tatagou.sdk.view.dialog;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import cn.tatagou.sdk.R$drawable;
import cn.tatagou.sdk.view.dialog.h.a;
import java.util.LinkedList;
import java.util.List;

public class WheelView extends View {
    private static final int[] c = new int[]{-285212673, -352321537, 872415231};
    boolean a = false;
    a b = new a(this) {
        final /* synthetic */ WheelView a;

        {
            this.a = r1;
        }

        public void onStarted() {
            this.a.j = true;
            this.a.a();
        }

        public void onScroll(int i) {
            this.a.b(i);
            int height = this.a.getHeight();
            if (this.a.k > height) {
                this.a.k = height;
                this.a.i.stopScrolling();
            } else if (this.a.k < (-height)) {
                this.a.k = -height;
                this.a.i.stopScrolling();
            }
        }

        public void onFinished() {
            if (this.a.j) {
                this.a.b();
                this.a.j = false;
            }
            this.a.k = 0;
            this.a.invalidate();
        }

        public void onJustify() {
            if (Math.abs(this.a.k) > 1) {
                this.a.i.scroll(this.a.k, 0);
            }
        }
    };
    private int d = 0;
    private int e = 5;
    private int f = 0;
    private GradientDrawable g;
    private GradientDrawable h;
    private h i;
    private boolean j;
    private int k;
    private LinearLayout l;
    private int m;
    private i n;
    private g o = new g(this);
    private List<d> p = new LinkedList();
    private List<f> q = new LinkedList();
    private List<e> r = new LinkedList();
    private DataSetObserver s = new DataSetObserver(this) {
        final /* synthetic */ WheelView a;

        {
            this.a = r1;
        }

        public void onChanged() {
            this.a.invalidateWheel(false);
        }

        public void onInvalidated() {
            this.a.invalidateWheel(true);
        }
    };

    public WheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public WheelView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.i = new h(getContext(), this.b);
    }

    public void setInterpolator(Interpolator interpolator) {
        this.i.setInterpolator(interpolator);
    }

    public int getVisibleItems() {
        return this.e;
    }

    public void setVisibleItems(int i) {
        this.e = i;
    }

    public i getViewAdapter() {
        return this.n;
    }

    public void setViewAdapter(i iVar) {
        if (this.n != null) {
            this.n.unregisterDataSetObserver(this.s);
        }
        this.n = iVar;
        if (this.n != null) {
            this.n.registerDataSetObserver(this.s);
        }
        invalidateWheel(true);
    }

    public void addChangingListener(d dVar) {
        this.p.add(dVar);
    }

    public void removeChangingListener(d dVar) {
        this.p.remove(dVar);
    }

    protected void a(int i, int i2) {
        for (d onChanged : this.p) {
            onChanged.onChanged(this, i, i2);
        }
    }

    public void addScrollingListener(f fVar) {
        this.q.add(fVar);
    }

    public void removeScrollingListener(f fVar) {
        this.q.remove(fVar);
    }

    protected void a() {
        for (f onScrollingStarted : this.q) {
            onScrollingStarted.onScrollingStarted(this);
        }
    }

    protected void b() {
        for (f onScrollingFinished : this.q) {
            onScrollingFinished.onScrollingFinished(this);
        }
    }

    public void addClickingListener(e eVar) {
        this.r.add(eVar);
    }

    public void removeClickingListener(e eVar) {
        this.r.remove(eVar);
    }

    protected void a(int i) {
        for (e onItemClicked : this.r) {
            onItemClicked.onItemClicked(this, i);
        }
    }

    public int getCurrentItem() {
        return this.d;
    }

    public void setCurrentItem(int i, boolean z) {
        if (this.n != null && this.n.getItemsCount() != 0) {
            int itemsCount = this.n.getItemsCount();
            if (i < 0 || i >= itemsCount) {
                if (this.a) {
                    while (i < 0) {
                        i += itemsCount;
                    }
                    i %= itemsCount;
                } else {
                    return;
                }
            }
            if (i == this.d) {
                return;
            }
            if (z) {
                int i2 = i - this.d;
                if (this.a) {
                    itemsCount = (itemsCount + Math.min(i, this.d)) - Math.max(i, this.d);
                    if (itemsCount < Math.abs(i2)) {
                        if (i2 >= 0) {
                            itemsCount = -itemsCount;
                        }
                        scroll(itemsCount, 0);
                        return;
                    }
                }
                itemsCount = i2;
                scroll(itemsCount, 0);
                return;
            }
            this.k = 0;
            itemsCount = this.d;
            this.d = i;
            a(itemsCount, this.d);
            invalidate();
        }
    }

    public void setCurrentItem(int i) {
        setCurrentItem(i, false);
    }

    public boolean isCyclic() {
        return this.a;
    }

    public void setCyclic(boolean z) {
        this.a = z;
        invalidateWheel(false);
    }

    public void invalidateWheel(boolean z) {
        if (z) {
            this.o.clearAll();
            if (this.l != null) {
                this.l.removeAllViews();
            }
            this.k = 0;
        } else if (this.l != null) {
            this.o.recycleItems(this.l, this.m, new c());
        }
        invalidate();
    }

    private void c() {
        if (this.g == null) {
            this.g = new GradientDrawable(Orientation.TOP_BOTTOM, c);
        }
        if (this.h == null) {
            this.h = new GradientDrawable(Orientation.BOTTOM_TOP, c);
        }
        setBackgroundResource(R$drawable.wheel_bg);
    }

    private int a(LinearLayout linearLayout) {
        if (!(linearLayout == null || linearLayout.getChildAt(0) == null)) {
            this.f = linearLayout.getChildAt(0).getMeasuredHeight();
        }
        return Math.max((this.f * this.e) - ((this.f * 10) / 50), getSuggestedMinimumHeight());
    }

    private int getItemHeight() {
        if (this.f != 0) {
            return this.f;
        }
        if (this.l == null || this.l.getChildAt(0) == null) {
            return getHeight() / this.e;
        }
        this.f = this.l.getChildAt(0).getHeight();
        return this.f;
    }

    private int b(int i, int i2) {
        c();
        this.l.setLayoutParams(new LayoutParams(-2, -2));
        this.l.measure(MeasureSpec.makeMeasureSpec(i, 0), MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth = this.l.getMeasuredWidth();
        if (i2 != 1073741824) {
            measuredWidth = Math.max(measuredWidth + 20, getSuggestedMinimumWidth());
            if (i2 != Integer.MIN_VALUE || i >= measuredWidth) {
                i = measuredWidth;
            }
        }
        this.l.measure(MeasureSpec.makeMeasureSpec(i - 20, 1073741824), MeasureSpec.makeMeasureSpec(0, 0));
        return i;
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        g();
        size = b(size, mode);
        if (mode2 != 1073741824) {
            mode = a(this.l);
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(mode, size2) : mode;
        }
        setMeasuredDimension(size, size2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        c(i3 - i, i4 - i2);
    }

    private void c(int i, int i2) {
        this.l.layout(0, 0, i - 20, i2);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.n != null && this.n.getItemsCount() > 0) {
            e();
            b(canvas);
        }
        a(canvas);
    }

    private void a(Canvas canvas) {
        int itemHeight = getItemHeight() * 2;
        this.g.setBounds(0, 0, getWidth(), itemHeight);
        this.g.draw(canvas);
        this.h.setBounds(0, getHeight() - itemHeight, getWidth(), getHeight());
        this.h.draw(canvas);
    }

    private void b(Canvas canvas) {
        canvas.save();
        canvas.translate(10.0f, (float) ((-(((this.d - this.m) * getItemHeight()) + ((getItemHeight() - getHeight()) / 2))) + this.k));
        this.l.draw(canvas);
        canvas.restore();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || getViewAdapter() == null) {
            return true;
        }
        switch (motionEvent.getAction()) {
            case 1:
                if (!this.j) {
                    int y = ((int) motionEvent.getY()) - (getHeight() / 2);
                    if (y > 0) {
                        y += getItemHeight() / 2;
                    } else {
                        y -= getItemHeight() / 2;
                    }
                    y /= getItemHeight();
                    if (y != 0 && c(this.d + y)) {
                        a(y + this.d);
                        break;
                    }
                }
                break;
            case 2:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
                break;
        }
        return this.i.onTouchEvent(motionEvent);
    }

    private void b(int i) {
        this.k += i;
        int itemHeight = getItemHeight();
        int i2 = this.k / itemHeight;
        int i3 = this.d - i2;
        int itemsCount = this.n.getItemsCount();
        int i4 = this.k % itemHeight;
        if (Math.abs(i4) <= itemHeight / 2) {
            i4 = 0;
        }
        if (this.a && itemsCount > 0) {
            if (i4 > 0) {
                i4 = i3 - 1;
                i3 = i2 + 1;
            } else if (i4 < 0) {
                i4 = i3 + 1;
                i3 = i2 - 1;
            } else {
                i4 = i3;
                i3 = i2;
            }
            while (i4 < 0) {
                i4 += itemsCount;
            }
            i4 %= itemsCount;
        } else if (i3 < 0) {
            i3 = this.d;
            i4 = 0;
        } else if (i3 >= itemsCount) {
            i3 = (this.d - itemsCount) + 1;
            i4 = itemsCount - 1;
        } else if (i3 > 0 && i4 > 0) {
            i4 = i3 - 1;
            i3 = i2 + 1;
        } else if (i3 >= itemsCount - 1 || i4 >= 0) {
            i4 = i3;
            i3 = i2;
        } else {
            i4 = i3 + 1;
            i3 = i2 - 1;
        }
        i2 = this.k;
        if (i4 != this.d) {
            setCurrentItem(i4, false);
        } else {
            invalidate();
        }
        this.k = i2 - (i3 * itemHeight);
        if (this.k > getHeight()) {
            this.k = (this.k % getHeight()) + getHeight();
        }
    }

    public void scroll(int i, int i2) {
        this.i.scroll((getItemHeight() * i) - this.k, i2);
    }

    private c getItemsRange() {
        if (getItemHeight() == 0) {
            return null;
        }
        int i = this.d;
        int i2 = 1;
        while (getItemHeight() * i2 < getHeight()) {
            i--;
            i2 += 2;
        }
        if (this.k != 0) {
            if (this.k > 0) {
                i--;
            }
            int itemHeight = this.k / getItemHeight();
            i -= itemHeight;
            i2 = (int) (Math.asin((double) itemHeight) + ((double) (i2 + 1)));
        }
        return new c(i, i2);
    }

    private boolean d() {
        int recycleItems;
        boolean z;
        c itemsRange = getItemsRange();
        if (this.l != null) {
            recycleItems = this.o.recycleItems(this.l, this.m, itemsRange);
            z = this.m != recycleItems;
            this.m = recycleItems;
        } else {
            f();
            z = true;
        }
        if (!z) {
            if (this.m == itemsRange.getFirst() && this.l.getChildCount() == itemsRange.getCount()) {
                z = false;
            } else {
                z = true;
            }
        }
        if (this.m <= itemsRange.getFirst() || this.m > itemsRange.getLast()) {
            this.m = itemsRange.getFirst();
        } else {
            recycleItems = this.m - 1;
            while (recycleItems >= itemsRange.getFirst() && a(recycleItems, true)) {
                this.m = recycleItems;
                recycleItems--;
            }
        }
        recycleItems = this.m;
        for (int childCount = this.l.getChildCount(); childCount < itemsRange.getCount(); childCount++) {
            if (!a(this.m + childCount, false) && this.l.getChildCount() == 0) {
                recycleItems++;
            }
        }
        this.m = recycleItems;
        return z;
    }

    private void e() {
        if (d()) {
            b(getWidth(), 1073741824);
            c(getWidth(), getHeight());
        }
    }

    private void f() {
        if (this.l == null) {
            this.l = new LinearLayout(getContext());
            this.l.setOrientation(1);
        }
    }

    private void g() {
        if (this.l != null) {
            this.o.recycleItems(this.l, this.m, new c());
        } else {
            f();
        }
        int i = this.e / 2;
        for (int i2 = this.d + i; i2 >= this.d - i; i2--) {
            if (a(i2, true)) {
                this.m = i2;
            }
        }
    }

    private boolean a(int i, boolean z) {
        View d = d(i);
        if (d == null) {
            return false;
        }
        if (z) {
            this.l.addView(d, 0);
        } else {
            this.l.addView(d);
        }
        return true;
    }

    private boolean c(int i) {
        return this.n != null && this.n.getItemsCount() > 0 && (this.a || (i >= 0 && i < this.n.getItemsCount()));
    }

    private View d(int i) {
        if (this.n == null || this.n.getItemsCount() == 0) {
            return null;
        }
        int itemsCount = this.n.getItemsCount();
        if (!c(i)) {
            return this.n.getEmptyItem(this.o.getEmptyItem(), this.l);
        }
        while (i < 0) {
            i += itemsCount;
        }
        return this.n.getItem(i % itemsCount, this.o.getItem(), this.l);
    }

    public void stopScrolling() {
        this.i.stopScrolling();
    }
}
