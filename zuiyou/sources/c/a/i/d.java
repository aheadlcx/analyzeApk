package c.a.i;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;
import c.a.a.a;
import c.a.i.b.c;

public class d extends AppCompatCheckBox implements u {
    private c a;

    public d(Context context) {
        this(context, null);
    }

    public d(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.checkboxStyle);
    }

    public d(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new c(this);
        this.a.a(attributeSet, i);
    }

    public void setButtonDrawable(@DrawableRes int i) {
        super.setButtonDrawable(i);
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
