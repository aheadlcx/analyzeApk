package c.a.c;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import c.a.i.u;

public class a extends ConstraintLayout implements u {
    private final c.a.i.b.a a;

    public a(Context context) {
        this(context, null);
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new c.a.i.b.a(this);
        this.a.a(attributeSet, i);
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.a != null) {
            this.a.a(i);
        }
    }

    public void d() {
        if (this.a != null) {
            this.a.a();
        }
    }
}
