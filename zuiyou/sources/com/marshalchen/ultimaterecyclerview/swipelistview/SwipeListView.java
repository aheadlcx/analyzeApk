package com.marshalchen.ultimaterecyclerview.swipelistview;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import java.util.List;

public class SwipeListView extends RecyclerView {
    int a = 0;
    int b = 0;
    public a c;
    private int d = 0;
    private float e;
    private float f;
    private int g;
    private LinearLayoutManager h;
    private b i;

    public SwipeListView(Context context, int i, int i2) {
        super(context);
        this.a = i2;
        this.b = i;
    }

    public SwipeListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SwipeListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public List<Integer> getPositionsSelected() {
        return this.i.f();
    }

    public int getCountSelected() {
        return this.i.e();
    }

    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        this.i.d();
        adapter.registerAdapterDataObserver(new AdapterDataObserver(this) {
            final /* synthetic */ SwipeListView a;

            {
                this.a = r1;
            }

            public void onChanged() {
                super.onChanged();
                this.a.c();
                this.a.i.d();
            }
        });
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
        this.h = (LinearLayoutManager) layoutManager;
        if (this.i != null) {
            this.i.a(this.h);
        }
    }

    protected void a(int[] iArr) {
        if (this.c != null) {
            this.c.a(iArr);
        }
    }

    protected void a(int i, int i2, boolean z) {
        if (this.c != null && i != -1) {
            this.c.a(i, i2, z);
        }
    }

    protected void a(int i, boolean z) {
        if (this.c != null && i != -1) {
            this.c.c(i, z);
        }
    }

    protected void a(int i) {
        if (this.c != null && i != -1) {
            this.c.a(i);
        }
    }

    protected void b(int i) {
        if (this.c != null && i != -1) {
            this.c.b(i);
        }
    }

    protected void b(int i, boolean z) {
        if (this.c != null && i != -1) {
            this.c.a(i, z);
        }
    }

    protected void c(int i, boolean z) {
        if (this.c != null && i != -1) {
            this.c.b(i, z);
        }
    }

    protected void d(int i, boolean z) {
        if (this.c != null && i != -1) {
            this.c.d(i, z);
        }
    }

    protected void a() {
        if (this.c != null) {
            this.c.b();
        }
    }

    protected void b() {
        if (this.c != null) {
            this.c.c();
        }
    }

    protected void c() {
        if (this.c != null) {
            this.c.a();
        }
    }

    protected void a(int i, float f) {
        if (this.c != null && i != -1) {
            this.c.a(i, f);
        }
    }

    protected int c(int i) {
        if (this.c == null || i == -1) {
            return -1;
        }
        return this.c.c(i);
    }

    public void setSwipeListViewListener(a aVar) {
        this.c = aVar;
    }

    public void d() {
        this.d = 0;
    }

    public void setOffsetRight(float f) {
        this.i.a(f);
    }

    public void setOffsetLeft(float f) {
        this.i.b(f);
    }

    public void setOnlyOneOpenedWhenSwipe(boolean z) {
        this.i.a(z);
    }

    public void setSwipeCloseAllItemsWhenMoveList(boolean z) {
        this.i.b(z);
    }

    public void setSwipeOpenOnLongPress(boolean z) {
        this.i.c(z);
    }

    public void setSwipeMode(int i) {
        this.i.a(i);
    }

    public int getSwipeActionLeft() {
        return this.i.b();
    }

    public void setSwipeActionLeft(int i) {
        this.i.b(i);
    }

    public int getSwipeActionRight() {
        return this.i.c();
    }

    public void setSwipeActionRight(int i) {
        this.i.c(i);
    }

    public void setAnimationTime(long j) {
        this.i.a(j);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (isEnabled() && this.i.a()) {
            if (this.d != 1) {
                switch (actionMasked) {
                    case 0:
                        super.onInterceptTouchEvent(motionEvent);
                        this.i.onTouch(this, motionEvent);
                        this.d = 0;
                        this.e = x;
                        this.f = y;
                        return false;
                    case 1:
                        this.i.onTouch(this, motionEvent);
                        if (this.d != 2) {
                            return false;
                        }
                        return true;
                    case 2:
                        a(x, y);
                        if (this.d != 2) {
                            return false;
                        }
                        return true;
                    case 3:
                        this.d = 0;
                        break;
                }
            }
            return this.i.onTouch(this, motionEvent);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    private void a(float f, float f2) {
        int i = 0;
        int abs = (int) Math.abs(f - this.e);
        int abs2 = (int) Math.abs(f2 - this.f);
        int i2 = this.g;
        abs = abs > i2 ? 1 : 0;
        if (abs2 > i2) {
            i = 1;
        }
        if (abs != 0) {
            this.d = 1;
            this.e = f;
            this.f = f2;
        }
        if (i != 0) {
            this.d = 2;
            this.e = f;
            this.f = f2;
        }
    }
}
