package c.a.i;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.util.Log;
import c.a.a.d;
import c.a.i.b.a;
import c.a.i.b.e;

public class t extends AppCompatSpinner implements u {
    private static final String a = t.class.getSimpleName();
    private static final int[] b = new int[]{16843505};
    private a c;
    private int d;

    public t(Context context) {
        this(context, null);
    }

    public t(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.a.spinnerStyle);
    }

    public t(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }

    public t(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i, i2, null);
    }

    @SuppressLint({"RestrictedApi"})
    public t(Context context, AttributeSet attributeSet, int i, int i2, Theme theme) {
        super(context, attributeSet, i, i2, theme);
        this.d = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.Spinner, i, 0);
        if (getPopupContext() != null) {
            if (i2 == -1) {
                if (VERSION.SDK_INT >= 11) {
                    TypedArray typedArray = null;
                    try {
                        typedArray = context.obtainStyledAttributes(attributeSet, b, i, 0);
                        if (typedArray.hasValue(0)) {
                            i2 = typedArray.getInt(0, 0);
                        }
                        if (typedArray != null) {
                            typedArray.recycle();
                        }
                    } catch (Throwable e) {
                        Log.i(a, "Could not read android:spinnerMode", e);
                        if (typedArray != null) {
                            typedArray.recycle();
                        }
                    } catch (Throwable th) {
                        if (typedArray != null) {
                            typedArray.recycle();
                        }
                    }
                } else {
                    i2 = 1;
                }
            }
            if (i2 == 1) {
                TypedArray obtainStyledAttributes2 = getPopupContext().obtainStyledAttributes(attributeSet, d.Spinner, i, 0);
                this.d = obtainStyledAttributes2.getResourceId(d.Spinner_android_popupBackground, 0);
                obtainStyledAttributes2.recycle();
            }
        }
        obtainStyledAttributes.recycle();
        this.c = new a(this);
        this.c.a(attributeSet, i);
    }

    public void setPopupBackgroundResource(@DrawableRes int i) {
        super.setPopupBackgroundResource(i);
        this.d = i;
        a();
    }

    private void a() {
        this.d = e.b(this.d);
        if (this.d != 0) {
            setPopupBackgroundDrawable(c.a.d.a.a.a().b(this.d));
        }
    }

    public void d() {
        if (this.c != null) {
            this.c.a();
        }
        a();
    }
}
