package c.a.e.b;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;
import c.a.i.u;

public class a extends AppBarLayout implements u {
    private c.a.i.b.a a;

    public a(Context context) {
        this(context, null);
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new c.a.i.b.a(this);
        this.a.a(attributeSet, 0);
    }

    public void d() {
        if (this.a != null) {
            this.a.a();
        }
    }
}
