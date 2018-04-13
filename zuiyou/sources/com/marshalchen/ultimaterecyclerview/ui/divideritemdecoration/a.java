package com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.View;

public class a extends FlexibleDividerDecoration {
    private b h;

    public interface b {
        int a(int i, RecyclerView recyclerView);

        int b(int i, RecyclerView recyclerView);
    }

    public static class a extends com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.FlexibleDividerDecoration.a<a> {
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

        public a(Context context) {
            super(context);
        }

        public a b() {
            a();
            return new a(this);
        }
    }

    protected a(a aVar) {
        super(aVar);
        this.h = aVar.b;
    }

    protected Rect a(int i, RecyclerView recyclerView, View view) {
        Rect rect = new Rect(0, 0, 0, 0);
        int translationX = (int) ViewCompat.getTranslationX(view);
        int translationY = (int) ViewCompat.getTranslationY(view);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        rect.top = (recyclerView.getPaddingTop() + this.h.a(i, recyclerView)) + translationY;
        rect.bottom = translationY + ((recyclerView.getHeight() - recyclerView.getPaddingBottom()) - this.h.b(i, recyclerView));
        translationY = a(i, recyclerView);
        if (this.a == DividerType.DRAWABLE) {
            rect.left = (layoutParams.leftMargin + view.getRight()) + translationX;
            rect.right = rect.left + translationY;
        } else {
            rect.left = ((layoutParams.leftMargin + view.getRight()) + (translationY / 2)) + translationX;
            rect.right = rect.left;
        }
        return rect;
    }

    protected void a(Rect rect, int i, RecyclerView recyclerView) {
        rect.set(0, 0, a(i, recyclerView), 0);
    }

    private int a(int i, RecyclerView recyclerView) {
        if (this.c != null) {
            return (int) this.c.a(i, recyclerView).getStrokeWidth();
        }
        if (this.f != null) {
            return this.f.a(i, recyclerView);
        }
        if (this.e != null) {
            return this.e.a(i, recyclerView).getIntrinsicWidth();
        }
        throw new RuntimeException("failed to get size");
    }
}
