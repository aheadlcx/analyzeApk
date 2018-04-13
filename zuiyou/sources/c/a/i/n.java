package c.a.i;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import c.a.i.b.a;

public class n extends RadioGroup implements u {
    private a a;

    public n(Context context) {
        this(context, null);
    }

    public n(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new a(this);
        this.a.a(attributeSet, 0);
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
