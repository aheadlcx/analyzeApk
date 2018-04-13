package cn.xiaochuankeji.tieba.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import c.a.i.g;

public class StateLayout extends g {
    private View a;
    private View b;
    private View c;
    private View d;
    private int e;

    public StateLayout(Context context) {
        this(context, null);
    }

    public StateLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StateLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 3;
    }

    public StateLayout a(int i) {
        this.a = findViewById(i);
        return this;
    }

    public StateLayout b(int i) {
        this.b = findViewById(i);
        return this;
    }

    public void setState(int i) {
        if (this.e != i) {
            this.e = i;
            switch (i) {
                case 0:
                    a();
                    return;
                case 1:
                    b();
                    return;
                case 2:
                    c();
                    return;
                case 3:
                    e();
                    return;
                default:
                    return;
            }
        }
    }

    public int getState() {
        return this.e;
    }

    private void a() {
        a(this.a);
        b(this.b);
        b(this.c);
        b(this.d);
    }

    private void b() {
        a(this.b);
        b(this.a);
        b(this.c);
        b(this.d);
    }

    private void c() {
        a(this.c);
        b(this.b);
        b(this.a);
        b(this.d);
    }

    private void e() {
        a(this.d);
        b(this.b);
        b(this.c);
        b(this.a);
    }

    private void a(View view) {
        if (view != null) {
            view.setVisibility(0);
        }
    }

    private void b(View view) {
        if (view != null) {
            view.setVisibility(8);
        }
    }
}
