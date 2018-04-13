package c.a.e.b;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import c.a.d.a.a;
import c.a.e.a.c;
import c.a.e.a.d;
import c.a.i.b.f;
import c.a.i.u;

public class e extends FloatingActionButton implements u {
    private int a;
    private int b;
    private f c;

    public e(Context context) {
        this(context, null);
    }

    public e(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public e(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 0;
        this.b = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.FloatingActionButton, i, c.Widget_Design_FloatingActionButton);
        this.b = obtainStyledAttributes.getResourceId(d.FloatingActionButton_backgroundTint, 0);
        this.a = obtainStyledAttributes.getResourceId(d.FloatingActionButton_rippleColor, 0);
        obtainStyledAttributes.recycle();
        a();
        b();
        this.c = new f(this);
        this.c.a(attributeSet, i);
    }

    private void a() {
        this.b = c.a.i.b.e.b(this.b);
        if (this.b != 0) {
            setBackgroundTintList(a.a().c(this.b));
        }
    }

    private void b() {
        this.a = c.a.i.b.e.b(this.a);
        if (this.a != 0) {
            setRippleColor(a.a().a(this.a));
        }
    }

    public void d() {
        a();
        b();
        if (this.c != null) {
            this.c.a();
        }
    }
}
