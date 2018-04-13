package c.a.e.b;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;
import c.a.e.a.d;
import c.a.i.b.a;
import c.a.i.b.e;
import c.a.i.u;

public class c extends CollapsingToolbarLayout implements u {
    private int a;
    private int b;
    private a c;

    public c(Context context) {
        this(context, null);
    }

    public c(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 0;
        this.b = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.CollapsingToolbarLayout, i, c.a.e.a.c.Widget_Design_CollapsingToolbar);
        this.a = obtainStyledAttributes.getResourceId(d.CollapsingToolbarLayout_contentScrim, 0);
        this.b = obtainStyledAttributes.getResourceId(d.CollapsingToolbarLayout_statusBarScrim, 0);
        obtainStyledAttributes.recycle();
        b();
        a();
        this.c = new a(this);
        this.c.a(attributeSet, 0);
    }

    private void a() {
        this.b = e.b(this.b);
        if (this.b != 0) {
            setStatusBarScrim(c.a.d.a.a.a().b(this.b));
        }
    }

    private void b() {
        this.a = e.b(this.a);
        if (this.a != 0) {
            setContentScrim(c.a.d.a.a.a().b(this.a));
        }
    }

    public void d() {
        b();
        a();
        if (this.c != null) {
            this.c.a();
        }
    }
}
