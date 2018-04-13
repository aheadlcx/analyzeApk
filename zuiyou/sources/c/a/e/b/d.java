package c.a.e.b;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import c.a.i.b.a;
import c.a.i.u;

public class d extends CoordinatorLayout implements u {
    private a a;

    public d(Context context) {
        this(context, null);
    }

    public d(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public d(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new a(this);
        this.a.a(attributeSet, 0);
    }

    public void d() {
        if (this.a != null) {
            this.a.a();
        }
    }
}
