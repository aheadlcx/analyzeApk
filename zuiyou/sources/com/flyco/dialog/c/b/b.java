package com.flyco.dialog.c.b;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.flyco.dialog.c.a.a;

public abstract class b<T extends b<T>> extends a<T> {
    protected View l;
    protected int m;
    protected int n;
    protected int o;
    protected boolean p;

    public abstract T c(View view);

    public abstract void d();

    public b(Context context) {
        super(context);
        b(1.0f);
        a(false);
    }

    public void d(View view) {
        this.i.setClipChildren(false);
        if (view != null) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onGlobalLayout() {
                    this.a.p = true;
                    this.a.d();
                }
            });
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.j.setClickable(false);
        if (this.p) {
            d();
        }
    }

    public T a(int i) {
        if (i == 48 || i == 80) {
            this.o = i;
            c(this.l);
            return this;
        }
        throw new IllegalArgumentException("Gravity must be either Gravity.TOP or Gravity.BOTTOM");
    }
}
