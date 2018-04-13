package antistatic.spinnerwheel;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import antistatic.spinnerwheel.WheelScroller.ScrollingListener;
import antistatic.spinnerwheel.adapters.WheelViewAdapter;
import java.util.LinkedList;
import java.util.List;
import qsbk.app.R;

public abstract class AbstractWheel extends View {
    private static int m = -1;
    protected int a = 0;
    protected int b;
    protected boolean c;
    protected boolean d;
    protected WheelScroller e;
    protected boolean f;
    protected int g;
    protected LinearLayout h;
    protected int i;
    protected WheelViewAdapter j;
    protected int k;
    protected int l;
    private final String n;
    private WheelRecycler o = new WheelRecycler(this);
    private List<OnWheelChangedListener> p = new LinkedList();
    private List<OnWheelScrollListener> q = new LinkedList();
    private List<OnWheelClickedListener> r = new LinkedList();
    private DataSetObserver s;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new d();
        int a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    protected abstract float a(MotionEvent motionEvent);

    protected abstract WheelScroller a(ScrollingListener scrollingListener);

    protected abstract void a(int i, int i2);

    protected abstract void e();

    protected abstract void f();

    protected abstract int getBaseDimension();

    protected abstract int getItemDimension();

    public AbstractWheel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        StringBuilder append = new StringBuilder().append(AbstractWheel.class.getName()).append(" #");
        int i2 = m + 1;
        m = i2;
        this.n = append.append(i2).toString();
        a(attributeSet, i);
        a(context);
    }

    protected void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.AbstractWheelView, i, 0);
        this.b = obtainStyledAttributes.getInt(0, 4);
        this.c = obtainStyledAttributes.getBoolean(1, false);
        this.d = obtainStyledAttributes.getBoolean(8, false);
        obtainStyledAttributes.recycle();
    }

    protected void a(Context context) {
        this.s = new a(this);
        this.e = a(new b(this));
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = getCurrentItem();
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.a = savedState.a;
            postDelayed(new c(this), 100);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void a() {
    }

    protected void b() {
    }

    protected void c() {
    }

    protected void d() {
    }

    public void stopScrolling() {
        this.e.stopScrolling();
    }

    public void setInterpolator(Interpolator interpolator) {
        this.e.setInterpolator(interpolator);
    }

    public void scroll(int i, int i2) {
        int itemDimension = (getItemDimension() * i) - this.g;
        b();
        this.e.scroll(itemDimension, i2);
    }

    private void c(int i) {
        this.g += i;
        int itemDimension = getItemDimension();
        int i2 = this.g / itemDimension;
        int i3 = this.a - i2;
        int itemsCount = this.j.getItemsCount();
        int i4 = this.g % itemDimension;
        if (Math.abs(i4) <= itemDimension / 2) {
            i4 = 0;
        }
        if (this.d && itemsCount > 0) {
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
            i3 = this.a;
            i4 = 0;
        } else if (i3 >= itemsCount) {
            i3 = (this.a - itemsCount) + 1;
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
        i2 = this.g;
        if (i4 != this.a) {
            setCurrentItem(i4, false);
        } else {
            invalidate();
        }
        i4 = getBaseDimension();
        this.g = i2 - (i3 * itemDimension);
        if (this.g > i4) {
            this.g = i4 + (this.g % i4);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            int i5 = i3 - i;
            int i6 = i4 - i2;
            f();
            if (!(this.l == i5 && this.k == i6)) {
                a(getMeasuredWidth(), getMeasuredHeight());
            }
            this.l = i5;
            this.k = i6;
        }
    }

    public void invalidateItemsLayout(boolean z) {
        if (z) {
            this.o.clearAll();
            if (this.h != null) {
                this.h.removeAllViews();
            }
            this.g = 0;
        } else if (this.h != null) {
            this.o.recycleItems(this.h, this.i, new ItemsRange());
        }
        invalidate();
    }

    public int getVisibleItems() {
        return this.b;
    }

    public void setVisibleItems(int i) {
        this.b = i;
    }

    public void setAllItemsVisible(boolean z) {
        this.c = z;
        invalidateItemsLayout(false);
    }

    public WheelViewAdapter getViewAdapter() {
        return this.j;
    }

    public void setViewAdapter(WheelViewAdapter wheelViewAdapter) {
        if (this.j != null) {
            this.j.unregisterDataSetObserver(this.s);
        }
        this.j = wheelViewAdapter;
        if (this.j != null) {
            this.j.registerDataSetObserver(this.s);
        }
        invalidateItemsLayout(true);
    }

    public int getCurrentItem() {
        return this.a;
    }

    public void setCurrentItem(int i) {
        setCurrentItem(i, false);
    }

    public void setCurrentItem(int i, boolean z) {
        if (this.j != null && this.j.getItemsCount() != 0) {
            int itemsCount = this.j.getItemsCount();
            if (i < 0 || i >= itemsCount) {
                if (this.d) {
                    while (i < 0) {
                        i += itemsCount;
                    }
                    i %= itemsCount;
                } else {
                    return;
                }
            }
            if (i == this.a) {
                return;
            }
            if (z) {
                int i2 = i - this.a;
                if (this.d) {
                    itemsCount = (itemsCount + Math.min(i, this.a)) - Math.max(i, this.a);
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
            this.g = 0;
            itemsCount = this.a;
            this.a = i;
            b(itemsCount, this.a);
            invalidate();
        }
    }

    public boolean isCyclic() {
        return this.d;
    }

    public void setCyclic(boolean z) {
        this.d = z;
        invalidateItemsLayout(false);
    }

    public void addChangingListener(OnWheelChangedListener onWheelChangedListener) {
        this.p.add(onWheelChangedListener);
    }

    public void removeChangingListener(OnWheelChangedListener onWheelChangedListener) {
        this.p.remove(onWheelChangedListener);
    }

    protected void b(int i, int i2) {
        for (OnWheelChangedListener onChanged : this.p) {
            onChanged.onChanged(this, i, i2);
        }
    }

    public void addScrollingListener(OnWheelScrollListener onWheelScrollListener) {
        this.q.add(onWheelScrollListener);
    }

    public void removeScrollingListener(OnWheelScrollListener onWheelScrollListener) {
        this.q.remove(onWheelScrollListener);
    }

    protected void g() {
        for (OnWheelScrollListener onScrollingStarted : this.q) {
            onScrollingStarted.onScrollingStarted(this);
        }
    }

    protected void h() {
        for (OnWheelScrollListener onScrollingFinished : this.q) {
            onScrollingFinished.onScrollingFinished(this);
        }
    }

    public void addClickingListener(OnWheelClickedListener onWheelClickedListener) {
        this.r.add(onWheelClickedListener);
    }

    public void removeClickingListener(OnWheelClickedListener onWheelClickedListener) {
        this.r.remove(onWheelClickedListener);
    }

    protected void a(int i) {
        for (OnWheelClickedListener onItemClicked : this.r) {
            onItemClicked.onItemClicked(this, i);
        }
    }

    protected boolean i() {
        int recycleItems;
        boolean z;
        ItemsRange itemsRange = getItemsRange();
        if (this.h != null) {
            recycleItems = this.o.recycleItems(this.h, this.i, itemsRange);
            z = this.i != recycleItems;
            this.i = recycleItems;
        } else {
            e();
            z = true;
        }
        if (!z) {
            if (this.i == itemsRange.getFirst() && this.h.getChildCount() == itemsRange.getCount()) {
                z = false;
            } else {
                z = true;
            }
        }
        if (this.i <= itemsRange.getFirst() || this.i > itemsRange.getLast()) {
            this.i = itemsRange.getFirst();
        } else {
            recycleItems = this.i - 1;
            while (recycleItems >= itemsRange.getFirst() && a(recycleItems, true)) {
                this.i = recycleItems;
                recycleItems--;
            }
        }
        recycleItems = this.i;
        for (int childCount = this.h.getChildCount(); childCount < itemsRange.getCount(); childCount++) {
            if (!a(this.i + childCount, false) && this.h.getChildCount() == 0) {
                recycleItems++;
            }
        }
        this.i = recycleItems;
        return z;
    }

    private ItemsRange getItemsRange() {
        int baseDimension;
        int itemDimension;
        if (this.c) {
            baseDimension = getBaseDimension();
            itemDimension = getItemDimension();
            if (itemDimension != 0) {
                this.b = (baseDimension / itemDimension) + 1;
            }
        }
        itemDimension = this.a - (this.b / 2);
        baseDimension = (itemDimension + this.b) - (this.b % 2 == 0 ? 0 : 1);
        if (this.g != 0) {
            if (this.g > 0) {
                itemDimension--;
            } else {
                baseDimension++;
            }
        }
        if (!isCyclic()) {
            if (itemDimension < 0) {
                itemDimension = 0;
            }
            if (this.j == null) {
                baseDimension = 0;
            } else if (baseDimension > this.j.getItemsCount()) {
                baseDimension = this.j.getItemsCount();
            }
        }
        return new ItemsRange(itemDimension, (baseDimension - itemDimension) + 1);
    }

    protected boolean b(int i) {
        return this.j != null && this.j.getItemsCount() > 0 && (this.d || (i >= 0 && i < this.j.getItemsCount()));
    }

    private boolean a(int i, boolean z) {
        View d = d(i);
        if (d == null) {
            return false;
        }
        if (z) {
            this.h.addView(d, 0);
        } else {
            this.h.addView(d);
        }
        return true;
    }

    private View d(int i) {
        if (this.j == null || this.j.getItemsCount() == 0) {
            return null;
        }
        int itemsCount = this.j.getItemsCount();
        if (!b(i)) {
            return this.j.getEmptyItem(this.o.getEmptyItem(), this.h);
        }
        while (i < 0) {
            i += itemsCount;
        }
        return this.j.getItem(i % itemsCount, this.o.getItem(), this.h);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || getViewAdapter() == null) {
            return true;
        }
        switch (motionEvent.getAction()) {
            case 0:
            case 2:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
                break;
            case 1:
                if (!this.f) {
                    int a = ((int) a(motionEvent)) - (getBaseDimension() / 2);
                    if (a > 0) {
                        a += getItemDimension() / 2;
                    } else {
                        a -= getItemDimension() / 2;
                    }
                    a /= getItemDimension();
                    if (a != 0 && b(this.a + a)) {
                        a(a + this.a);
                        break;
                    }
                }
                break;
        }
        return this.e.onTouchEvent(motionEvent);
    }
}
