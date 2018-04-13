package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.ImageView;

@RestrictTo({Scope.LIBRARY_GROUP})
public class AppCompatImageHelper {
    private final ImageView a;
    private ct b;
    private ct c;
    private ct d;

    public AppCompatImageHelper(ImageView imageView) {
        this.a = imageView;
    }

    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.a.getContext(), attributeSet, R.styleable.AppCompatImageView, i, 0);
        try {
            Drawable drawable = this.a.getDrawable();
            if (drawable == null) {
                int resourceId = obtainStyledAttributes.getResourceId(R.styleable.AppCompatImageView_srcCompat, -1);
                if (resourceId != -1) {
                    drawable = AppCompatResources.getDrawable(this.a.getContext(), resourceId);
                    if (drawable != null) {
                        this.a.setImageDrawable(drawable);
                    }
                }
            }
            if (drawable != null) {
                DrawableUtils.a(drawable);
            }
            if (obtainStyledAttributes.hasValue(R.styleable.AppCompatImageView_tint)) {
                ImageViewCompat.setImageTintList(this.a, obtainStyledAttributes.getColorStateList(R.styleable.AppCompatImageView_tint));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.AppCompatImageView_tintMode)) {
                ImageViewCompat.setImageTintMode(this.a, DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(R.styleable.AppCompatImageView_tintMode, -1), null));
            }
            obtainStyledAttributes.recycle();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    public void setImageResource(int i) {
        if (i != 0) {
            Drawable drawable = AppCompatResources.getDrawable(this.a.getContext(), i);
            if (drawable != null) {
                DrawableUtils.a(drawable);
            }
            this.a.setImageDrawable(drawable);
        } else {
            this.a.setImageDrawable(null);
        }
        d();
    }

    boolean a() {
        Drawable background = this.a.getBackground();
        if (VERSION.SDK_INT < 21 || !(background instanceof RippleDrawable)) {
            return true;
        }
        return false;
    }

    void a(ColorStateList colorStateList) {
        if (this.c == null) {
            this.c = new ct();
        }
        this.c.mTintList = colorStateList;
        this.c.mHasTintList = true;
        d();
    }

    ColorStateList b() {
        return this.c != null ? this.c.mTintList : null;
    }

    void a(Mode mode) {
        if (this.c == null) {
            this.c = new ct();
        }
        this.c.mTintMode = mode;
        this.c.mHasTintMode = true;
        d();
    }

    Mode c() {
        return this.c != null ? this.c.mTintMode : null;
    }

    void d() {
        Drawable drawable = this.a.getDrawable();
        if (drawable != null) {
            DrawableUtils.a(drawable);
        }
        if (drawable == null) {
            return;
        }
        if (!e() || !a(drawable)) {
            if (this.c != null) {
                AppCompatDrawableManager.a(drawable, this.c, this.a.getDrawableState());
            } else if (this.b != null) {
                AppCompatDrawableManager.a(drawable, this.b, this.a.getDrawableState());
            }
        }
    }

    private boolean e() {
        int i = VERSION.SDK_INT;
        if (i > 21) {
            if (this.b != null) {
                return true;
            }
            return false;
        } else if (i != 21) {
            return false;
        } else {
            return true;
        }
    }

    private boolean a(@NonNull Drawable drawable) {
        if (this.d == null) {
            this.d = new ct();
        }
        ct ctVar = this.d;
        ctVar.a();
        ColorStateList imageTintList = ImageViewCompat.getImageTintList(this.a);
        if (imageTintList != null) {
            ctVar.mHasTintList = true;
            ctVar.mTintList = imageTintList;
        }
        Mode imageTintMode = ImageViewCompat.getImageTintMode(this.a);
        if (imageTintMode != null) {
            ctVar.mHasTintMode = true;
            ctVar.mTintMode = imageTintMode;
        }
        if (!ctVar.mHasTintList && !ctVar.mHasTintMode) {
            return false;
        }
        AppCompatDrawableManager.a(drawable, ctVar, this.a.getDrawableState());
        return true;
    }
}
