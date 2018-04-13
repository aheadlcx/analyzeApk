package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.widget.AutoSizeableTextView;
import android.support.v7.appcompat.R;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import java.lang.ref.WeakReference;

@RequiresApi(9)
class x {
    final TextView a;
    private ct b;
    private ct c;
    private ct d;
    private ct e;
    @NonNull
    private final aa f;
    private int g = 0;
    private Typeface h;
    private boolean i;

    static x a(TextView textView) {
        if (VERSION.SDK_INT >= 17) {
            return new z(textView);
        }
        return new x(textView);
    }

    x(TextView textView) {
        this.a = textView;
        this.f = new aa(this.a);
    }

    @SuppressLint({"NewApi"})
    void a(AttributeSet attributeSet, int i) {
        TintTypedArray obtainStyledAttributes;
        boolean z;
        boolean z2;
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3 = null;
        Context context = this.a.getContext();
        AppCompatDrawableManager appCompatDrawableManager = AppCompatDrawableManager.get();
        TintTypedArray obtainStyledAttributes2 = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.AppCompatTextHelper, i, 0);
        int resourceId = obtainStyledAttributes2.getResourceId(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
        if (obtainStyledAttributes2.hasValue(R.styleable.AppCompatTextHelper_android_drawableLeft)) {
            this.b = a(context, appCompatDrawableManager, obtainStyledAttributes2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (obtainStyledAttributes2.hasValue(R.styleable.AppCompatTextHelper_android_drawableTop)) {
            this.c = a(context, appCompatDrawableManager, obtainStyledAttributes2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (obtainStyledAttributes2.hasValue(R.styleable.AppCompatTextHelper_android_drawableRight)) {
            this.d = a(context, appCompatDrawableManager, obtainStyledAttributes2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (obtainStyledAttributes2.hasValue(R.styleable.AppCompatTextHelper_android_drawableBottom)) {
            this.e = a(context, appCompatDrawableManager, obtainStyledAttributes2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableBottom, 0));
        }
        obtainStyledAttributes2.recycle();
        boolean z3 = this.a.getTransformationMethod() instanceof PasswordTransformationMethod;
        if (resourceId != -1) {
            obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, resourceId, R.styleable.TextAppearance);
            if (z3 || !obtainStyledAttributes.hasValue(R.styleable.TextAppearance_textAllCaps)) {
                z = false;
                z2 = false;
            } else {
                z2 = obtainStyledAttributes.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
                z = true;
            }
            a(context, obtainStyledAttributes);
            if (VERSION.SDK_INT < 23) {
                if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textColor)) {
                    colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.TextAppearance_android_textColor);
                } else {
                    colorStateList = null;
                }
                if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
                    colorStateList2 = obtainStyledAttributes.getColorStateList(R.styleable.TextAppearance_android_textColorHint);
                } else {
                    colorStateList2 = null;
                }
                if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textColorLink)) {
                    colorStateList3 = obtainStyledAttributes.getColorStateList(R.styleable.TextAppearance_android_textColorLink);
                }
            } else {
                colorStateList2 = null;
                colorStateList = null;
            }
            obtainStyledAttributes.recycle();
        } else {
            colorStateList2 = null;
            colorStateList = null;
            z = false;
            z2 = false;
        }
        obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.TextAppearance, i, 0);
        if (!z3 && obtainStyledAttributes.hasValue(R.styleable.TextAppearance_textAllCaps)) {
            z2 = obtainStyledAttributes.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
            z = true;
        }
        if (VERSION.SDK_INT < 23) {
            if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textColor)) {
                colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.TextAppearance_android_textColor);
            }
            if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
                colorStateList2 = obtainStyledAttributes.getColorStateList(R.styleable.TextAppearance_android_textColorHint);
            }
            if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textColorLink)) {
                colorStateList3 = obtainStyledAttributes.getColorStateList(R.styleable.TextAppearance_android_textColorLink);
            }
        }
        a(context, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        if (colorStateList != null) {
            this.a.setTextColor(colorStateList);
        }
        if (colorStateList2 != null) {
            this.a.setHintTextColor(colorStateList2);
        }
        if (colorStateList3 != null) {
            this.a.setLinkTextColor(colorStateList3);
        }
        if (!z3 && r0) {
            a(z2);
        }
        if (this.h != null) {
            this.a.setTypeface(this.h, this.g);
        }
        this.f.a(attributeSet, i);
        if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && this.f.a() != 0) {
            int[] e = this.f.e();
            if (e.length <= 0) {
                return;
            }
            if (((float) this.a.getAutoSizeStepGranularity()) != -1.0f) {
                this.a.setAutoSizeTextTypeUniformWithConfiguration(this.f.c(), this.f.d(), this.f.b(), 0);
            } else {
                this.a.setAutoSizeTextTypeUniformWithPresetSizes(e, 0);
            }
        }
    }

    private void a(Context context, TintTypedArray tintTypedArray) {
        boolean z = true;
        this.g = tintTypedArray.getInt(R.styleable.TextAppearance_android_textStyle, this.g);
        if (tintTypedArray.hasValue(R.styleable.TextAppearance_android_fontFamily) || tintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)) {
            this.h = null;
            int i = tintTypedArray.hasValue(R.styleable.TextAppearance_android_fontFamily) ? R.styleable.TextAppearance_android_fontFamily : R.styleable.TextAppearance_fontFamily;
            if (!context.isRestricted()) {
                try {
                    this.h = tintTypedArray.getFont(i, this.g, new y(this, new WeakReference(this.a)));
                    if (this.h != null) {
                        z = false;
                    }
                    this.i = z;
                } catch (UnsupportedOperationException e) {
                } catch (NotFoundException e2) {
                }
            }
            if (this.h == null) {
                String string = tintTypedArray.getString(i);
                if (string != null) {
                    this.h = Typeface.create(string, this.g);
                }
            }
        } else if (tintTypedArray.hasValue(R.styleable.TextAppearance_android_typeface)) {
            this.i = false;
            switch (tintTypedArray.getInt(R.styleable.TextAppearance_android_typeface, 1)) {
                case 1:
                    this.h = Typeface.SANS_SERIF;
                    return;
                case 2:
                    this.h = Typeface.SERIF;
                    return;
                case 3:
                    this.h = Typeface.MONOSPACE;
                    return;
                default:
                    return;
            }
        }
    }

    private void a(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.i) {
            this.h = typeface;
            TextView textView = (TextView) weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.g);
            }
        }
    }

    void a(Context context, int i) {
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, i, R.styleable.TextAppearance);
        if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_textAllCaps)) {
            a(obtainStyledAttributes.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
        }
        if (VERSION.SDK_INT < 23 && obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textColor)) {
            ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.TextAppearance_android_textColor);
            if (colorStateList != null) {
                this.a.setTextColor(colorStateList);
            }
        }
        a(context, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        if (this.h != null) {
            this.a.setTypeface(this.h, this.g);
        }
    }

    void a(boolean z) {
        this.a.setAllCaps(z);
    }

    void a() {
        if (this.b != null || this.c != null || this.d != null || this.e != null) {
            Drawable[] compoundDrawables = this.a.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.e);
        }
    }

    final void a(Drawable drawable, ct ctVar) {
        if (drawable != null && ctVar != null) {
            AppCompatDrawableManager.a(drawable, ctVar, this.a.getDrawableState());
        }
    }

    protected static ct a(Context context, AppCompatDrawableManager appCompatDrawableManager, int i) {
        ColorStateList a = appCompatDrawableManager.a(context, i);
        if (a == null) {
            return null;
        }
        ct ctVar = new ct();
        ctVar.mHasTintList = true;
        ctVar.mTintList = a;
        return ctVar;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    void a(boolean z, int i, int i2, int i3, int i4) {
        if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
            b();
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    void a(int i, float f) {
        if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && !c()) {
            b(i, f);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    void b() {
        this.f.f();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    boolean c() {
        return this.f.g();
    }

    private void b(int i, float f) {
        this.f.a(i, f);
    }

    void a(int i) {
        this.f.a(i);
    }

    void a(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        this.f.a(i, i2, i3, i4);
    }

    void a(@NonNull int[] iArr, int i) throws IllegalArgumentException {
        this.f.a(iArr, i);
    }

    int d() {
        return this.f.a();
    }

    int e() {
        return this.f.b();
    }

    int f() {
        return this.f.c();
    }

    int g() {
        return this.f.d();
    }

    int[] h() {
        return this.f.e();
    }
}
