package c.a.e.b;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.StyleRes;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import c.a.e.a.b;
import c.a.e.a.c;
import c.a.e.a.d;
import c.a.i.b.a;
import c.a.i.b.e;
import c.a.i.f;
import c.a.i.u;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class i extends TextInputLayout implements u {
    private static final String a = i.class.getSimpleName();
    private a b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;

    public i(Context context) {
        this(context, null);
    }

    public i(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public i(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.b = new a(this);
        this.b.a(attributeSet, i);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, d.TextInputLayout, i, c.Widget_Design_TextInputLayout);
        if (obtainStyledAttributes.hasValue(d.TextInputLayout_android_textColorHint)) {
            int resourceId = obtainStyledAttributes.getResourceId(d.TextInputLayout_android_textColorHint, 0);
            this.f = resourceId;
            this.g = resourceId;
            e();
        }
        b(obtainStyledAttributes.getResourceId(d.TextInputLayout_errorTextAppearance, 0));
        a(obtainStyledAttributes.getResourceId(d.TextInputLayout_counterTextAppearance, 0));
        this.c = obtainStyledAttributes.getResourceId(d.TextInputLayout_passwordToggleDrawable, 0);
        obtainStyledAttributes.recycle();
    }

    private void a(@StyleRes int i) {
        if (i != 0) {
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(getContext(), i, c.a.a.d.SkinTextAppearance);
            if (obtainStyledAttributes.hasValue(c.a.a.d.SkinTextAppearance_android_textColor)) {
                this.d = obtainStyledAttributes.getResourceId(c.a.a.d.SkinTextAppearance_android_textColor, 0);
            }
            obtainStyledAttributes.recycle();
        }
        a();
    }

    public void setCounterEnabled(boolean z) {
        super.setCounterEnabled(z);
        if (z) {
            a();
        }
    }

    private void a() {
        this.d = e.b(this.d);
        if (this.d != 0) {
            TextView counterView = getCounterView();
            if (counterView != null) {
                counterView.setTextColor(c.a.d.a.a.a().a(this.d));
                c();
            }
        }
    }

    private TextView getCounterView() {
        try {
            Field declaredField = TextInputLayout.class.getDeclaredField("mCounterView");
            declaredField.setAccessible(true);
            return (TextView) declaredField.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setErrorTextAppearance(@StyleRes int i) {
        super.setErrorTextAppearance(i);
        b(i);
    }

    private void b(@StyleRes int i) {
        if (i != 0) {
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(getContext(), i, c.a.a.d.SkinTextAppearance);
            if (obtainStyledAttributes.hasValue(c.a.a.d.SkinTextAppearance_android_textColor)) {
                this.e = obtainStyledAttributes.getResourceId(c.a.a.d.SkinTextAppearance_android_textColor, 0);
            }
            obtainStyledAttributes.recycle();
        }
        b();
    }

    public void setErrorEnabled(boolean z) {
        super.setErrorEnabled(z);
        if (z) {
            b();
        }
    }

    private void b() {
        this.e = e.b(this.e);
        if (this.e != 0 && this.e != b.design_error) {
            TextView errorView = getErrorView();
            if (errorView != null) {
                errorView.setTextColor(c.a.d.a.a.a().a(this.e));
                c();
            }
        }
    }

    private TextView getErrorView() {
        try {
            Field declaredField = TextInputLayout.class.getDeclaredField("mErrorView");
            declaredField.setAccessible(true);
            return (TextView) declaredField.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void c() {
        try {
            Method declaredMethod = TextInputLayout.class.getDeclaredMethod("updateEditTextBackground", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDefaultTextColor(ColorStateList colorStateList) {
        try {
            Field declaredField = TextInputLayout.class.getDeclaredField("mDefaultTextColor");
            declaredField.setAccessible(true);
            declaredField.set(this, colorStateList);
            f();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void e() {
        this.f = e.b(this.f);
        if (this.f != 0 && this.f != b.abc_hint_foreground_material_light) {
            setFocusedTextColor(c.a.d.a.a.a().c(this.f));
        } else if (getEditText() != null) {
            int i = 0;
            if (getEditText() instanceof f) {
                i = ((f) getEditText()).getTextColorResId();
            } else if (getEditText() instanceof h) {
                i = ((h) getEditText()).getTextColorResId();
            }
            i = e.b(i);
            if (i != 0) {
                setFocusedTextColor(c.a.d.a.a.a().c(i));
            }
        }
    }

    private void setFocusedTextColor(ColorStateList colorStateList) {
        try {
            Field declaredField = TextInputLayout.class.getDeclaredField("mFocusedTextColor");
            declaredField.setAccessible(true);
            declaredField.set(this, colorStateList);
            f();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void f() {
        try {
            Method declaredMethod = TextInputLayout.class.getDeclaredMethod("updateLabelState", new Class[]{Boolean.TYPE});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, new Object[]{Boolean.valueOf(false)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void d() {
        b();
        a();
        e();
        if (this.b != null) {
            this.b.a();
        }
    }
}
