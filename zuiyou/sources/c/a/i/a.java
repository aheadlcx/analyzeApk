package c.a.i;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import android.widget.TextView;
import c.a.i.b.e;
import c.a.i.b.i;

public class a extends AppCompatAutoCompleteTextView implements c.a.i.a.a, u {
    private static final int[] a = new int[]{16843126};
    private int b;
    private i c;
    private c.a.i.b.a d;

    public a(Context context) {
        this(context, null);
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.a.autoCompleteTextViewStyle);
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a, i, 0);
        if (obtainStyledAttributes.hasValue(0)) {
            this.b = obtainStyledAttributes.getResourceId(0, 0);
        }
        obtainStyledAttributes.recycle();
        a();
        this.d = new c.a.i.b.a(this);
        this.d.a(attributeSet, i);
        this.c = i.a((TextView) this);
        this.c.a(attributeSet, i);
    }

    public void setDropDownBackgroundResource(@DrawableRes int i) {
        super.setDropDownBackgroundResource(i);
        this.b = i;
        a();
    }

    private void a() {
        this.b = e.b(this.b);
        if (this.b != 0) {
            Drawable b = c.a.d.a.a.a().b(this.b);
            if (b != null) {
                setDropDownBackgroundDrawable(b);
            }
        }
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        if (this.d != null) {
            this.d.a(i);
        }
    }

    public void setTextAppearance(int i) {
        setTextAppearance(getContext(), i);
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (this.c != null) {
            this.c.a(context, i);
        }
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(@DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
        if (this.c != null) {
            this.c.a(i, i2, i3, i4);
        }
    }

    public void setCompoundDrawablesWithIntrinsicBounds(@DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
        super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
        if (this.c != null) {
            this.c.b(i, i2, i3, i4);
        }
    }

    public void d() {
        if (this.d != null) {
            this.d.a();
        }
        if (this.c != null) {
            this.c.d();
        }
        a();
    }

    public void setTextColorResource(@ColorRes int i) {
        if (this.c != null) {
            this.c.a(i);
        }
    }

    public void setTextHintColorResource(int i) {
        if (this.c != null) {
            this.c.c(i);
        }
    }
}
