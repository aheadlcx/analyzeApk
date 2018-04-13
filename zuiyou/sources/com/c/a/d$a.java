package com.c.a;

import android.support.annotation.RequiresApi;
import android.view.View;
import com.c.a.h.a;

class d$a extends a {
    final /* synthetic */ d a;
    private boolean b;

    private d$a(d dVar) {
        this.a = dVar;
    }

    public boolean a(View view, int i) {
        boolean b = d.a(this.a).b(1, i);
        if (b) {
            if (!(d.b(this.a) == null || d.b(this.a).isEmpty())) {
                for (f a : d.b(this.a)) {
                    a.a();
                }
            }
            this.b = true;
        }
        return b;
    }

    public int a(View view) {
        return d.c(this.a);
    }

    public int b(View view) {
        return 0;
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        super.a(view, i, i2, i3, i4);
        d.a(this.a, Math.abs(((float) i) / ((float) d.d(this.a).getWidth())));
        d.a(this.a, i);
        this.a.invalidate();
        if (d.e(this.a) < d.f(this.a) && !this.b) {
            this.b = true;
        }
        if (!(d.b(this.a) == null || d.b(this.a).isEmpty())) {
            for (f a : d.b(this.a)) {
                a.a(d.e(this.a), d.g(this.a));
            }
        }
        if (d.e(this.a) >= 1.0f && !d.h(this.a).isFinishing()) {
            if (d.b(this.a) != null && !d.b(this.a).isEmpty() && d.e(this.a) >= d.f(this.a) && this.b) {
                this.b = false;
                for (f a2 : d.b(this.a)) {
                    a2.b();
                }
            }
            d.h(this.a).overridePendingTransition(0, a.a.swipe_slide_out_right);
            d.h(this.a).finish();
        }
    }

    public void a(View view, float f, float f2) {
        int width = (f > 0.0f || (f == 0.0f && d.e(this.a) > d.f(this.a))) ? (view.getWidth() + this.a.a.getIntrinsicWidth()) + 10 : 0;
        if (a()) {
            d.a(this.a).a(width, 0);
            this.a.invalidate();
        } else if (width > 0 && !d.h(this.a).isFinishing()) {
            d.h(this.a).overridePendingTransition(0, a.a.swipe_slide_out_right);
            d.h(this.a).finish();
        }
    }

    public int a(View view, int i, int i2) {
        return Math.min(view.getWidth(), Math.max(i, 0));
    }

    public boolean a() {
        return this.a.a();
    }

    @RequiresApi(api = 16)
    public void a(int i, int i2) {
        super.a(i, i2);
        g.a(d.h(this.a), new g.a(this) {
            final /* synthetic */ d$a a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a.setPageTranslucent(true);
            }
        });
    }
}
