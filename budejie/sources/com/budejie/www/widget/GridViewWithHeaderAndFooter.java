package com.budejie.www.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class GridViewWithHeaderAndFooter extends GridView {
    public static boolean a = false;
    private OnItemClickListener b;
    private OnItemLongClickListener c;
    private int d = -1;
    private View e = null;
    private int f = -1;
    private ArrayList<GridViewWithHeaderAndFooter$a> g = new ArrayList();
    private ArrayList<GridViewWithHeaderAndFooter$a> h = new ArrayList();
    private ListAdapter i;
    private GridViewWithHeaderAndFooter$d j;

    private void a() {
    }

    public GridViewWithHeaderAndFooter(Context context) {
        super(context);
        a();
    }

    public GridViewWithHeaderAndFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public GridViewWithHeaderAndFooter(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        ListAdapter adapter = getAdapter();
        if (adapter != null && (adapter instanceof GridViewWithHeaderAndFooter$c)) {
            ((GridViewWithHeaderAndFooter$c) adapter).a(getNumColumnsCompatible());
            ((GridViewWithHeaderAndFooter$c) adapter).b(getRowHeight());
        }
    }

    public void setClipChildren(boolean z) {
    }

    public void setClipChildrenSupper(boolean z) {
        super.setClipChildren(false);
    }

    public void a(View view, int i) {
        a(view, null, true, i);
    }

    public void a(View view, Object obj, boolean z, int i) {
        ListAdapter adapter = getAdapter();
        if (adapter == null || (adapter instanceof GridViewWithHeaderAndFooter$c)) {
            LayoutParams layoutParams = view.getLayoutParams();
            GridViewWithHeaderAndFooter$a gridViewWithHeaderAndFooter$a = new GridViewWithHeaderAndFooter$a(null);
            ViewGroup gridViewWithHeaderAndFooter$b = new GridViewWithHeaderAndFooter$b(this, getContext());
            if (layoutParams != null) {
                view.setLayoutParams(new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height));
                gridViewWithHeaderAndFooter$b.setLayoutParams(new AbsListView.LayoutParams(layoutParams.width, layoutParams.height));
            } else if (i != 0) {
                view.setLayoutParams(new FrameLayout.LayoutParams(-1, i));
                gridViewWithHeaderAndFooter$b.setLayoutParams(new AbsListView.LayoutParams(-1, i));
            }
            gridViewWithHeaderAndFooter$b.addView(view);
            gridViewWithHeaderAndFooter$a.a = view;
            gridViewWithHeaderAndFooter$a.b = gridViewWithHeaderAndFooter$b;
            gridViewWithHeaderAndFooter$a.c = obj;
            gridViewWithHeaderAndFooter$a.d = z;
            this.g.add(gridViewWithHeaderAndFooter$a);
            if (adapter != null) {
                ((GridViewWithHeaderAndFooter$c) adapter).c();
                return;
            }
            return;
        }
        throw new IllegalStateException("Cannot add header view to grid -- setAdapter has already been called.");
    }

    public int getHeaderViewCount() {
        return this.g.size();
    }

    public int getFooterViewCount() {
        return this.h.size();
    }

    @TargetApi(11)
    private int getNumColumnsCompatible() {
        if (VERSION.SDK_INT >= 11) {
            return super.getNumColumns();
        }
        try {
            Field declaredField = GridView.class.getDeclaredField("mNumColumns");
            declaredField.setAccessible(true);
            return declaredField.getInt(this);
        } catch (Exception e) {
            if (this.d != -1) {
                return this.d;
            }
            throw new RuntimeException("Can not determine the mNumColumns for this API platform, please call setNumColumns to set it.");
        }
    }

    @TargetApi(16)
    private int getColumnWidthCompatible() {
        if (VERSION.SDK_INT >= 16) {
            return super.getColumnWidth();
        }
        try {
            Field declaredField = GridView.class.getDeclaredField("mColumnWidth");
            declaredField.setAccessible(true);
            return declaredField.getInt(this);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.e = null;
    }

    @TargetApi(16)
    public int getVerticalSpacing() {
        try {
            if (VERSION.SDK_INT >= 16) {
                return super.getVerticalSpacing();
            }
            Field declaredField = GridView.class.getDeclaredField("mVerticalSpacing");
            declaredField.setAccessible(true);
            return declaredField.getInt(this);
        } catch (Exception e) {
            return 0;
        }
    }

    @TargetApi(16)
    public int getHorizontalSpacing() {
        try {
            if (VERSION.SDK_INT >= 16) {
                return super.getHorizontalSpacing();
            }
            Field declaredField = GridView.class.getDeclaredField("mHorizontalSpacing");
            declaredField.setAccessible(true);
            return declaredField.getInt(this);
        } catch (Exception e) {
            return 0;
        }
    }

    public int getRowHeight() {
        if (this.f > 0) {
            return this.f;
        }
        ListAdapter adapter = getAdapter();
        int numColumnsCompatible = getNumColumnsCompatible();
        if (adapter == null || adapter.getCount() <= (this.g.size() + this.h.size()) * numColumnsCompatible) {
            return -1;
        }
        int columnWidthCompatible = getColumnWidthCompatible();
        View view = getAdapter().getView(numColumnsCompatible * this.g.size(), this.e, this);
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new AbsListView.LayoutParams(-1, -2, 0);
            view.setLayoutParams(layoutParams);
        }
        view.measure(getChildMeasureSpec(MeasureSpec.makeMeasureSpec(columnWidthCompatible, 1073741824), 0, layoutParams.width), getChildMeasureSpec(MeasureSpec.makeMeasureSpec(0, 0), 0, layoutParams.height));
        this.e = view;
        this.f = view.getMeasuredHeight();
        return this.f;
    }

    public void setAdapter(ListAdapter listAdapter) {
        this.i = listAdapter;
        if (this.g.size() > 0 || this.h.size() > 0) {
            ListAdapter gridViewWithHeaderAndFooter$c = new GridViewWithHeaderAndFooter$c(this.g, this.h, listAdapter);
            int numColumnsCompatible = getNumColumnsCompatible();
            if (numColumnsCompatible > 1) {
                gridViewWithHeaderAndFooter$c.a(numColumnsCompatible);
            }
            gridViewWithHeaderAndFooter$c.b(getRowHeight());
            super.setAdapter(gridViewWithHeaderAndFooter$c);
            return;
        }
        super.setAdapter(listAdapter);
    }

    public ListAdapter getOriginalAdapter() {
        return this.i;
    }

    public void setNumColumns(int i) {
        super.setNumColumns(i);
        this.d = i;
        ListAdapter adapter = getAdapter();
        if (adapter != null && (adapter instanceof GridViewWithHeaderAndFooter$c)) {
            ((GridViewWithHeaderAndFooter$c) adapter).a(i);
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.b = onItemClickListener;
        super.setOnItemClickListener(getItemClickHandler());
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.c = onItemLongClickListener;
        super.setOnItemLongClickListener(getItemClickHandler());
    }

    private GridViewWithHeaderAndFooter$d getItemClickHandler() {
        if (this.j == null) {
            this.j = new GridViewWithHeaderAndFooter$d(this, null);
        }
        return this.j;
    }
}
