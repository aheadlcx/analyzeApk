package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R;
import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SynchronizedPool;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

@RestrictTo({Scope.LIBRARY_GROUP})
public class BottomNavigationMenuView extends ViewGroup implements MenuView {
    private final TransitionSet a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final OnClickListener f;
    private final Pool<BottomNavigationItemView> g;
    private boolean h;
    private BottomNavigationItemView[] i;
    private int j;
    private int k;
    private ColorStateList l;
    private ColorStateList m;
    private int n;
    private int[] o;
    private BottomNavigationPresenter p;
    private MenuBuilder q;

    public BottomNavigationMenuView(Context context) {
        this(context, null);
    }

    public BottomNavigationMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = new SynchronizedPool(5);
        this.h = true;
        this.j = 0;
        this.k = 0;
        Resources resources = getResources();
        this.b = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_max_width);
        this.c = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_min_width);
        this.d = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_max_width);
        this.e = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_height);
        this.a = new AutoTransition();
        this.a.setOrdering(0);
        this.a.setDuration(115);
        this.a.setInterpolator(new FastOutSlowInInterpolator());
        this.a.addTransition(new TextScale());
        this.f = new a(this);
        this.o = new int[5];
    }

    public void initialize(MenuBuilder menuBuilder) {
        this.q = menuBuilder;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int min;
        int size = MeasureSpec.getSize(i);
        int childCount = getChildCount();
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.e, 1073741824);
        int min2;
        if (this.h) {
            i3 = childCount - 1;
            min = Math.min(size - (this.c * i3), this.d);
            min2 = Math.min((size - min) / i3, this.b);
            size = (size - min) - (i3 * min2);
            int i4 = 0;
            while (i4 < childCount) {
                int[] iArr = this.o;
                if (i4 == this.k) {
                    i3 = min;
                } else {
                    i3 = min2;
                }
                iArr[i4] = i3;
                if (size > 0) {
                    int[] iArr2 = this.o;
                    iArr2[i4] = iArr2[i4] + 1;
                    i3 = size - 1;
                } else {
                    i3 = size;
                }
                i4++;
                size = i3;
            }
        } else {
            if (childCount == 0) {
                i3 = 1;
            } else {
                i3 = childCount;
            }
            min2 = Math.min(size / i3, this.d);
            i3 = size - (min2 * childCount);
            for (min = 0; min < childCount; min++) {
                this.o[min] = min2;
                if (i3 > 0) {
                    int[] iArr3 = this.o;
                    iArr3[min] = iArr3[min] + 1;
                    i3--;
                }
            }
        }
        i3 = 0;
        for (min = 0; min < childCount; min++) {
            View childAt = getChildAt(min);
            if (childAt.getVisibility() != 8) {
                childAt.measure(MeasureSpec.makeMeasureSpec(this.o[min], 1073741824), makeMeasureSpec);
                childAt.getLayoutParams().width = childAt.getMeasuredWidth();
                i3 += childAt.getMeasuredWidth();
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(i3, MeasureSpec.makeMeasureSpec(i3, 1073741824), 0), View.resolveSizeAndState(this.e, makeMeasureSpec, 0));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                if (ViewCompat.getLayoutDirection(this) == 1) {
                    childAt.layout((i5 - i7) - childAt.getMeasuredWidth(), 0, i5 - i7, i6);
                } else {
                    childAt.layout(i7, 0, childAt.getMeasuredWidth() + i7, i6);
                }
                i7 += childAt.getMeasuredWidth();
            }
        }
    }

    public int getWindowAnimations() {
        return 0;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.l = colorStateList;
        if (this.i != null) {
            for (BottomNavigationItemView iconTintList : this.i) {
                iconTintList.setIconTintList(colorStateList);
            }
        }
    }

    @Nullable
    public ColorStateList getIconTintList() {
        return this.l;
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.m = colorStateList;
        if (this.i != null) {
            for (BottomNavigationItemView textColor : this.i) {
                textColor.setTextColor(colorStateList);
            }
        }
    }

    public ColorStateList getItemTextColor() {
        return this.m;
    }

    public void setItemBackgroundRes(int i) {
        this.n = i;
        if (this.i != null) {
            for (BottomNavigationItemView itemBackground : this.i) {
                itemBackground.setItemBackground(i);
            }
        }
    }

    public int getItemBackgroundRes() {
        return this.n;
    }

    public void setPresenter(BottomNavigationPresenter bottomNavigationPresenter) {
        this.p = bottomNavigationPresenter;
    }

    public void buildMenuView() {
        removeAllViews();
        if (this.i != null) {
            for (Object release : this.i) {
                this.g.release(release);
            }
        }
        if (this.q.size() == 0) {
            this.j = 0;
            this.k = 0;
            this.i = null;
            return;
        }
        boolean z;
        this.i = new BottomNavigationItemView[this.q.size()];
        if (this.q.size() > 3) {
            z = true;
        } else {
            z = false;
        }
        this.h = z;
        for (int i = 0; i < this.q.size(); i++) {
            this.p.setUpdateSuspended(true);
            this.q.getItem(i).setCheckable(true);
            this.p.setUpdateSuspended(false);
            View newItem = getNewItem();
            this.i[i] = newItem;
            newItem.setIconTintList(this.l);
            newItem.setTextColor(this.m);
            newItem.setItemBackground(this.n);
            newItem.setShiftingMode(this.h);
            newItem.initialize((MenuItemImpl) this.q.getItem(i), 0);
            newItem.setItemPosition(i);
            newItem.setOnClickListener(this.f);
            addView(newItem);
        }
        this.k = Math.min(this.q.size() - 1, this.k);
        this.q.getItem(this.k).setChecked(true);
    }

    public void updateMenuView() {
        int size = this.q.size();
        if (size != this.i.length) {
            buildMenuView();
            return;
        }
        int i = this.j;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = this.q.getItem(i2);
            if (item.isChecked()) {
                this.j = item.getItemId();
                this.k = i2;
            }
        }
        if (i != this.j) {
            TransitionManager.beginDelayedTransition(this, this.a);
        }
        for (i = 0; i < size; i++) {
            this.p.setUpdateSuspended(true);
            this.i[i].initialize((MenuItemImpl) this.q.getItem(i), 0);
            this.p.setUpdateSuspended(false);
        }
    }

    private BottomNavigationItemView getNewItem() {
        BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) this.g.acquire();
        if (bottomNavigationItemView == null) {
            return new BottomNavigationItemView(getContext());
        }
        return bottomNavigationItemView;
    }

    public int getSelectedItemId() {
        return this.j;
    }

    void a(int i) {
        int size = this.q.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = this.q.getItem(i2);
            if (i == item.getItemId()) {
                this.j = i;
                this.k = i2;
                item.setChecked(true);
                return;
            }
        }
    }
}
