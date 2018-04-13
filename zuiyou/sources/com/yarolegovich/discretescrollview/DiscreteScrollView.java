package com.yarolegovich.discretescrollview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class DiscreteScrollView extends RecyclerView {
    private static final int a = Orientation.HORIZONTAL.ordinal();
    private a b;
    private List<b> c;
    private List<a> d;

    public interface a<T extends ViewHolder> {
        void a(@Nullable T t, int i);
    }

    public interface b<T extends ViewHolder> {
        void a(float f, int i, int i2, @Nullable T t, @Nullable T t2);

        void a(@NonNull T t, int i);

        void b(@NonNull T t, int i);
    }

    private class c implements com.yarolegovich.discretescrollview.a.b {
        final /* synthetic */ DiscreteScrollView a;

        private c(DiscreteScrollView discreteScrollView) {
            this.a = discreteScrollView;
        }

        public void a(boolean z) {
            this.a.setOverScrollMode(z ? 0 : 2);
        }

        public void a() {
            if (!this.a.c.isEmpty()) {
                int c = this.a.b.c();
                ViewHolder a = this.a.a(c);
                if (a != null) {
                    this.a.a(a, c);
                }
            }
        }

        public void b() {
            if (!this.a.d.isEmpty() || !this.a.c.isEmpty()) {
                int c = this.a.b.c();
                ViewHolder a = this.a.a(c);
                if (a != null) {
                    this.a.b(a, c);
                    this.a.c(a, c);
                }
            }
        }

        public void a(float f) {
            if (!this.a.c.isEmpty()) {
                int currentItem = this.a.getCurrentItem();
                int b = this.a.b.b();
                if (currentItem != b) {
                    this.a.a(f, currentItem, b, this.a.a(currentItem), this.a.a(b));
                }
            }
        }

        public void c() {
            this.a.post(new Runnable(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.a();
                }
            });
        }

        public void d() {
            this.a.a();
        }
    }

    public DiscreteScrollView(Context context) {
        super(context);
        a(null);
    }

    public DiscreteScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public DiscreteScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        this.c = new ArrayList();
        this.d = new ArrayList();
        int i = a;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.yarolegovich.discretescrollview.b.b.DiscreteScrollView);
            i = obtainStyledAttributes.getInt(com.yarolegovich.discretescrollview.b.b.DiscreteScrollView_dsv_orientation, a);
            obtainStyledAttributes.recycle();
        }
        this.b = new a(getContext(), new c(), Orientation.values()[i]);
        setLayoutManager(this.b);
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager instanceof a) {
            super.setLayoutManager(layoutManager);
            return;
        }
        throw new IllegalArgumentException(getContext().getString(com.yarolegovich.discretescrollview.b.a.dsv_ex_msg_dont_set_lm));
    }

    public boolean fling(int i, int i2) {
        boolean fling = super.fling(i, i2);
        if (fling) {
            this.b.a(i, i2);
        } else {
            this.b.a();
        }
        return fling;
    }

    @Nullable
    public ViewHolder a(int i) {
        View findViewByPosition = this.b.findViewByPosition(i);
        return findViewByPosition != null ? getChildViewHolder(findViewByPosition) : null;
    }

    public int getCurrentItem() {
        return this.b.c();
    }

    public void setItemTransformer(com.yarolegovich.discretescrollview.transform.a aVar) {
        this.b.a(aVar);
    }

    public void setItemTransitionTimeMillis(@IntRange(from = 10) int i) {
        this.b.a(i);
    }

    public void setSlideOnFling(boolean z) {
        this.b.a(z);
    }

    public void setSlideOnFlingThreshold(int i) {
        this.b.c(i);
    }

    public void setOrientation(Orientation orientation) {
        this.b.a(orientation);
    }

    public void setOffscreenItems(int i) {
        this.b.b(i);
    }

    public void a(@NonNull a<?> aVar) {
        this.d.add(aVar);
    }

    private void a(ViewHolder viewHolder, int i) {
        for (b a : this.c) {
            a.a(viewHolder, i);
        }
    }

    private void b(ViewHolder viewHolder, int i) {
        for (b b : this.c) {
            b.b(viewHolder, i);
        }
    }

    private void a(float f, int i, int i2, ViewHolder viewHolder, ViewHolder viewHolder2) {
        for (b a : this.c) {
            a.a(f, i, i2, viewHolder, viewHolder2);
        }
    }

    private void c(ViewHolder viewHolder, int i) {
        for (a a : this.d) {
            a.a(viewHolder, i);
        }
    }

    private void a() {
        if (!this.d.isEmpty()) {
            int c = this.b.c();
            c(a(c), c);
        }
    }
}
