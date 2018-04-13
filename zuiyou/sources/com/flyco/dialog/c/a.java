package com.flyco.dialog.c;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class a extends com.flyco.dialog.c.b.a<a> {
    private View P;
    private View Q;
    private View R;
    private View S;
    private int T;
    private float U;
    private int V;
    private int W;

    public a(Context context) {
        super(context);
        this.T = Color.parseColor("#61AEDC");
        this.U = 1.0f;
        this.V = Color.parseColor("#DCDCDC");
        this.W = 0;
        this.n = Color.parseColor("#61AEDC");
        this.o = 22.0f;
        this.t = Color.parseColor("#383838");
        this.u = 17.0f;
        this.D = Color.parseColor("#8a000000");
        this.E = Color.parseColor("#8a000000");
        this.F = Color.parseColor("#8a000000");
    }

    public View a() {
        this.l.setLayoutParams(new LayoutParams(-1, -2));
        this.a.addView(this.l);
        this.P = new View(this.c);
        this.a.addView(this.P);
        this.q.setLayoutParams(new LayoutParams(-1, -2));
        this.a.addView(this.q);
        this.S = new View(this.c);
        this.S.setLayoutParams(new LayoutParams(-1, 1));
        this.a.addView(this.S);
        this.x.setLayoutParams(new LayoutParams(0, c(45.0f), 1.0f));
        this.w.addView(this.x);
        this.Q = new View(this.c);
        this.Q.setLayoutParams(new LayoutParams(1, -1));
        this.w.addView(this.Q);
        this.z.setLayoutParams(new LayoutParams(0, c(45.0f), 1.0f));
        this.w.addView(this.z);
        this.R = new View(this.c);
        this.R.setLayoutParams(new LayoutParams(1, -1));
        this.w.addView(this.R);
        this.y.setLayoutParams(new LayoutParams(0, c(45.0f), 1.0f));
        this.w.addView(this.y);
        this.a.addView(this.w);
        return this.a;
    }

    public void b() {
        int i;
        super.b();
        if (this.W == 0) {
            this.l.setMinHeight(c(48.0f));
            this.l.setGravity(16);
            this.l.setPadding(c(15.0f), c(5.0f), c(0.0f), c(5.0f));
            this.l.setVisibility(this.p ? 0 : 8);
        } else if (this.W == 1) {
            this.l.setGravity(17);
            this.l.setPadding(c(0.0f), c(15.0f), c(0.0f), c(0.0f));
        }
        this.P.setLayoutParams(new LayoutParams(-1, c(this.U)));
        this.P.setBackgroundColor(this.T);
        View view = this.P;
        if (this.p && this.W == 0) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        if (this.W == 0) {
            this.q.setPadding(c(15.0f), c(10.0f), c(15.0f), c(10.0f));
            this.q.setMinHeight(c(68.0f));
            this.q.setGravity(this.s);
        } else if (this.W == 1) {
            this.q.setPadding(c(15.0f), c(7.0f), c(15.0f), c(20.0f));
            this.q.setMinHeight(c(56.0f));
            this.q.setGravity(17);
        }
        this.S.setBackgroundColor(this.V);
        this.Q.setBackgroundColor(this.V);
        this.R.setBackgroundColor(this.V);
        if (this.v == 1) {
            this.x.setVisibility(8);
            this.y.setVisibility(8);
            this.Q.setVisibility(8);
            this.R.setVisibility(8);
        } else if (this.v == 2) {
            this.z.setVisibility(8);
            this.Q.setVisibility(8);
        }
        float c = (float) c(this.N);
        this.a.setBackgroundDrawable(com.flyco.dialog.b.a.a(this.O, c));
        this.x.setBackgroundDrawable(com.flyco.dialog.b.a.a(c, this.O, this.J, 0));
        this.y.setBackgroundDrawable(com.flyco.dialog.b.a.a(c, this.O, this.J, 1));
        TextView textView = this.z;
        if (this.v != 1) {
            c = 0.0f;
        }
        textView.setBackgroundDrawable(com.flyco.dialog.b.a.a(c, this.O, this.J, -1));
    }

    public a a(int i) {
        this.W = i;
        return this;
    }
}
