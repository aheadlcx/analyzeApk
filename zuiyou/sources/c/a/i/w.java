package c.a.i;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;
import c.a.i.a.a;
import c.a.i.b.i;

public class w extends AppCompatTextView implements a, u {
    private i a;
    private c.a.i.b.a b;

    public w(Context context) {
        this(context, null);
    }

    public w(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public w(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new c.a.i.b.a(this);
        this.b.a(attributeSet, i);
        this.a = i.a((TextView) this);
        this.a.a(attributeSet, i);
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        if (this.b != null) {
            this.b.a(i);
        }
    }

    public void setTextAppearance(int i) {
        setTextAppearance(getContext(), i);
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (this.a != null) {
            this.a.a(context, i);
        }
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(@DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
        if (this.a != null) {
            this.a.a(i, i2, i3, i4);
        }
    }

    public void setCompoundDrawablesWithIntrinsicBounds(@DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
        super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
        if (this.a != null) {
            this.a.b(i, i2, i3, i4);
        }
    }

    public void d() {
        if (this.b != null) {
            this.b.a();
        }
        if (this.a != null) {
            this.a.d();
        }
    }

    public void setTextColorResource(@ColorRes int i) {
        if (this.a != null) {
            this.a.a(i);
        }
    }

    public void setTextHintColorResource(int i) {
        if (this.a != null) {
            this.a.c(i);
        }
    }
}
