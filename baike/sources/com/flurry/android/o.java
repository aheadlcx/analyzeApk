package com.flurry.android;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;

final class o extends RelativeLayout {
    private u a;
    private Context b;
    private String c;
    private int d;
    private boolean e;
    private boolean f;

    o(u uVar, Context context, String str, int i) {
        super(context);
        this.a = uVar;
        this.b = context;
        this.c = str;
        this.d = i;
    }

    final void a() {
        if (!this.e) {
            View c = c();
            if (c != null) {
                removeAllViews();
                addView(c, b());
                c.a().a(new f((byte) 3, this.a.k()));
                this.e = true;
            } else if (!this.f) {
                c = new TextView(this.b);
                c.setText(u.b);
                c.setTextSize(1, 20.0f);
                addView(c, b());
            }
            this.f = true;
        }
    }

    private static LayoutParams b() {
        return new LayoutParams(-1, -1);
    }

    private synchronized y c() {
        y yVar;
        List a = this.a.a(this.b, Arrays.asList(new String[]{this.c}), null, this.d, false);
        if (a.isEmpty()) {
            yVar = null;
        } else {
            yVar = (y) a.get(0);
            yVar.setOnClickListener(this.a);
        }
        return yVar;
    }
}
