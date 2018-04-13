package com.flyco.dialog.c.c.a;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.flyco.dialog.c.b.b;
import com.flyco.dialog.view.TriangleView;

public abstract class a<T extends a<T>> extends b<T> {
    private LayoutParams a;
    protected View q;
    protected LinearLayout r;
    protected TriangleView s;
    protected LayoutParams t;
    protected int u;
    protected int v;
    protected int w;
    protected int x;
    protected int y;
    protected int z;

    public abstract View e();

    public /* synthetic */ b c(View view) {
        return b(view);
    }

    public a(Context context) {
        super(context);
        this.q = e();
        f();
    }

    public a(Context context, View view) {
        super(context);
        this.q = view;
        f();
    }

    private void f() {
        a(new com.flyco.a.a.a());
        b(new com.flyco.a.b.a());
        a(false);
        b(Color.parseColor("#BB000000"));
        d(5.0f);
        a(8.0f, 8.0f);
        a(48);
        e(24.0f);
        f(12.0f);
    }

    public View a() {
        View inflate = View.inflate(this.c, com.flyco.dialog.a.b.popup_bubble, null);
        this.r = (LinearLayout) inflate.findViewById(com.flyco.dialog.a.a.ll_content);
        this.s = (TriangleView) inflate.findViewById(com.flyco.dialog.a.a.triangle_view);
        this.r.addView(this.q);
        this.t = (LayoutParams) this.r.getLayoutParams();
        this.a = (LayoutParams) this.s.getLayoutParams();
        inflate.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        return inflate;
    }

    public void b() {
        int i = 48;
        this.r.setBackgroundDrawable(com.flyco.dialog.b.a.a(this.u, (float) this.v));
        this.t.setMargins(this.w, 0, this.x, 0);
        this.r.setLayoutParams(this.t);
        this.s.setColor(this.u);
        TriangleView triangleView = this.s;
        if (this.o == 48) {
            i = 80;
        }
        triangleView.setGravity(i);
        this.a.width = this.y;
        this.a.height = this.z;
        this.s.setLayoutParams(this.a);
    }

    public void d() {
        int height;
        this.s.setX((float) (this.m - (this.s.getWidth() / 2)));
        if (this.o == 48) {
            height = this.n - this.s.getHeight();
            this.s.setY((float) height);
            this.r.setY((float) (height - this.r.getHeight()));
        } else {
            this.s.setY((float) this.n);
            this.r.setY((float) (this.n + this.s.getHeight()));
        }
        height = this.m - this.t.leftMargin;
        int i = (this.d.widthPixels - this.m) - this.t.rightMargin;
        int width = this.r.getWidth();
        if (width / 2 <= height && width / 2 <= i) {
            height = this.m - (width / 2);
        } else if (height <= i) {
            height = this.t.leftMargin;
        } else {
            height = this.d.widthPixels - (this.t.rightMargin + width);
        }
        this.r.setX((float) height);
    }

    public T b(View view) {
        if (view != null) {
            this.l = view;
            int[] iArr = new int[2];
            this.l.getLocationOnScreen(iArr);
            this.m = iArr[0] + (view.getWidth() / 2);
            if (this.o == 48) {
                this.n = (iArr[1] - com.flyco.dialog.b.b.a(this.c)) - c(1.0f);
            } else {
                this.n = ((iArr[1] - com.flyco.dialog.b.b.a(this.c)) + view.getHeight()) + c(1.0f);
            }
        }
        return this;
    }

    public T b(int i) {
        this.u = i;
        return this;
    }

    public T d(float f) {
        this.v = c(f);
        return this;
    }

    public T a(float f, float f2) {
        this.w = c(f);
        this.x = c(f2);
        return this;
    }

    public T e(float f) {
        this.y = c(f);
        return this;
    }

    public T f(float f) {
        this.z = c(f);
        return this;
    }
}
