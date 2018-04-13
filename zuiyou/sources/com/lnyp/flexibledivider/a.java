package com.lnyp.flexibledivider;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.View;

public class a extends FlexibleDividerDecoration {
    private b i;
    private boolean j;

    public interface b {
        int a(int i, RecyclerView recyclerView);

        int b(int i, RecyclerView recyclerView);
    }

    public static class a extends com.lnyp.flexibledivider.FlexibleDividerDecoration.a<a> {
        private b b = new b(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public int a(int i, RecyclerView recyclerView) {
                return 0;
            }

            public int b(int i, RecyclerView recyclerView) {
                return 0;
            }
        };
        private boolean c = false;

        public a(Context context) {
            super(context);
        }

        public a b() {
            this.c = true;
            return this;
        }

        public a c() {
            a();
            return new a(this, this.c);
        }
    }

    protected a(a aVar, boolean z) {
        super(aVar);
        this.i = aVar.b;
        this.j = z;
    }

    protected Rect a(int i, RecyclerView recyclerView, View view) {
        if (this.j && i == 0) {
            return new Rect(0, 0, 0, 0);
        }
        Adapter adapter = recyclerView.getAdapter();
        if ((adapter instanceof com.lnyp.recyclerview.a) && ((com.lnyp.recyclerview.a) adapter).b(i)) {
            return new Rect(0, 0, 0, 0);
        }
        Rect rect = new Rect(0, 0, 0, 0);
        int translationX = (int) ViewCompat.getTranslationX(view);
        int translationY = (int) ViewCompat.getTranslationY(view);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        rect.left = (recyclerView.getPaddingLeft() + this.i.a(i, recyclerView)) + translationX;
        rect.right = translationX + ((recyclerView.getWidth() - recyclerView.getPaddingRight()) - this.i.b(i, recyclerView));
        translationX = a(i, recyclerView);
        boolean a = a(recyclerView);
        if (this.a != DividerType.DRAWABLE) {
            int i2 = translationX / 2;
            if (a) {
                rect.top = ((view.getTop() - layoutParams.topMargin) - i2) + translationY;
            } else {
                rect.top = ((layoutParams.bottomMargin + view.getBottom()) + i2) + translationY;
            }
            rect.bottom = rect.top;
        } else if (a) {
            rect.bottom = (view.getTop() - layoutParams.topMargin) + translationY;
            rect.top = rect.bottom - translationX;
        } else {
            rect.top = (layoutParams.bottomMargin + view.getBottom()) + translationY;
            rect.bottom = rect.top + translationX;
        }
        if (this.h) {
            if (a) {
                rect.top += translationX;
                rect.bottom += translationX;
            } else {
                rect.top -= translationX;
                rect.bottom -= translationX;
            }
        }
        return rect;
    }

    protected void a(Rect rect, int i, RecyclerView recyclerView) {
        if (this.j) {
            if (i == 0) {
                rect.set(0, 0, 0, 0);
                return;
            }
            i--;
        }
        Adapter adapter = recyclerView.getAdapter();
        if ((adapter instanceof com.lnyp.recyclerview.a) && ((com.lnyp.recyclerview.a) adapter).b(i)) {
            rect.set(0, 0, 0, 0);
        } else if (this.h) {
            rect.set(0, 0, 0, 0);
        } else if (a(recyclerView)) {
            rect.set(0, a(i, recyclerView), 0, 0);
        } else {
            rect.set(0, 0, 0, a(i, recyclerView));
        }
    }

    private int a(int i, RecyclerView recyclerView) {
        if (this.c != null) {
            return (int) this.c.a(i, recyclerView).getStrokeWidth();
        }
        if (this.f != null) {
            return this.f.a(i, recyclerView);
        }
        if (this.e != null) {
            return this.e.a(i, recyclerView).getIntrinsicHeight();
        }
        throw new RuntimeException("failed to get size");
    }
}
