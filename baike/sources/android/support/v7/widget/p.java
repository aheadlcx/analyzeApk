package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;

class p {
    private final View a;
    private final AppCompatDrawableManager b;
    private int c = -1;
    private ct d;
    private ct e;
    private ct f;

    p(View view) {
        this.a = view;
        this.b = AppCompatDrawableManager.get();
    }

    void a(AttributeSet attributeSet, int i) {
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.a.getContext(), attributeSet, R.styleable.ViewBackgroundHelper, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_android_background)) {
                this.c = obtainStyledAttributes.getResourceId(R.styleable.ViewBackgroundHelper_android_background, -1);
                ColorStateList a = this.b.a(this.a.getContext(), this.c);
                if (a != null) {
                    b(a);
                }
            }
            if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_backgroundTint)) {
                ViewCompat.setBackgroundTintList(this.a, obtainStyledAttributes.getColorStateList(R.styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_backgroundTintMode)) {
                ViewCompat.setBackgroundTintMode(this.a, DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
            obtainStyledAttributes.recycle();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    void a(int i) {
        this.c = i;
        b(this.b != null ? this.b.a(this.a.getContext(), i) : null);
        c();
    }

    void a(Drawable drawable) {
        this.c = -1;
        b(null);
        c();
    }

    void a(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new ct();
        }
        this.e.mTintList = colorStateList;
        this.e.mHasTintList = true;
        c();
    }

    ColorStateList a() {
        return this.e != null ? this.e.mTintList : null;
    }

    void a(Mode mode) {
        if (this.e == null) {
            this.e = new ct();
        }
        this.e.mTintMode = mode;
        this.e.mHasTintMode = true;
        c();
    }

    Mode b() {
        return this.e != null ? this.e.mTintMode : null;
    }

    void c() {
        Drawable background = this.a.getBackground();
        if (background == null) {
            return;
        }
        if (!d() || !b(background)) {
            if (this.e != null) {
                AppCompatDrawableManager.a(background, this.e, this.a.getDrawableState());
            } else if (this.d != null) {
                AppCompatDrawableManager.a(background, this.d, this.a.getDrawableState());
            }
        }
    }

    void b(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new ct();
            }
            this.d.mTintList = colorStateList;
            this.d.mHasTintList = true;
        } else {
            this.d = null;
        }
        c();
    }

    private boolean d() {
        int i = VERSION.SDK_INT;
        if (i > 21) {
            if (this.d != null) {
                return true;
            }
            return false;
        } else if (i != 21) {
            return false;
        } else {
            return true;
        }
    }

    private boolean b(@NonNull Drawable drawable) {
        if (this.f == null) {
            this.f = new ct();
        }
        ct ctVar = this.f;
        ctVar.a();
        ColorStateList backgroundTintList = ViewCompat.getBackgroundTintList(this.a);
        if (backgroundTintList != null) {
            ctVar.mHasTintList = true;
            ctVar.mTintList = backgroundTintList;
        }
        Mode backgroundTintMode = ViewCompat.getBackgroundTintMode(this.a);
        if (backgroundTintMode != null) {
            ctVar.mHasTintMode = true;
            ctVar.mTintMode = backgroundTintMode;
        }
        if (!ctVar.mHasTintList && !ctVar.mHasTintMode) {
            return false;
        }
        AppCompatDrawableManager.a(drawable, ctVar, this.a.getDrawableState());
        return true;
    }
}
