package c.a.i.b;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.TextView;
import c.a.a.b;
import c.a.a.d;
import c.a.d.a.a;

public class i extends e {
    private static final String f = i.class.getSimpleName();
    final TextView a;
    protected int b = 0;
    protected int c = 0;
    protected int d = 0;
    protected int e = 0;
    private int g = 0;
    private int h = 0;

    public static i a(TextView textView) {
        if (VERSION.SDK_INT >= 17) {
            return new j(textView);
        }
        return new i(textView);
    }

    public i(TextView textView) {
        this.a = textView;
    }

    public void a(AttributeSet attributeSet, int i) {
        Context context = this.a.getContext();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.SkinCompatTextHelper, i, 0);
        int resourceId = obtainStyledAttributes.getResourceId(d.SkinCompatTextHelper_android_textAppearance, 0);
        if (obtainStyledAttributes.hasValue(d.SkinCompatTextHelper_android_drawableLeft)) {
            this.c = obtainStyledAttributes.getResourceId(d.SkinCompatTextHelper_android_drawableLeft, 0);
        }
        if (obtainStyledAttributes.hasValue(d.SkinCompatTextHelper_android_drawableTop)) {
            this.e = obtainStyledAttributes.getResourceId(d.SkinCompatTextHelper_android_drawableTop, 0);
        }
        if (obtainStyledAttributes.hasValue(d.SkinCompatTextHelper_android_drawableRight)) {
            this.d = obtainStyledAttributes.getResourceId(d.SkinCompatTextHelper_android_drawableRight, 0);
        }
        if (obtainStyledAttributes.hasValue(d.SkinCompatTextHelper_android_drawableBottom)) {
            this.b = obtainStyledAttributes.getResourceId(d.SkinCompatTextHelper_android_drawableBottom, 0);
        }
        obtainStyledAttributes.recycle();
        if (resourceId != 0) {
            obtainStyledAttributes = context.obtainStyledAttributes(resourceId, d.SkinTextAppearance);
            if (obtainStyledAttributes.hasValue(d.SkinTextAppearance_android_textColor)) {
                this.g = obtainStyledAttributes.getResourceId(d.SkinTextAppearance_android_textColor, 0);
            }
            if (obtainStyledAttributes.hasValue(d.SkinTextAppearance_android_textColorHint)) {
                this.h = obtainStyledAttributes.getResourceId(d.SkinTextAppearance_android_textColorHint, 0);
            }
            obtainStyledAttributes.recycle();
        }
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, d.SkinTextAppearance, i, 0);
        if (obtainStyledAttributes2.hasValue(d.SkinTextAppearance_android_textColor)) {
            this.g = obtainStyledAttributes2.getResourceId(d.SkinTextAppearance_android_textColor, 0);
        }
        if (obtainStyledAttributes2.hasValue(d.SkinTextAppearance_android_textColorHint)) {
            this.h = obtainStyledAttributes2.getResourceId(d.SkinTextAppearance_android_textColorHint, 0);
        }
        obtainStyledAttributes2.recycle();
        d();
    }

    public void a(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, d.SkinTextAppearance);
        if (obtainStyledAttributes.hasValue(d.SkinTextAppearance_android_textColor)) {
            this.g = obtainStyledAttributes.getResourceId(d.SkinTextAppearance_android_textColor, 0);
        }
        if (obtainStyledAttributes.hasValue(d.SkinTextAppearance_android_textColorHint)) {
            this.h = obtainStyledAttributes.getResourceId(d.SkinTextAppearance_android_textColorHint, 0);
        }
        obtainStyledAttributes.recycle();
        f();
        e();
    }

    private void e() {
        this.h = e.b(this.h);
        if (this.h != b.abc_hint_foreground_material_light && this.h != 0) {
            try {
                this.a.setHintTextColor(a.a().c(this.h));
            } catch (Exception e) {
            }
        }
    }

    private void f() {
        this.g = e.b(this.g);
        if (this.g != b.abc_primary_text_disable_only_material_light && this.g != b.abc_secondary_text_material_light && this.g != 0) {
            try {
                this.a.setTextColor(a.a().c(this.g));
            } catch (Exception e) {
            }
        }
    }

    public void a(@DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
        this.c = i;
        this.e = i2;
        this.d = i3;
        this.b = i4;
        a();
    }

    public void b(@DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
        this.c = i;
        this.e = i2;
        this.d = i3;
        this.b = i4;
        b();
    }

    protected void a() {
        b();
    }

    protected void b() {
        Drawable b;
        Drawable b2;
        Drawable b3;
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
            drawable = a.a().b(this.b);
        }
        if (this.c != 0 || this.e != 0 || this.d != 0 || this.b != 0) {
            this.a.setCompoundDrawablesWithIntrinsicBounds(b, b2, b3, drawable);
        }
    }

    public int c() {
        return this.g;
    }

    public void d() {
        a();
        f();
        e();
    }

    public void a(@ColorRes int i) {
        this.g = i;
        f();
    }

    public void c(@ColorRes int i) {
        this.h = i;
        e();
    }
}
