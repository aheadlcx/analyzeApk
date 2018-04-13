package c.a.i.b;

import android.content.res.TypedArray;
import android.support.v4.widget.CompoundButtonCompat;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import c.a.a.d;
import c.a.d.a.a;

public class c extends e {
    private final CompoundButton a;
    private int b = 0;
    private int c = 0;

    public c(CompoundButton compoundButton) {
        this.a = compoundButton;
    }

    public void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.a.getContext().obtainStyledAttributes(attributeSet, d.CompoundButton, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(d.CompoundButton_android_button)) {
                this.b = obtainStyledAttributes.getResourceId(d.CompoundButton_android_button, 0);
            }
            if (obtainStyledAttributes.hasValue(d.CompoundButton_buttonTint)) {
                this.c = obtainStyledAttributes.getResourceId(d.CompoundButton_buttonTint, 0);
            }
            obtainStyledAttributes.recycle();
            a();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    public void a(int i) {
        this.b = i;
        a();
    }

    public void a() {
        this.b = e.b(this.b);
        if (this.b != 0) {
            this.a.setButtonDrawable(a.a().b(this.b));
        }
        this.c = e.b(this.c);
        if (this.c != 0) {
            CompoundButtonCompat.setButtonTintList(this.a, a.a().c(this.c));
        }
    }
}
