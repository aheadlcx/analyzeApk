package c.a.i;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import c.a.i.b.a;

public class q extends RelativeLayout implements u {
    private a a;

    public q(Context context) {
        this(context, null);
    }

    public q(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public q(Context context, AttributeSet attributeSet, int i) {
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
