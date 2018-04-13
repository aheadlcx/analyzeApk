package com.budejie.www.activity.view.TopNavigationTabIndicator;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class ScrollViewCustom extends HorizontalScrollView {
    private Runnable a = new Runnable(this) {
        final /* synthetic */ ScrollViewCustom a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.b - this.a.getScrollX() != 0) {
                this.a.b = this.a.getScrollX();
                this.a.postDelayed(this.a.a, (long) this.a.c);
            } else if (this.a.e != null) {
                this.a.e.a();
                Rect rect = new Rect();
                this.a.getDrawingRect(rect);
                if (this.a.getScrollX() == 0) {
                    this.a.e.c();
                } else if ((this.a.d + this.a.getPaddingLeft()) + this.a.getPaddingRight() == rect.right) {
                    this.a.e.b();
                } else {
                    this.a.e.d();
                }
            }
        }
    };
    private int b;
    private int c = 10;
    private int d = 0;
    private a e;

    public interface a {
        void a();

        void b();

        void c();

        void d();

        void e();
    }

    public ScrollViewCustom(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnScrollStopListner(a aVar) {
        this.e = aVar;
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        a();
    }

    public void a() {
        this.b = getScrollX();
        postDelayed(this.a, (long) this.c);
        d();
    }

    private void d() {
        if (this.d <= 0) {
            for (int i = 0; i < getChildCount(); i++) {
                this.d += getChildAt(i).getWidth();
            }
        }
    }

    public void b() {
        if (this.e != null) {
            this.e.e();
        }
    }

    public void c() {
        if (this.e != null) {
            this.e.c();
        }
    }
}
