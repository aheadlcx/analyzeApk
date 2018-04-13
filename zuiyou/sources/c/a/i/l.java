package c.a.i;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import c.a.i.b.g;

public class l extends ProgressBar implements u {
    private g a;

    public l(Context context) {
        this(context, null);
    }

    public l(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842871);
    }

    public l(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new g(this);
        this.a.a(attributeSet, i);
    }

    public void d() {
        if (this.a != null) {
            this.a.a();
        }
    }
}
