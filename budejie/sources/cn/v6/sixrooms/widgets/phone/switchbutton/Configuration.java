package cn.v6.sixrooms.widgets.phone.switchbutton;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import java.lang.reflect.Field;

public class Configuration implements Cloneable {
    private Drawable a = null;
    private Drawable b = null;
    private Drawable c = null;
    private int d = a.b;
    private int e = a.a;
    private int f = a.c;
    private int g = a.d;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = -1;
    private int m = -1;
    private float n;
    private int o = -1;
    private float p = -1.0f;
    private float q = 0.0f;
    private Rect r;

    static class a {
        static int a = Color.parseColor("#E3E3E3");
        static int b = Color.parseColor("#02BFE7");
        static int c = Color.parseColor("#FFFFFF");
        static int d = Color.parseColor("#fafafa");
        static int e = 2;
        static int f = 999;
        static float g = 2.0f;
        static int h = 0;
    }

    static class b {
        static int a = 24;
    }

    private Configuration() {
    }

    public static Configuration getDefault(float f) {
        Configuration configuration = new Configuration();
        configuration.n = f;
        configuration.setThumbMarginInPixel(configuration.getDefaultThumbMarginInPixel());
        configuration.r = new Rect(a.h, a.h, a.h, a.h);
        return configuration;
    }

    public void setBackDrawable(Drawable drawable, Drawable drawable2) {
        if (drawable2 == null && drawable == null) {
            throw new IllegalArgumentException("back drawable can not be null");
        } else if (drawable != null) {
            this.b = drawable;
            if (drawable2 != null) {
                this.a = drawable2;
            } else {
                this.a = this.b;
            }
        }
    }

    final void a(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("off drawable can not be null");
        }
        this.b = drawable;
    }

    final void b(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("on drawable can not be null");
        }
        this.a = drawable;
    }

    public Drawable getOnDrawable() {
        return this.a;
    }

    public Drawable getOffDrawable() {
        return this.b;
    }

    public void setThumbDrawable(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("thumb drawable can not be null");
        }
        this.c = drawable;
    }

    public Drawable getThumbDrawable() {
        return this.c;
    }

    public void setThumbMargin(int i, int i2, int i3, int i4) {
        this.h = (int) (((float) i) * this.n);
        this.i = (int) (((float) i2) * this.n);
        this.j = (int) (((float) i3) * this.n);
        this.k = (int) (((float) i4) * this.n);
    }

    public void setThumbMarginInPixel(int i, int i2, int i3, int i4) {
        this.h = i;
        this.i = i2;
        this.j = i3;
        this.k = i4;
    }

    public void setThumbMargin(int i, int i2, int i3) {
        setThumbMargin(i, i2, i3, i3);
    }

    public void setThumbMargin(int i, int i2) {
        setThumbMargin(i, i, i2, i2);
    }

    public void setThumbMargin(int i) {
        setThumbMargin(i, i, i, i);
    }

    public void setThumbMarginInPixel(int i) {
        setThumbMarginInPixel(i, i, i, i);
    }

    public int getDefaultThumbMarginInPixel() {
        return (int) (((float) a.e) * this.n);
    }

    public int getThumbMarginTop() {
        return this.h;
    }

    public int getThumbMarginBottom() {
        return this.i;
    }

    public int getThumbMarginLeft() {
        return this.j;
    }

    public int getThumbMarginRight() {
        return this.k;
    }

    public float getDensity() {
        return this.n;
    }

    public void setRadius(float f) {
        this.p = f;
    }

    public float getRadius() {
        if (this.p < 0.0f) {
            return (float) a.f;
        }
        return this.p;
    }

    public void setVelocity(int i) {
        this.o = i;
    }

    public int getVelocity() {
        return this.o;
    }

    public void setOnColor(int i) {
        this.d = i;
    }

    public int getOnColor(int i) {
        return this.d;
    }

    public void setOffColor(int i) {
        this.e = i;
    }

    public int getOffColor() {
        return this.e;
    }

    public void setThumbColor(int i) {
        this.f = i;
    }

    public int getThumbColor() {
        return this.f;
    }

    public void setThumbWidthAndHeightInPixel(int i, int i2) {
        if (i > 0) {
            this.l = i;
        }
        if (i2 > 0) {
            this.m = i2;
        }
    }

    public void setThumbWidthAndHeight(int i, int i2) {
        setThumbWidthAndHeightInPixel((int) (((float) i) * this.n), (int) (((float) i2) * this.n));
    }

    public Drawable getOffDrawableWithFix() {
        if (this.b != null) {
            return this.b;
        }
        return a(this.e);
    }

    public Drawable getOnDrawableWithFix() {
        if (this.a != null) {
            return this.a;
        }
        return a(this.d);
    }

    public Drawable getThumbDrawableWithFix() {
        if (this.c != null) {
            return this.c;
        }
        int[] iArr;
        Drawable stateListDrawable = new StateListDrawable();
        Drawable a = a(this.f);
        Drawable a2 = a(this.g);
        try {
            Field declaredField = View.class.getDeclaredField("PRESSED_ENABLED_STATE_SET");
            declaredField.setAccessible(true);
            iArr = (int[]) declaredField.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            iArr = null;
        }
        if (iArr != null) {
            stateListDrawable.addState(iArr, a2);
        }
        stateListDrawable.addState(new int[0], a);
        return stateListDrawable;
    }

    public float getMeasureFactor() {
        if (this.q <= 0.0f) {
            this.q = a.g;
        }
        return this.q;
    }

    public void setMeasureFactor(float f) {
        if (f <= 0.0f) {
            this.q = a.g;
        }
        this.q = f;
    }

    public Rect getInsetBounds() {
        return this.r;
    }

    public void setInsetBounds(int i, int i2, int i3, int i4) {
        setInsetLeft(i);
        setInsetTop(i2);
        setInsetRight(i3);
        setInsetBottom(i4);
    }

    public void setInsetLeft(int i) {
        if (i > 0) {
            i = -i;
        }
        this.r.left = i;
    }

    public void setInsetTop(int i) {
        if (i > 0) {
            i = -i;
        }
        this.r.top = i;
    }

    public void setInsetRight(int i) {
        if (i > 0) {
            i = -i;
        }
        this.r.right = i;
    }

    public void setInsetBottom(int i) {
        if (i > 0) {
            i = -i;
        }
        this.r.bottom = i;
    }

    public int getInsetX() {
        return getShrinkX() / 2;
    }

    public int getInsetY() {
        return getShrinkY() / 2;
    }

    public int getShrinkX() {
        return this.r.left + this.r.right;
    }

    public int getShrinkY() {
        return this.r.top + this.r.bottom;
    }

    public boolean needShrink() {
        return ((this.r.left + this.r.right) + this.r.top) + this.r.bottom != 0;
    }

    private Drawable a(int i) {
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(getRadius());
        gradientDrawable.setColor(i);
        return gradientDrawable;
    }

    final int a() {
        int i = this.l;
        if (i >= 0) {
            return i;
        }
        if (this.c != null) {
            i = this.c.getIntrinsicWidth();
            if (i > 0) {
                return i;
            }
        }
        if (this.n > 0.0f) {
            return (int) (((float) b.a) * this.n);
        }
        throw new IllegalArgumentException("density must be a positive number");
    }

    final int b() {
        int i = this.m;
        if (i >= 0) {
            return i;
        }
        if (this.c != null) {
            i = this.c.getIntrinsicHeight();
            if (i > 0) {
                return i;
            }
        }
        if (this.n > 0.0f) {
            return (int) (((float) b.a) * this.n);
        }
        throw new IllegalArgumentException("density must be a positive number");
    }
}
