package c.a.i;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import c.a.i.b.a;

public abstract class aa extends ViewGroup implements u {
    private a a;

    public aa(Context context) {
        this(context, null);
    }

    public aa(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public aa(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new a(this);
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
