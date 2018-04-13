package c.a.i.b;

import android.annotation.TargetApi;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;
import c.a.a.d;
import c.a.d.a.a;

@TargetApi(17)
@RequiresApi(17)
public class j extends i {
    private int f = 0;
    private int g = 0;

    public j(TextView textView) {
        super(textView);
    }

    public void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.a.getContext().obtainStyledAttributes(attributeSet, d.SkinCompatTextHelper, i, 0);
        if (obtainStyledAttributes.hasValue(d.SkinCompatTextHelper_android_drawableStart)) {
            this.f = obtainStyledAttributes.getResourceId(d.SkinCompatTextHelper_android_drawableStart, 0);
            this.f = e.b(this.f);
        }
        if (obtainStyledAttributes.hasValue(d.SkinCompatTextHelper_android_drawableEnd)) {
            this.g = obtainStyledAttributes.getResourceId(d.SkinCompatTextHelper_android_drawableEnd, 0);
            this.g = e.b(this.g);
        }
        obtainStyledAttributes.recycle();
        super.a(attributeSet, i);
    }

    public void a(@DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
        this.f = i;
        this.e = i2;
        this.g = i3;
        this.b = i4;
        a();
    }

    protected void a() {
        Drawable b;
        Drawable b2;
        Drawable b3;
        Drawable b4;
        Drawable b5;
        Drawable drawable = null;
        this.c = e.b(this.c);
        if (this.c != 0) {
            b = a.a().b(this.c);
        } else {
            b = null;
        }
        this.e = e.b(this.e);
        if (this.e != 0) {
            b2 = a.a().b(this.e);
        } else {
            b2 = null;
        }
        this.d = e.b(this.d);
        if (this.d != 0) {
            b3 = a.a().b(this.d);
        } else {
            b3 = null;
        }
        this.b = e.b(this.b);
        if (this.b != 0) {
            b4 = a.a().b(this.b);
        } else {
            b4 = null;
        }
        if (this.f != 0) {
            b5 = a.a().b(this.f);
        } else {
            b5 = null;
        }
        if (b5 != null) {
            b = b5;
        }
        if (this.g != 0) {
            drawable = a.a().b(this.g);
        }
        if (drawable != null) {
            b3 = drawable;
        }
        if (this.c != 0 || this.e != 0 || this.d != 0 || this.b != 0 || this.f != 0 || this.g != 0) {
            this.a.setCompoundDrawablesWithIntrinsicBounds(b, b2, b3, b4);
        }
    }
}
